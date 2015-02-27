package com.zeroone_creative.basicapplication.model.enumerate;

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
