package com.ymt.envagency.util.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

public class CpuUsage {
	private static Logger log = LoggerFactory.getLogger(CpuUsage.class);
	private static CpuUsage INSTANCE = new CpuUsage();

	private static long idleCpuTimeTemp = 0;
	private static long userCpuTimeTemp = 0;
	private static long systemCpuTimeTemp = 0;
	private static long softirqCpuTimeTemp = 0;
	private static long totalCpuTimeTemp = 0;

	private CpuUsage() {

	}

	public static CpuUsage getInstance() {
		return INSTANCE;
	}

	/**
	 * Purpose:采集CPU使用率
	 * 
	 * @param args
	 * @return float,CPU使用率,小于1
	 */
	public int[] get() {
		// log.info("开始收集cpu使用率");
		int result[] = new int[8];

		int idleCpuUsage = 0, userCpuUsage = 0, systemCpuUsage = 0, softirqCpuUsage = 0;

		 String fileName = "/proc/stat";
	        File file = new File(fileName );
	        BufferedReader reader = null;
	        
		try {
			  //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            int count = 0;
            
			long idleCpuTime1 = 0, userCpuTime1 = 0, systemCpuTime1 = 0, softirqCpuTime1 = 0, totalCpuTime1 = 0; // 分别为系统启动后空闲的CPU时间和总的CPU时间
			while ((line = reader.readLine()) != null) {

			//	log.info("line:" + line);
				if (line.startsWith("cpu ")) {
					line = line.trim();

					String[] temp = line.split("\\s+");
					idleCpuTime1 = Long.parseLong(temp[4]);
					userCpuTime1 = Long.parseLong(temp[1]);
					systemCpuTime1 = Long.parseLong(temp[3]);
					softirqCpuTime1 = Long.parseLong(temp[7]);

					for (String s : temp) {
						if (!s.equals("cpu")) {
							totalCpuTime1 += Long.parseLong(s);
						}
					}

//					log.info("idleCpuTime1:" + idleCpuTime1 + ", userCpuTime1:"
//							+ userCpuTime1 + ", userCpuTime1:" + userCpuTime1
//							+ ", systemCpuTime1:" + systemCpuTime1
//							+ ", softirqCpuTime1:" + softirqCpuTime1
//							+ ", totalCpuTime1:" + totalCpuTime1);

					break;
				}
			}
			reader.close();

			if((totalCpuTime1 - totalCpuTimeTemp)!=0)
			{
				if (idleCpuTimeTemp != 0) {
					idleCpuUsage = (int) (((idleCpuTime1 - idleCpuTimeTemp) * 100 / (totalCpuTime1 - totalCpuTimeTemp)) + 0.5);
				}
	
				if (userCpuTimeTemp != 0) {
					userCpuUsage = (int) (((userCpuTime1 - userCpuTimeTemp) * 100 / (totalCpuTime1 - totalCpuTimeTemp)) + 0.5);
				}
	
				if (systemCpuTimeTemp != 0) {
					systemCpuUsage = (int) (((systemCpuTime1 - systemCpuTimeTemp) * 100 / (totalCpuTime1 - totalCpuTimeTemp)) + 0.5);
				}
	
				if (softirqCpuTimeTemp != 0) {
					softirqCpuUsage = (int) (((softirqCpuTime1 - softirqCpuTimeTemp) * 100 / (totalCpuTime1 - totalCpuTimeTemp)) + 0.5);
				}
			}

			result[3] = idleCpuUsage;
			result[2] = userCpuUsage;
			result[0] = systemCpuUsage;
			result[1] = softirqCpuUsage;

//			result[4] = idleCpuTime1;
//			result[5] = userCpuTime1;
//			result[6] = systemCpuTime1;
//			result[7] = softirqCpuTime1;

			idleCpuTimeTemp = idleCpuTime1;
			userCpuTimeTemp = userCpuTime1;
			systemCpuTimeTemp = systemCpuTime1;
			softirqCpuTimeTemp = softirqCpuTime1;
			totalCpuTimeTemp = totalCpuTime1;

		} catch (IOException e) {
			log.error("CpuUsage发生Exception. " + e.getMessage());
		}

		return result;
	}
	
