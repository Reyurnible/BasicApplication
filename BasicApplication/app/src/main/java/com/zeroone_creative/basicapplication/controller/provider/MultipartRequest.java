package com.zeroone_creative.basicapplication.controller.provider;

/**
 * Created by shunhosaka on 2015/01/12.
 */

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class MultipartRequest extends Request<String> {

    private MultipartEntity entity = new MultipartEntity();

    private final Response.Listener<String> mListener;
    private final Map<String, String> mStringParts;
    private final Map<String, File> mFileParts;

    private Priority mPriority = Priority.LOW;

    public MultipartRequest(String url, Response.Listener<String> listener,
                            Response.ErrorListener errorListener,
                            Map<String, String> stringParts, Map<String, File> fileParts) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mStringParts = stringParts;
        mFileParts = fileParts;
        buildMultipartEntity();
    }

    private void buildMultipartEntity() {
        for (Map.Entry<String, String> entry : mStringParts.entrySet()) {
            try {
                entity.addPart(entry.getKey(), new StringBody(entry.getValue()));
            } catch (UnsupportedEncodingException e) {
                VolleyLog.e("UnsupportedEncodingException");
            }
        }
        for (Map.Entry<String, File> entry : mFileParts.entrySet()) {
            entity.addPart(entry.getKey(), new FileBody(entry.getValue()));
        }
    }

    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }

    public MultipartEntity getEntity() {
        return entity;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return Response.success("Uploaded", getCacheEntry());
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    /**
     * 優先順位を設定する
     *
     * @param priority 優先順位設定
     */
    public void setPriority(final Priority priority) {
        this.mPriority = priority;
    }

    /**
     * 現在の優先順位を返却する
     *
     * @return 優先順位
     */
    public Priority getPriority() {
        return mPriority;
    }

}