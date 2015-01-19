package com.zeroone_creative.basicapplication.controller.provider;import android.content.Context;import android.os.Build;import com.android.volley.RequestQueue;import com.android.volley.toolbox.HttpClientStack;import com.android.volley.toolbox.HttpStack;import com.android.volley.toolbox.Volley;import org.apache.http.client.CookieStore;import org.apache.http.conn.scheme.PlainSocketFactory;import org.apache.http.conn.scheme.Scheme;import org.apache.http.conn.scheme.SchemeRegistry;import org.apache.http.conn.ssl.SSLSocketFactory;import org.apache.http.conn.ssl.X509HostnameVerifier;import org.apache.http.cookie.Cookie;import org.apache.http.impl.client.BasicCookieStore;import org.apache.http.impl.client.DefaultHttpClient;import org.apache.http.impl.conn.SingleClientConnManager;import javax.net.ssl.HostnameVerifier;//RequestQueuesを管理するクラス。//シングルトン制御public class VolleyHelper{    private static final HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;    public static final Object lock = new Object();    private static RequestQueue mRequestQueue;    private static CustomImageLoader mImageLoader;    private static CookieStore mCookieStore;        /**     * RequestQueueのシングルトン生成     * @param context アプリケーションコンテキスト     * @return     */    public static RequestQueue getRequestQueue(final Context context) {        synchronized (lock) {            if (mRequestQueue == null) {                HttpStack stack;                if (Build.VERSION.SDK_INT >= 9) {                    stack = new MultiPartStack();                } else {                    SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();                    socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);                    SchemeRegistry registry = new SchemeRegistry();                    registry.register(new Scheme("http", new PlainSocketFactory(), 80));                    registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));                    DefaultHttpClient client = new DefaultHttpClient();                    SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);                    client = new DefaultHttpClient(mgr, client.getParams());                    mCookieStore = new BasicCookieStore();                    client.setCookieStore(mCookieStore);                    stack = new HttpClientStack( client );                }                mRequestQueue = Volley.newRequestQueue(context, stack);            }            return mRequestQueue;        }    }    public static CookieStore getCookieStore(){    	 synchronized (lock) {    		return mCookieStore;     	 }    }    public static void addCookie(Cookie cookie){        synchronized (lock) {            mCookieStore.addCookie(cookie);        }    }    public static void RequestCancell(Object tag){    	synchronized (lock) {            if (mRequestQueue != null) {                mRequestQueue.cancelAll(tag);            }        }    }        public static void RequestStart(){    	synchronized (lock) {            if (mRequestQueue != null) {                mRequestQueue.start();            }        }    }        public static void RequestStop(){    	synchronized (lock) {            if (mRequestQueue != null) {                mRequestQueue.stop();            }        }    }        public static CustomImageLoader getImageLoader(final Context context) {        if (mImageLoader == null) {            mImageLoader = new CustomImageLoader(getRequestQueue(context), new BitmapCache());        }        return mImageLoader;    }}