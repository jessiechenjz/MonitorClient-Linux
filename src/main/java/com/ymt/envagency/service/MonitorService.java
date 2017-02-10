package com.ymt.envagency.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorService {
	
	 private static Boolean _isRunning = false;
	 private static Date deadTime;
     private static Date startTime;
     private static String recallUrl;
     private static int configId;
     private static int itemId;
     //private static int machineId;
     private static String ip;
     private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);  

	private MonitorService(){}
	
	private static final MonitorService monitorService=new MonitorService();
	
	private static MonitorThread monitorThread = new MonitorThread();
	private static Thread thread; // 将myRunnable作为Thread target创建新的线程

	public static MonitorService getInstance(){
		return monitorService;
	}
	
	public static void StartMonitor()
	{
		try {
		
		 if (_isRunning)
         {
			 logger.info("监控已启动");
			 
			 monitorThread.setDeadTime(deadTime);
			 monitorThread.setConfigId(configId);
			 monitorThread.setItemId(itemId);
			 monitorThread.setRecallUrl(recallUrl);
			 
			 logger.info("DeadTime:"+monitorThread.getDeadTime());
			 logger.info("configId:"+monitorThread.getConfigId());
			 logger.info("itemId:"+monitorThread.getItemId());
			 
             return;
         }
		 
		 Date time = new Date();
		 
//		 if(time.before(deadTime) && time.after(startTime))
		if(time.before(deadTime))
		 {
//			 monitorThread.setIp(ip);
//			 monitorThread.setUserName("root");
//			 monitorThread.setPassword("ymt@123");
			 monitorThread.setDeadTime(deadTime);
			 monitorThread.setConfigId(configId);
			 monitorThread.setItemId(itemId);
			 monitorThread.setRecallUrl(recallUrl);
			 
			 logger.info("当前时间在监控时间内，启动监控进程");
			 thread = new Thread(monitorThread);
			 thread.start();
			 logger.info("监控进程start");
			 _isRunning=true;
		 }
		 else {
			 logger.info("当前时间不在监控时间内，未启动监控进程");
		}
		} catch (Exception e) {
			 logger.info("StartMonitor() Exception:"+e);
		}
	}
	
	 @SuppressWarnings("deprecation")
	public static void StopMonitor()
     {
		 thread.stop();
		 _isRunning=false;
		 logger.info("关闭监控进程");
     }
	 
	 public static void setRunning(boolean flag){
		 _isRunning=flag;
	 }
	 
	 public static synchronized  boolean getRunning(){
		 return _isRunning;
	 }    

	public static Date getDeadTime() {
		return deadTime;
	}

	public static void setDeadTime(Date deadTime) {
		MonitorService.deadTime = deadTime;
	}

	public static Date getStartTime() {
		return startTime;
	}

	public static void setStartTime(Date startTime) {
		MonitorService.startTime = startTime;
	}

	public static String getRecallUrl() {
		return recallUrl;
	}

	public static void setRecallUrl(String recallUrl) {
		MonitorService.recallUrl = recallUrl;
	}

	public static int getConfigId() {
		return configId;
	}

	public static void setConfigId(int configId) {
		MonitorService.configId = configId;
	}

	public static int getItemId() {
		return itemId;
	}

	public static void setItemId(int itemId) {
		MonitorService.itemId = itemId;
	}

	public static MonitorService getMonitorservice() {
		return monitorService;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		MonitorService.ip = ip;
	}
	
	
}
