package com.ymt.envagency.model;

public class MonitorInfo {
	private int configId;
	private int itemId;
	private int cpuData;
	private int coreNumber;
	private int memoryData;
	private int memoryTotalData;
	private int diskReadData;
	private int diskWriteData;
	private int networkReceiveData;
	private int networkSendData;
	private String addInfoTime;

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

	public int getCpuData() {
		return cpuData;
	}

	public void setCpuData(int cpuData) {
		this.cpuData = cpuData;
	}

	public int getCoreNumber() {
		return coreNumber;
	}

	public void setCoreNumber(int coreNumber) {
		this.coreNumber = coreNumber;
	}

	public int getMemoryData() {
		return memoryData;
	}

	public void setMemoryData(int memoryData) {
		this.memoryData = memoryData;
	}

	public int getMemoryTotalData() {
		return memoryTotalData;
	}

	public void setMemoryTotalData(int memoryTotalData) {
		this.memoryTotalData = memoryTotalData;
	}

	public int getDiskReadData() {
		return diskReadData;
	}

	public void setDiskReadData(int diskReadData) {
		this.diskReadData = diskReadData;
	}

	public int getDiskWriteData() {
		return diskWriteData;
	}

	public void setDiskWriteData(int diskWriteData) {
		this.diskWriteData = diskWriteData;
	}

	public int getNetworkReceiveData() {
		return networkReceiveData;
	}

	public void setNetworkReceiveData(int networkReceiveData) {
		this.networkReceiveData = networkReceiveData;
	}

	public int getNetworkSendData() {
		return networkSendData;
	}

	public void setNetworkSendData(int networkSendData) {
		this.networkSendData = networkSendData;
	}

	public String getAddInfoTime() {
		return addInfoTime;
	}

	public void setAddInfoTime(String addInfoTime) {
		this.addInfoTime = addInfoTime;
	}
	
}
