package com.ymt.envagency.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymt.envagency.model.APIResponse;
import com.ymt.envagency.model.MachineMonitorConfigPack;
import com.ymt.envagency.service.MonitorService;
import com.ymt.envagency.util.monitor.CpuUsage;
import com.ymt.envagency.util.monitor.IoUsage;
import com.ymt.envagency.util.monitor.NetUsage;


@Controller
@RequestMapping("/Monitor")
public class MonitorControl {

	private static final Logger logger = LoggerFactory
			.getLogger(MonitorControl.class);
	private JSONObject ret = new JSONObject();

	@RequestMapping(value = { "/test" }, method = { RequestMethod.GET })
	public @ResponseBody String test() {

		return "Success333";
	}

	// @RequestMapping(params = "/testJson")
	@RequestMapping(value = { "/testJson" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String testJson() {
		ret.put("StateCode", 0);
		ret.put("retMSG", "成功");
		ret.put("date", "0");
		logger.info("log succcess");
		return ret.toString();
	}

	@RequestMapping(value = { "/GetIoRead" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetIoRead() {

		String outMessage = "";
		int totalMen = 0;

		try {

			totalMen = (int) IoUsage.getInstance().getRead();
			logger.info("GetMonitorMemory成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
			ret.put("Data", totalMen);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			ret.put("Date", totalMen);
		}
		return ret.toString();
	}
	
	@RequestMapping(value = { "/GetIoWrite" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetIoWrite() {

		String outMessage = "";
		int totalMen = 0;

		try {

			totalMen = (int) IoUsage.getInstance().getWrite();
			logger.info("GetMonitorMemory成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
			ret.put("Data", totalMen);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			ret.put("Date", totalMen);
		}
		return ret.toString();
	}
	
	@RequestMapping(value = { "/GetNetSend" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetNetSend() {

		String outMessage = "";
		int totalMen = 0;

		try {

			totalMen = (int) NetUsage.getInstance().getSend();
			logger.info("GetMonitorMemory成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
			ret.put("Data", totalMen);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			ret.put("Date", totalMen);
		}
		return ret.toString();
	}
	
	@RequestMapping(value = { "/GetNetReceive" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetNetReceive() {

		String outMessage = "";
		int totalMen = 0;

		try {

			totalMen = (int) NetUsage.getInstance().getReceive();
			logger.info("GetMonitorMemory成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
			ret.put("Data", totalMen);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			ret.put("Date", totalMen);
		}
		return ret.toString();
	}
	
	@RequestMapping(value = { "/GetNetData" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetNetData() {

		String outMessage = "";

		try {

			long [] data = NetUsage.getInstance().getData();
			logger.info("GetMonitorMemory成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
			ret.put("Data", data[0]+","+data[1]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			//ret.put("Date", totalMen);
		}
		return ret.toString();
	}
	
	@RequestMapping(value = { "/GetIoData" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetIoData() {

		String outMessage = "";

		try {

			long [] data = IoUsage.getInstance().getData();
//			logger.info("GetIoData成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
			ret.put("Data", data[0]+","+data[1]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			//ret.put("Date", totalMen);
		}
		return ret.toString();
	}
	
	@RequestMapping(value = { "/GetIoData1" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetIoData1() {

		String outMessage = "";

		try {

			long [] data = IoUsage.getInstance().getData1();
//			logger.info("GetIoData成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
			ret.put("Data", data[0]+","+data[1]+","+data[2]+","+data[3]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			//ret.put("Date", totalMen);
		}
		return ret.toString();
	}
	
	@RequestMapping(value = { "/GetIoData2" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetIoData2() {

		String outMessage = "";

		try {

			long [] data = IoUsage.getInstance().getData2();
//			logger.info("GetIoData成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
			ret.put("Data", data[0]+","+data[1]+","+data[2]+","+data[3]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			//ret.put("Date", totalMen);
		}
		return ret.toString();
	}
	
	@RequestMapping(value = { "/GetCpu" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetCpu() {

		String outMessage = "";

		try {

			int[]  data = CpuUsage.getInstance().get();
//			logger.info("GetCpu成功");

			ret.put("StateCode", "0");
			ret.put("Message", outMessage);
//			ret.put("Data", data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5]+","+data[6]+","+data[7]);
			ret.put("Data", data[0]+","+data[1]+","+data[2]+","+data[3]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			//ret.put("Date", totalMen);
		}
		return ret.toString();
	}

	// @RequestMapping(params = "/StartMonitorMachine")
	@RequestMapping(value = { "/StartMonitorMachine" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String StartMonitorMachine(MachineMonitorConfigPack config) {

		try {
			logger.info("请求StartMonitorMachine:configId="
					+ config.getConfigId() + "&itemId=" + config.getItemId()
					+ "&startTime=" + config.getStartTime() + "&lastTime="
					+ config.getLastTime() + "&ReCallUrl="
					+ config.getReCallUrl() + "&ip=" + config.getIp());
			MonitorService.setConfigId(config.getConfigId());
			MonitorService.setItemId(config.getItemId());
			MonitorService.setRecallUrl(config.getReCallUrl());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date startDate=new Date();
//			Date startDate = sdf.parse(config.getStartTime());

			Date deadDate = new Date(startDate.getTime() + config.getLastTime()
					* 60 * 1000);

			MonitorService.setStartTime(startDate);
			MonitorService.setDeadTime(deadDate);
			MonitorService.setIp(config.getIp());

			MonitorService.getInstance().StartMonitor();

			logger.info("请求启动监控信息成功");

			ret.put("StateCode", "0");
			ret.put("Message", "成功");
			ret.put("Data", "0");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("请求StartMonitorMachine Exception:"+e);

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			ret.put("date", "1");
		}
		return config.getJsoncallback()+"("+ret.toString()+")";
	}

	@RequestMapping(value = { "/StopMonitorMachine" }, method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String StopMonitorMachine(String jsoncallback) {

		APIResponse<String> rsp;

		try {

			logger.info("请求StopMonitorMachine");

			MonitorService.getInstance().StopMonitor();

			ret.put("StateCode", "0");
			ret.put("Message", "成功");
			ret.put("Data", "0");

			logger.info("请求StopMonitorMachine成功");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("请求StopMonitorMachine Exception:"+e.toString());

			ret.put("StateCode", "1");
			ret.put("Message", e.toString());
			ret.put("Data", "1");
		}

		return jsoncallback+"("+ret.toString()+")";
	}

}
