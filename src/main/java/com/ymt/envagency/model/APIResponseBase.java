package com.ymt.envagency.model;

public class APIResponseBase {
	private StateCode StateCode;
	private String Message;

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

}