	/**
	 * Purpose:采集CPU使用率
	 * 
	 * @param args
	 * @return float,CPU使用率,小于1
	 */
	public long[] get1() {
		// log.info("开始收集cpu使用率");
		long result[] = new long[8];

		long idleCpuUsage = 0, userCpuUsage = 0, systemCpuUsage = 0, softirqCpuUsage = 0;
		Process pro1;
		Runtime r = Runtime.getRuntime();
		try {
			String command = "cat /proc/stat";
			// 第一次采集CPU时间
			pro1 = r.exec(command);
			BufferedReader in1 = new BufferedReader(new InputStreamReader(
					pro1.getInputStream()));
			String line = null;
			long idleCpuTime1 = 0, userCpuTime1 = 0, systemCpuTime1 = 0, softirqCpuTime1 = 0, totalCpuTime1 = 0; // 分别为系统启动后空闲的CPU时间和总的CPU时间
			while ((line = in1.readLine()) != null) {

			//	log.info("line:" + line);
				if (line.startsWith("cpu ")) {
					line = line.trim();

					String[] temp = line.split("\\s+");
					idleCpuTime1 = Long.parseLong(temp[4]);
					userCpuTime1 = Long.parseLong(temp[1]);
					systemCpuTime1 = Long.parseLong(temp[3]);
					softirqCpuTime1 = Long.parseLong(temp[7]);

					for (String s : temp) {
						if (!s.equals("cpu")) {
							totalCpuTime1 += Long.parseLong(s);
						}
					}

					log.info("idleCpuTime1:" + idleCpuTime1 + ", userCpuTime1:"
							+ userCpuTime1 + ", userCpuTime1:" + userCpuTime1
							+ ", systemCpuTime1:" + systemCpuTime1
							+ ", softirqCpuTime1:" + softirqCpuTime1
							+ ", totalCpuTime1:" + totalCpuTime1);

					break;
				}
			}
			in1.close();
			pro1.destroy();

			if (idleCpuTimeTemp != 0) {
				idleCpuUsage = (int) (((idleCpuTime1 - idleCpuTimeTemp) * 100 / (totalCpuTime1 - totalCpuTimeTemp)) + 0.5);
			}

			if (userCpuTimeTemp != 0) {
				userCpuUsage = (int) (((userCpuTime1 - userCpuTimeTemp) * 100 / (totalCpuTime1 - totalCpuTimeTemp)) + 0.5);
			}

			if (systemCpuTimeTemp != 0) {
				systemCpuUsage = (int) (((systemCpuTime1 - systemCpuTimeTemp) * 100 / (totalCpuTime1 - totalCpuTimeTemp)) + 0.5);
			}

			if (softirqCpuTimeTemp != 0) {
				softirqCpuUsage = (int) (((softirqCpuTime1 - softirqCpuTimeTemp) * 100 / (totalCpuTime1 - totalCpuTimeTemp)) + 0.5);
			}

			result[0] = idleCpuUsage;
			result[1] = userCpuUsage;
			result[2] = systemCpuUsage;
			result[3] = softirqCpuUsage;

			result[4] = idleCpuTime1;
			result[5] = userCpuTime1;
			result[6] = systemCpuTime1;
			result[7] = softirqCpuTime1;

			idleCpuTimeTemp = idleCpuTime1;
			userCpuTimeTemp = userCpuTime1;
			systemCpuTimeTemp = systemCpuTime1;
			softirqCpuTimeTemp = softirqCpuTime1;
			totalCpuTimeTemp = totalCpuTime1;

		} catch (IOException e) {
			log.error("CpuUsage发生Exception. " + e.getMessage());
		}

		return result;
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// while(true){

		long idleCpuUsage = (int) (((1480957822 - 1480933457) * 100 / (1523434948 - 1523410569)) + 0.5);

		System.out.println(idleCpuUsage);
		// Thread.sleep(1000);
		// }
	}
}
