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
  
/*
 * 采集内存
 */
public class MemUsage {
	private static Logger log = LoggerFactory.getLogger(MemUsage.class);   
	    private static MemUsage INSTANCE = new MemUsage();  
	      
	    private MemUsage(){  
	      
	    }  
	      
	    public static MemUsage getInstance(){  
	        return INSTANCE;  
	    }  
	      
	    /** 
	     * Purpose:采集内存使用率 
	     * @param args 
	     * @return float,内存使用率,小于1 
	     */  
	    public float get() {  
//	        log.info("开始收集memory使用率");  
	        float memUsage = 0.0f;  
	        Process pro = null;  
	        Runtime r = Runtime.getRuntime();  
	        try {  
	            String command = "cat /proc/meminfo";  
	            pro = r.exec(command);  
	            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
	            String line = null;  
	            int count = 0;  
	            long totalMem = 0, freeMem = 0;  
	            while((line=in.readLine()) != null){      
	                log.info(line);   
	                String[] memInfo = line.split("\\s+");  
	                if(memInfo[0].startsWith("MemTotal")){  
	                    totalMem = Long.parseLong(memInfo[1]);  
	                }  
	                if(memInfo[0].startsWith("MemFree")){  
	                    freeMem = Long.parseLong(memInfo[1]);  
	                }  
	                memUsage = 1- (float)freeMem/(float)totalMem;  
	                log.info("本节点内存使用率为: " + memUsage);   
	                if(++count == 2){  
	                    break;  
	                }                 
	            }  
	            in.close();  
	            pro.destroy();  
	        } catch (IOException e) {  
	            StringWriter sw = new StringWriter();  
	            e.printStackTrace(new PrintWriter(sw));  
	            log.error("MemUsage发生InstantiationException. " + e.getMessage());  
	            log.error(sw.toString());  
	        }     
	        return memUsage;  
	    }  
	    
	    /** 
	     * Purpose:采集总内存 
	     * @param args 
	     * @return floatKB
	     */  
	    public float getMenTotal() {  
//	        log.info("开始收集MenTotal");  
	        float totalMem = 0;  
	        Process pro = null;  
	        Runtime r = Runtime.getRuntime();  
	        try {  
	            String command = "cat /proc/meminfo";  
	            pro = r.exec(command);  
	            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
	            String line = null;  
	            int count = 0;  
	          
	            while((line=in.readLine()) != null){      
//	                log.info(line);   
	                String[] memInfo = line.split("\\s+");  
	                if(memInfo[0].startsWith("MemTotal")){  
	                    totalMem = Long.parseLong(memInfo[1]);  
	                }  
	               
//	                log.info("本节点MenTotal为: " + totalMem);   
	                if(++count == 2){  
	                    break;  
	                }                 
	            }  
	            in.close();  
	            pro.destroy();  
	        } catch (IOException e) {  
	            StringWriter sw = new StringWriter();  
	            e.printStackTrace(new PrintWriter(sw));  
	            log.error("MemUsage发生InstantiationException. " + e.getMessage());  
	            log.error(sw.toString());  
	        }     
	        return totalMem;  
	    }  
	    
	    /** 
	     * Purpose:采集内存MemFree
	     * @param args 
	     * @return float KB
	     */  
	    public float getMemFree() {  
//	        log.info("开始收集MemFree");  
	    	
	        float freeMem = 0;  
	        String fileName = "/proc/meminfo";
	        File file = new File(fileName );
	        BufferedReader reader = null;
	        
	        try {  

	            reader = new BufferedReader(new FileReader(file));
	           
	            String line = null;  
	          
	            while((line=reader.readLine()) != null){      
//	                log.info(line);   
	                String[] memInfo = line.split("\\s+");  
	               
	                if(memInfo[0].startsWith("MemFree")){  
	                    freeMem = Long.parseLong(memInfo[1]);  
	                    break;
	                }  

	            }  
	            reader.close(); 
	        } catch (IOException e) {  
	            StringWriter sw = new StringWriter();  
	            e.printStackTrace(new PrintWriter(sw));  
	            log.error("MemUsage发生InstantiationException. " + e.getMessage());  
	            log.error(sw.toString());  
	        }     
	        return freeMem;  
	    }  
	    
	    /** 
	     * Purpose:采集内存MemFree
	     * @param args 
	     * @return float KB
	     */  
	    public float getMemFree1() {  
//	        log.info("开始收集MemFree");  
	        float freeMem = 0;  
	        Process pro = null;  
	        Runtime r = Runtime.getRuntime();  
	        try {  
	            String command = "cat /proc/meminfo";  
	            pro = r.exec(command);  
	            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
	            String line = null;  
	            int count = 0;  
	          
	            while((line=in.readLine()) != null){      
//	                log.info(line);   
	                String[] memInfo = line.split("\\s+");  
	               
	                if(memInfo[0].startsWith("MemFree")){  
	                    freeMem = Long.parseLong(memInfo[1]);  
	                }  
//	                log.info("本节点内存MemFree: " + freeMem);   
	                if(++count == 2){  
	                    break;  
	                }                 
	            }  
	            in.close();  
	            pro.destroy();  
	        } catch (IOException e) {  
	            StringWriter sw = new StringWriter();  
	            e.printStackTrace(new PrintWriter(sw));  
	            log.error("MemUsage发生InstantiationException. " + e.getMessage());  
	            log.error(sw.toString());  
	        }     
	        return freeMem;  
	    }  
	      
	    /** 
	     * @param args 
	     * @throws InterruptedException  
	     */  
	    public static void main(String[] args) throws InterruptedException {  
	        while(true){  
	            System.out.println(MemUsage.getInstance().get());  
	            Thread.sleep(5000);  
	        }  
	    }  
}
