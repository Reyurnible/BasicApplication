package com.zeroone_creative.basicapplication.model.enumerate;

import android.net.Uri;

import com.android.volley.Request;

public enum NetworkTasks {
    GetSample(0, Request.Method.GET),
    ;

    public int id;
    //Request
    public int method;

    NetworkTasks(int id, int method) {
        this.id = id;
        this.method = method;
    }

    static private String baseUrl = "sample.net";

    public static Uri.Builder getUri(final NetworkTasks tasks) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http");
        builder.encodedAuthority(baseUrl);
        switch (tasks) {
            case GetSample: {
                //叩く先のAPI
                builder.path("/api/Sample");
                break;
            }
        }
        return builder;
    }
}
