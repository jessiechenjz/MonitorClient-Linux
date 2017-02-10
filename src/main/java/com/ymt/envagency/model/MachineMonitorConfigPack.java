package com.ymt.envagency.model;

public class MachineMonitorConfigPack {
	private String jsoncallback;
	private int configId;
	private int itemId;
	private String startTime;
	private int lastTime;
	private String ReCallUrl;
	private String ip;

	
	public String getJsoncallback() {
		return jsoncallback;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public String getReCallUrl() {
		return ReCallUrl;
	}

	public void setReCallUrl(String reCallUrl) {
		ReCallUrl = reCallUrl;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
