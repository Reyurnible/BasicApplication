package com.zeroone_creative.basicapplication.controller.util;

import android.net.Uri;

/**
 * Created by shunhosaka on 2014/12/06.
 */
public class UriUtil {

    static private String baseUrl = "www.sample.jp";

    static private Uri.Builder getBaseUri() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http");
        builder.encodedAuthority(baseUrl);
        return builder;
    }

    static public String getSampleUri() {
        Uri.Builder builder = getBaseUri();
        //叩く先のAPI
        builder.path("/api/sample.json");
        return builder.build().toString();
    }

}
