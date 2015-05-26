package com.zeroone_creative.basicapplication.controller.util;import android.content.Context;import android.util.Log;import com.android.volley.AuthFailureError;import com.android.volley.Request;import com.android.volley.Request.Priority;import com.android.volley.Response;import com.android.volley.Response.Listener;import com.android.volley.VolleyError;import com.zeroone_creative.basicapplication.controller.provider.CustomJSONArrayRequest;import com.zeroone_creative.basicapplication.controller.provider.CustomJSONRequest;import com.zeroone_creative.basicapplication.controller.provider.VolleyHelper;import com.zeroone_creative.basicapplication.model.enumerate.NetworkTasks;import com.zeroone_creative.basicapplication.model.system.AppConfig;import org.json.JSONArray;import org.json.JSONObject;import java.util.HashMap;import java.util.Map;//ストアの一覧を取得public class VolleyJSONRequest {    private NetworkTasks mTask;    private String mUrl;    private JSONObject mParams;    private Map<String, String> mHeaders = new HashMap<String, String>();    private Priority mPriority = Priority.LOW;    public static VolleyJSONRequest newRequest(NetworkTasks task) {        return new VolleyJSONRequest(task);    }    public VolleyJSONRequest(NetworkTasks task) {        this.mTask = mTask;    }    /**     * if update url     *     * @param url     * @return     */    public VolleyJSONRequest url(final String url) {        mUrl = url;        return this;    }    public VolleyJSONRequest priority(final Priority priority) {        this.mPriority = priority;        return this;    }    public VolleyJSONRequest params(JSONObject params) {        this.mParams = params;        return this;    }    public VolleyJSONRequest header(Map<String, String> headers) {        if (headers != null) {            this.mHeaders = headers;        }        return this;    }    public void request(Context context, final RequestType type, final NetworkTaskCallback callback) {        Request request = null;        if (type == RequestType.Object) {            request = getObjectRequest(callback);            ((CustomJSONRequest) request).setPriority(mPriority);        } else if (type == RequestType.Array) {            request = getArrayRequest(callback);            ((CustomJSONArrayRequest) request).setPriority(mPriority);        }        if (request == null) return;        request.setTag(mTask.name());        VolleyHelper.getRequestQueue(context).add(request);    }    public Request<JSONObject> getObjectRequest(final NetworkTaskCallback callback) {        if (AppConfig.DEBUG) {            Log.d(this.getClass().getSimpleName(), mUrl);        }        CustomJSONRequest request = new CustomJSONRequest(mTask.method, mUrl, mParams,                new Listener<JSONObject>() {                    @Override                    public void onResponse(JSONObject response) {                        if (AppConfig.DEBUG) {                            Log.d(this.getClass().getSimpleName(), "response:" + response.toString());                        }                        try {                            if (callback != null) {                                callback.onSuccessNetworkTask(mTask.id, response);                            }                        } catch (IllegalStateException e) {                            e.printStackTrace();                        }                    }                },                new Response.ErrorListener() {                    @Override                    public void onErrorResponse(VolleyError error) {                        if (AppConfig.DEBUG) {                            Log.d(this.getClass().getSimpleName(), "error:" + error.toString());                        }                        try {                            if (callback != null) {                                callback.onFailedNetworkTask(mTask.id, error);                            }                        } catch (IllegalStateException e) {                            e.printStackTrace();                        }                    }                }) {            @Override            public Map<String, String> getHeaders() throws AuthFailureError {                mHeaders.putAll(super.getHeaders());                return mHeaders;            }        };        return request;    }    private Request<JSONArray> getArrayRequest(final NetworkTaskCallback callback) {        if (AppConfig.DEBUG) {            Log.d(this.getClass().getSimpleName(), mUrl);        }        CustomJSONArrayRequest request = new CustomJSONArrayRequest(mUrl,                new Listener<JSONArray>() {                    @Override                    public void onResponse(JSONArray response) {                        if (AppConfig.DEBUG) {                            Log.d(this.getClass().getSimpleName(), response.toString());                        }                        try {                            if (callback != null) {                                callback.onSuccessNetworkTask(mTask.id, response);                            }                        } catch (IllegalStateException e) {                            e.printStackTrace();                        }                    }                },                new Response.ErrorListener() {                    @Override                    public void onErrorResponse(VolleyError error) {                        if (AppConfig.DEBUG) {                            Log.d(this.getClass().getSimpleName(), "error:" + error.toString());                        }                        try {                            if (callback != null) {                                callback.onFailedNetworkTask(mTask.id, error);                            }                        } catch (IllegalStateException e) {                            e.printStackTrace();                        }                    }                }) {            @Override            public Map<String, String> getHeaders() throws AuthFailureError {                mHeaders.putAll(super.getHeaders());                return mHeaders;            }        };        return request;    }    public interface NetworkTaskCallback {        void onSuccessNetworkTask(final int taskId, final Object response);        void onFailedNetworkTask(final int taskId, final VolleyError error);    }    public enum RequestType {        Object,        Array,;    }}