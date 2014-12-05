package com.zeroone_creative.basicapplication.controller.util;import android.util.Log;import com.android.volley.AuthFailureError;import com.android.volley.Request.Priority;import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.Response.Listener;import com.android.volley.VolleyError;import com.zeroone_creative.basicapplication.controller.provider.CustomJSONArrayRequest;import com.zeroone_creative.basicapplication.controller.provider.NetworkTaskCallback;import com.zeroone_creative.basicapplication.controller.provider.NetworkTasks;import com.zeroone_creative.basicapplication.model.system.AppConfig;import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject;import java.io.UnsupportedEncodingException;import java.util.HashMap;import java.util.Map;//ストアの一覧を取得public class JSONArrayRequestUtil {	private NetworkTaskCallback callback;	private String requestFrom;	private Map<String, String> mHeaders = new HashMap<String, String>();		public JSONArrayRequestUtil(final NetworkTaskCallback callback, final String requestFrom, Map<String, String> headers) {        this.callback = callback;        this.requestFrom = requestFrom;        if(headers != null) {            this.mHeaders = headers;        }	}	public void onRequest(RequestQueue mQueue,final Priority requestPriority,final String url,final NetworkTasks task){		if(AppConfig.DEBUG){			Log.d(this.getClass().getSimpleName(),url);		}		CustomJSONArrayRequest request = new CustomJSONArrayRequest(url, new Listener<JSONArray>() {			@Override			public void onResponse(JSONArray response) {				try{					if(AppConfig.DEBUG){						Log.d(this.getClass().getSimpleName(),response.toString());					}					callback.onSuccessNetworkTask(task.id, response);				}catch(IllegalStateException e){					e.printStackTrace();				}			}		},		new Response.ErrorListener() {			@Override 			public void onErrorResponse(VolleyError error) {				JSONObject response = null;				try {	                String responseBody = new String( error.networkResponse.data, "utf-8" );	                response = new JSONObject( responseBody );	            	            } catch ( JSONException e ) {	            	e.printStackTrace();	            } catch (UnsupportedEncodingException e){	            	e.printStackTrace();	            }				if(AppConfig.DEBUG){					if(response!=null){						Log.d(this.getClass().getSimpleName(),"error response:"+response.toString());					}					Log.d(this.getClass().getSimpleName(),"error:"+error.toString());				}				try{					callback.onFailedNetworkTask(task.id,response);				}catch(IllegalStateException e){					e.printStackTrace();				}			}		}){			@Override			public Map<String, String> getHeaders() throws AuthFailureError {				mHeaders.put("User-Agent","Mozilla/5.0 (Linux; U; Android 2.3.4; en-us; Nexus S Build/GRJ22) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");				mHeaders.put("ACCEPT", "text/html");				return mHeaders;			}		};		request.setPriority(requestPriority);		request.setTag(requestFrom);		mQueue.add(request);	}}