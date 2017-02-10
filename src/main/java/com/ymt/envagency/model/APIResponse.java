package com.ymt.envagency.model;

public class APIResponse<T> extends APIResponseBase {

	private T data ;
	
	public APIResponse(T o)
	{
		data=o;
	}
}
