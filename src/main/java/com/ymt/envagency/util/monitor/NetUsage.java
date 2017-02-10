package com.ymt.envagency.util.monitor;

import java.io.BufferedReader;  
import java.io.File;
import java.io.FileReader;
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.io.StringWriter; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetUsage {
	private static Logger log = LoggerFactory.getLogger(NetUsage.class);  
    private static NetUsage INSTANCE = new NetUsage();  
    private final static float TotalBandwidth = 1000;   //网口带宽,Mbps  
    private static long inSizeTemp=0;
    private static long outSizeTemp=0;
    
    private NetUsage(){  
      
    }  
      
    public static NetUsage getInstance(){  
        return INSTANCE;  
    }  
      
    /** 
     * @Purpose:采集网络带宽使用率 
     * @param args 
     * @return float,网络带宽使用率,小于1 
     */   
    public float get() {  
        log.info("开始收集网络带宽使用率");  
        float netUsage = 0.0f;  
        Process pro1,pro2;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "cat /proc/net/dev";  
            //第一次采集流量数据  
            long startTime = System.currentTimeMillis();  
            pro1 = r.exec(command);  
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));  
            String line = null;  
            long inSize1 = 0, outSize1 = 0;  
            while((line=in1.readLine()) != null){     
                line = line.trim();  
                if(line.startsWith("eth0")){  
                    log.info(line);  
                    String[] temp = line.split("\\s+");   
                    inSize1 = Long.parseLong(temp[0].substring(5)); //Receive bytes,单位为Byte  
                    outSize1 = Long.parseLong(temp[8]);             //Transmit bytes,单位为Byte  
                    break;  
                }                 
            }     
            in1.close();  
            pro1.destroy();  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                StringWriter sw = new StringWriter();  
                e.printStackTrace(new PrintWriter(sw));  
                log.error("NetUsage休眠时发生InterruptedException. " + e.getMessage());  
                log.error(sw.toString());  
            }  
            //第二次采集流量数据  
            long endTime = System.currentTimeMillis();  
            pro2 = r.exec(command);  
            BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));  
            long inSize2 = 0 ,outSize2 = 0;  
            while((line=in2.readLine()) != null){     
                line = line.trim();  
                if(line.startsWith("eth0")){  
                    log.info(line);  
                    String[] temp = line.split("\\s+");   
                    inSize2 = Long.parseLong(temp[0].substring(5));  
                    outSize2 = Long.parseLong(temp[8]);  
                    break;  
                }                 
            }  
            if(inSize1 != 0 && outSize1 !=0 && inSize2 != 0 && outSize2 !=0){  
                float interval = (float)(endTime - startTime)/1000;  
                //网口传输速度,单位为bps  
                float curRate = (float)(inSize2 - inSize1 + outSize2 - outSize1)*8/(1000000*interval);  
                netUsage = curRate/TotalBandwidth;  
                log.info("本节点网口速度为: " + curRate + "Mbps");  
                log.info("本节点网络带宽使用率为: " + netUsage);  
            }                 
            in2.close();  
            pro2.destroy();  
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            log.error("NetUsage发生InstantiationException. " + e.getMessage());  
            log.error(sw.toString());  
        }     
        return netUsage;  
    }  
    
    /** 
     * @Purpose:采集网络Receive
     * @param args 
     * @return float,bite
     */   
    public float getReceive() {  
//        log.info("开始收集网络带宽使用率");  
        long inSize1 = 0;
        Process pro1;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "cat /proc/net/dev";  
            //第一次采集流量数据  
            long startTime = System.currentTimeMillis();  
            pro1 = r.exec(command);  
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));  
            String line = null;  
            while((line=in1.readLine()) != null){     
                line = line.trim();  
                if(line.startsWith("eth")){
                    String[] temp = line.split("\\s+");   
                    inSize1 = Long.parseLong(temp[0].substring(5)); //Receive bytes,单位为Byte  
                    break;  
                }                 
            }     
            in1.close();  
            pro1.destroy();  

        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            log.error("NetUsage发生InstantiationException. " + e.getMessage());  
            log.error(sw.toString());  
        }     
        return inSize1;  
    }  
    
    /** 
     * @Purpose:采集网络带宽使用率 
     * @param args 
     * @return float,网络带宽使用率,小于1 
     */   
    public float getSend() {  
//        log.info("开始收集网络带宽使用率");  
        float netUsage = 0.0f;  
        long outSize1 = 0; 
        Process pro1;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "cat /proc/net/dev";  
            //第一次采集流量数据  
            long startTime = System.currentTimeMillis();  
            pro1 = r.exec(command);  
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));  
            String line = null;  
             
            while((line=in1.readLine()) != null){     
                line = line.trim();  
                if(line.startsWith("eth")){  
                    String[] temp = line.split("\\s+");   
                    outSize1 = Long.parseLong(temp[8]);             //Transmit bytes,单位为Byte  
                    break;  
                }                 
            }     
            in1.close();  
            pro1.destroy();  
           
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            log.error("NetUsage发生InstantiationException. " + e.getMessage());  
            log.error(sw.toString());  
        }     
        return outSize1;  
    }  
    
    /** 
     * @Purpose:采集网络带宽使用率 
     * @param args 
     * @return float,网络带宽使用率,小于1 
     */   
    public long[] getData() {  
//        log.info("开始收集网络带宽使用率");  
        float netUsage = 0.0f;  
        long inSize1 = 0; 
        long outSize1 = 0; 
        
        long result[] = new long[2];
        
        String fileName = "/proc/net/dev";
        File file = new File(fileName );
        BufferedReader reader = null;
        
        try {  
        	  //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            int count = 0;
             
            while((line=reader.readLine()) != null){     
                line = line.trim();  
                if(line.startsWith("eth")){  
                    String[] temp = line.split("\\s+");   
                    long inKbyte = Long.parseLong(temp[0].substring(5))/1024; //Receive bytes,单位为Byte,转化为KB
                    long outKbyte = Long.parseLong(temp[8])/1024;             //Transmit bytes,单位为Byte ,转化为KB 
                    
                    if (inSizeTemp!=0) {
		
                        inSize1= inKbyte-inSizeTemp;
                        outSize1= outKbyte-outSizeTemp;
					}
                    
                    inSizeTemp=inKbyte;
                    outSizeTemp=outKbyte;
                    
                    break;  
                }                 
            }     
            reader.close(); 
           
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            log.error("NetUsage发生InstantiationException. " + e.getMessage());  
            log.error(sw.toString());  
        }     
        
        result[0]=inSize1;
        result[1]=outSize1;
//        result[2]=inSizeTemp;
//        result[3]=outSizeTemp;
       
        return result;  
    }  
    
    public long[] getData1() {  
//      log.info("开始收集网络带宽使用率");  
      float netUsage = 0.0f;  
      long inSize1 = 0; 
      long outSize1 = 0; 
      
      long result[] = new long[4];
      
      Process pro1;  
      Runtime r = Runtime.getRuntime();  
      try {  
          String command = "cat /proc/net/dev";  
          //第一次采集流量数据  
          long startTime = System.currentTimeMillis();  
          pro1 = r.exec(command);  
          BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));  
          String line = null;  
           
          while((line=in1.readLine()) != null){     
              line = line.trim();  
              if(line.startsWith("eth")){  
                  String[] temp = line.split("\\s+");   
                  long inKbyte = Long.parseLong(temp[0].substring(5))/1024; //Receive bytes,单位为Byte,转化为KB
                  long outKbyte = Long.parseLong(temp[8])/1024;             //Transmit bytes,单位为Byte ,转化为KB 
                  
                  if (inSizeTemp!=0) {
		
                      inSize1= inKbyte-inSizeTemp;
                      outSize1= outKbyte-outSizeTemp;
					}
                  
                  inSizeTemp=inKbyte;
                  outSizeTemp=outKbyte;
                  
                  break;  
              }                 
          }     
          in1.close();  
          pro1.destroy();  
         
      } catch (IOException e) {  
          StringWriter sw = new StringWriter();  
          e.printStackTrace(new PrintWriter(sw));  
          log.error("NetUsage发生InstantiationException. " + e.getMessage());  
          log.error(sw.toString());  
      }     
      
      result[0]=inSize1;
      result[1]=outSize1;
      result[2]=inSizeTemp;
      result[3]=outSizeTemp;
      
      return result;  
  }  
  
    /** 
     * @param args 
     * @throws InterruptedException  
     */  
    public static void main(String[] args) throws InterruptedException {  
        while(true){  
            System.out.println(NetUsage.getInstance().get());  
            Thread.sleep(5000);  
        }  
    } 
}
