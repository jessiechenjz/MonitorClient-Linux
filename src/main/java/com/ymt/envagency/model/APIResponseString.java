package com.ymt.envagency.model;

public class APIResponseString {
	private StateCode StateCode;
	private String Message;
	private String data ;
	public StateCode getStateCode() {
		return StateCode;
	}
	public void setStateCode(StateCode stateCode) {
		StateCode = stateCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
