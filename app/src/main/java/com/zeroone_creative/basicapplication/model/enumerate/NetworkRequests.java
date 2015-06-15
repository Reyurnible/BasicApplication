package com.zeroone_creative.basicapplication.model.enumerate;

import android.net.Uri;

import com.android.volley.Request;

import java.util.Map;

public enum NetworkRequests {
    GetSample(0, Request.Method.GET),
    ;
    public int id;
    public int method;
    NetworkRequests(int id, int method) {
        this.id = id;
        this.method = method;
    }

    private String getUrlScheme() {
        return "http";
    }

    private String getUrlDomain() {
        switch (this) {
            case GetSample:
            default:
                return "sample.net";
        }
    }

    private String getUrlPath() {
        switch (this) {
            case GetSample:
                return "/api/sample";
            default:
                return "/";
        }
    }

    public Uri.Builder getUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(this.getUrlScheme());
        builder.encodedAuthority(this.getUrlDomain());
        builder.appendEncodedPath(this.getUrlPath());
        return builder;
    }

    public Uri.Builder getUrl(Map<String, String> parameters) {
        Uri.Builder builder = getUrl();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            builder.appendQueryParameter(parameter.getKey(), parameter.getValue());
        }
        return builder;
    }

    public Uri.Builder getUrl(String extraRoute) {
        Uri.Builder builder = getUrl();
        builder.appendEncodedPath(extraRoute);
        return builder;
    }

}
