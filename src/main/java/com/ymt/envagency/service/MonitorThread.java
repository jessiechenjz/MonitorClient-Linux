package com.ymt.envagency.service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ymt.envagency.util.HttpRequest;
import com.ymt.envagency.util.monitor.CpuUsage;
import com.ymt.envagency.util.monitor.IoUsage;
import com.ymt.envagency.util.monitor.MemUsage;
import com.ymt.envagency.util.monitor.NetUsage;

public class MonitorThread implements Runnable {

	private String ip;
	private String userName;
	private String password;
	private Date deadTime;
	private int configId;
	private int itemId;
	private String recallUrl;
	
	private static final Logger logger = LoggerFactory.getLogger(MonitorThread.class); 

	public synchronized String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public synchronized String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public synchronized Date getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}
	
	public synchronized int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public synchronized int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public synchronized String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public synchronized String getRecallUrl() {
		return recallUrl;
	}

	public void setRecallUrl(String recallUrl) {
		this.recallUrl = recallUrl;
	}
	
	public void run() {
		logger.info("进入run()");
		try {
			float Memtotal = MemUsage.getInstance().getMenTotal()/1024;

			Date nowDate = new Date();
			
			//这边获取是为了抛弃第一的无意义的取值
			CpuUsage.getInstance().get();
			IoUsage.getInstance().getData();
			NetUsage.getInstance().getData();
			
			logger.info("当前时间："+nowDate);
			
			while (nowDate.before(getDeadTime())) {

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.SECOND, 1);
				String addInfoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(calendar.getTime());
				
				int cpus[]=CpuUsage.getInstance().get();
				
				long ios[]=IoUsage.getInstance().getData();
				long nets[]=NetUsage.getInstance().getData();
				
//				logger.info("cpuData:" + (int)cpus[3]+","+(int)cpus[0]+","+(int)cpus[1]+","+(int)cpus[2]);
//				logger.info("cpuData:" + (int)cpus[3]+","+(int)cpus[0]+","+(int)cpus[1]+","+(int)cpus[2]);
//				logger.info("cpuData:" + (int)cpus[3]+","+(int)cpus[0]+","+(int)cpus[1]+","+(int)cpus[2]);

				String url = recallUrl+"/createStressMonitorInfo.action";
				String param = "configId=" + configId + "&itemId=" + itemId
						+ "&cpuData=" + (int)cpus[0]+ "&cpu1Data=" + (int)cpus[1] + "&cpu2Data=" + (int)cpus[2] 
								+ "&cpu3Data=" + (int)cpus[3] + "&coreNumber=" + 8
						+ "&memoryData=" + (int)(MemUsage.getInstance().getMemFree()/1024)+ "&memoryTotalData="
						+ (int)Memtotal + "&diskReadData=" + (int)(ios[0]) + "&diskWriteData="
						+ (int)(ios[1]) + "&networkReceiveData=" + (int)(nets[0])
						+ "&networkSendData=" + (int)(nets[1]) + "&addInfoTime="
						+ URLEncoder.encode(addInfoTime);
//				logger.info("url:" + url);
//				logger.info("param:" + param);

				 String s = HttpRequest.sendGet(url, param);
				 logger.info("发送："+url+"?"+param+"  返回："+s);
				 
				 Thread.sleep(1000);
				 nowDate = new Date();
			}
			MonitorService.setRunning(false);
			logger.info("监控时间到，监控停止。");
		} catch (Exception e) {
			logger.info("MonitorThread中run异常"+e.toString());
		}

	}

}
