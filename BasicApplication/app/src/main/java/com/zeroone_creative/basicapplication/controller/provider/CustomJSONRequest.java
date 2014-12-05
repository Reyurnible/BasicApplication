package com.zeroone_creative.basicapplication.controller.provider;import java.io.UnsupportedEncodingException;import org.json.JSONException;import org.json.JSONObject;import com.android.volley.NetworkResponse;import com.android.volley.ParseError;import com.android.volley.Response;import com.android.volley.Response.ErrorListener;import com.android.volley.Response.Listener;import com.android.volley.toolbox.HttpHeaderParser;import com.android.volley.toolbox.JsonObjectRequest;public class CustomJSONRequest extends JsonObjectRequest {		private Priority mPriority = Priority.LOW;	/**	 * コンストラクタ	 * @param url	 * @param listener	 * @param errorListener	 */	public CustomJSONRequest(int method,String url,JSONObject json, Listener<JSONObject> listener,			ErrorListener errorListener) {		super(method, url, json, listener, errorListener);	}	/**	 * 優先順位を設定する	 * @param priority 優先順位設定	 */	public void setPriority(final Priority priority) {		this.mPriority = priority;	}	/**	 * 現在の優先順位を返却する	 * @return 優先順位	 */	public Priority getPriority() {		return mPriority;	}}