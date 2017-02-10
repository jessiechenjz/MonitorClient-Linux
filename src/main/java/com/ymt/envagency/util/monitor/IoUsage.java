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

public class IoUsage {
	private static Logger log = LoggerFactory.getLogger(IoUsage.class);  
    private static IoUsage INSTANCE = new IoUsage();  
    private static long ioReadTemp = 0;  
    private static long ioWriteTemp = 0; 
    private IoUsage(){  
      
    }  
      
    public static IoUsage getInstance(){  
        return INSTANCE;  
    }  
      
    /** 
     * @Purpose:采集磁盘IO使用率 
     * @param args 
     * @return float,磁盘IO使用率,小于1 
     */  
    public float get() {  
        log.info("开始收集磁盘IO使用率");  
        float ioUsage = 0.0f;  
        Process pro = null;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "iostat -d -x";  
            pro = r.exec(command);  
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
            String line = null;  
            int count =  0;  
            while((line=in.readLine()) != null){          
                if(++count >= 4){  
//                  log.info(line);  
                    String[] temp = line.split("\\s+");  
                    if(temp.length > 1){  
                        float util =  Float.parseFloat(temp[temp.length-1]);  
                        ioUsage = (ioUsage>util)?ioUsage:util;  
                    }  
                }  
            }  
            if(ioUsage > 0){  
                log.info("本节点磁盘IO使用率为: " + ioUsage);      
                ioUsage /= 100;   
            }             
            in.close();  
            pro.destroy();  
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            log.error("IoUsage发生InstantiationException. " + e.getMessage());  
            log.error(sw.toString());  
        }     
        return ioUsage;  
    }  
  
    /** 
     * @Purpose:采集磁盘IO read
     * @param args 
     * @return float,KB
     */  
    public float getRead() {  
//        log.info("开始收集磁盘IO read");  
        float ioRead = 0;  
        Process pro = null;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "iostat -d -k";  
            pro = r.exec(command);  
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
            String line = null;  
            int count =  0;  
            while((line=in.readLine()) != null){          
                if(++count >= 4){  
//                  log.info(line);  
                    String[] temp = line.split("\\s+");  
                    if(temp.length > 3){  
                    	ioRead =  Float.parseFloat(temp[2]);  
                    }  
                }  
            }  
                    
            in.close();  
            pro.destroy();  
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            log.error("IoUsage发生InstantiationException. " + e.getMessage());  
            log.error(sw.toString());  
        }     
        return ioRead;  
    }  
    
    /** 
     * @Purpose:采集磁盘IO Write
     * @param args 
     * @return float,KB
     */  
    public float getWrite() {  
//        log.info("开始收集磁盘IO Write");  
        float ioWrite = 0;  
        Process pro = null;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "iostat -d -k";  
            pro = r.exec(command);  
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
            String line = null;  
            int count =  0;  
            while((line=in.readLine()) != null){          
                if(++count >= 4){  
//                  log.info(line);  
                    String[] temp = line.split("\\s+");  
                    if(temp.length > 3){  
                    	ioWrite =  Float.parseFloat(temp[3]);  
                    }  
                }  
            }  
                    
            in.close();  
            pro.destroy();  
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            log.error("IoUsage发生InstantiationException. " + e.getMessage());  
            log.error(sw.toString());  
        }     
        return ioWrite;  
    }  
  
    /** 
     * @Purpose:采集磁盘IO read
     * @param args 
     * @return float,KB
     */  
    public long[] getData2() {  
//        log.info("开始收集磁盘IO read");  
        long ioRead = 0;  
        long ioWrite = 0;          

        long result[] = new long[4];
        
        Process pro = null;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "cat /proc/diskstats | grep sda";  
            pro = r.exec(command);  
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
            String line = null;  
 
            int count=0;
            while((line=in.readLine()) != null){   
            	
//            	log.info("line"+count+":"+line);
            	
            	if(++count == 1)
            	{ 
                    String[] temp = line.split("\\s+");  
                    if(temp.length > 10){  
                    	long ioReadData =  (long)(Float.parseFloat(temp[6])/2+0.5);  
                    	long ioWriteData =  (long)(Float.parseFloat(temp[10])/2+0.5); 
                    	
                    	  if(ioReadTemp!=0)
                          {
                    		  ioRead=ioReadData-ioReadTemp;
                    		  ioWrite=ioWriteData-ioWriteTemp;
                          }
                    	  ioReadTemp=ioReadData;
                    	  ioWriteTemp=ioWriteData;
                    }

                    break;
                }  
            }  
                    
            in.close();  
            pro.destroy();  
        } catch (IOException e) {  
            log.error("IoUsage发生InstantiationException. " + e.getMessage());  
        }     
        
        result[0]=ioRead;
        result[1]=ioWrite;
        result[2]=ioReadTemp;
        result[3]=ioWriteTemp;
        
        return result;  
    }  
    
    public long[] getData1() {  
//      log.info("开始收集磁盘IO read");  
      long ioRead = 0;  
      long ioWrite = 0;          

      long result[] = new long[4];
      
      Process pro = null;  
      Runtime r = Runtime.getRuntime();  
      try {  
          String command = "cat /proc/diskstats";  
          pro = r.exec(command);  
          BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
          String line = null;  
          int count=0;
          while((line=in.readLine()) != null){          
//        	  log.info("line"+count+":"+line);
        	  if(++count == 26){  
//                log.info(line);  
                  String[] temp = line.split("\\s+");  
                  if(temp.length > 10){  
                  	long ioReadData =  (long)(Float.parseFloat(temp[6])/2+0.5);  
                  	long ioWriteData =  (long)(Float.parseFloat(temp[10])/2+0.5); 
                  	
                  	  if(ioReadTemp!=0)
                        {
                  		  ioRead=ioReadData-ioReadTemp;
                  		  ioWrite=ioWriteData-ioWriteTemp;
                        }
                  	  ioReadTemp=ioReadData;
                  	  ioWriteTemp=ioWriteData;
                  }
                  break;
              }  
			
          }  
                  
          in.close();  
          pro.destroy();  
      } catch (IOException e) {  
          log.error("IoUsage发生InstantiationException. " + e.getMessage());  
      }     
      
      result[0]=ioRead;
      result[1]=ioWrite;
      result[2]=ioReadTemp;
      result[3]=ioWriteTemp;
      
      return result;  
  }  
    
    public long[] getData() {  
//      log.info("开始收集磁盘IO read");  
      long ioRead = 0;  
      long ioWrite = 0;          

      long result[] = new long[2];
      
      String fileName = "/proc/diskstats";
      File file = new File(fileName );
      BufferedReader reader = null;
      try {
          //System.out.println("以行为单位读取文件内容，一次读一整行：");
          reader = new BufferedReader(new FileReader(file));
          String line = null;
          int count = 0;
          // 一次读入一行，直到读入null为文件结束
          while ((line = reader.readLine()) != null) {
              // 显示行号
        	  
  			if(++count == 26){  
//                  log.info(line);  
                    String[] temp = line.split("\\s+");  
                    if(temp.length > 10){  
                    	long ioReadData =  (long)(Float.parseFloat(temp[6])/2+0.5);  
                    	long ioWriteData =  (long)(Float.parseFloat(temp[10])/2+0.5); 
                    	
                    	  if(ioReadTemp!=0)
                          {
                    		  ioRead=ioReadData-ioReadTemp;
                    		  ioWrite=ioWriteData-ioWriteTemp;
                          }
                    	  ioReadTemp=ioReadData;
                    	  ioWriteTemp=ioWriteData;
                    }
                    break;
                }  
          }
          reader.close();
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          if (reader != null) {
              try {
                  reader.close();
              } catch (IOException e1) {
              }
          }
      }
      
      result[0]=ioRead;
      result[1]=ioWrite;
//      result[2]=ioReadTemp;
//      result[3]=ioWriteTemp;
      
      return result;  
  }  
    
    /** 
     * @param args 
     * @throws InterruptedException  
     */  
    public static void main(String[] args) throws InterruptedException {  
        while(true){  
            System.out.println(IoUsage.getInstance().get());  
            Thread.sleep(5000);  
        }  
    }  
}
