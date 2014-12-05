package com.zeroone_creative.basicapplication.controller.provider;

public enum NetworkTasks {

	;
	public int id;
	//Request
	public int method;
	
	private NetworkTasks(int id, int method) {
		this.id = id;
		this.method = method;
	}
}
