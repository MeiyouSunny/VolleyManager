package com.example.allen.volleymanager.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.allen.volleymanager.App;

import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by Allen Lin on 2016/02/18.
 */
public class HttpManager {
    private static HttpManager mHttpManager;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private HttpManager(Context context) {
        mRequestQueue = Volley.newRequestQueue(context, new OkHttp3Stack(new OkHttpClient()));
        mImageLoader = new ImageLoader(mRequestQueue,
                new LruBitmapCache(context));
    }

    public static synchronized HttpManager getInstance() {
        if (mHttpManager == null) {
            mHttpManager = new HttpManager(App.getContext());
        }
        return mHttpManager;
    }

    /**
     * 添加请求到队列
     */
    private <T> Request<T> add(Request<T> request) {
        return mRequestQueue.add(request);
    }

    public <T> GsonRequest<T> sendRequest(Object tag, BaseRequestParameter requestParameter, String url, Response.Listener<T> listener,
                                          Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.POST, tag, requestParameter.getParams(), url, listener, errorListener);
    }

    public <T> GsonRequest<T> sendRequest(Object tag, Map<String, String> params, String url, Response.Listener<T> listener,
                                          Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.POST, tag, params, url, listener, errorListener);
    }

    public <T> GsonRequest<T> sendRequestGet(Object tag, String url, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.GET, tag, null, url, listener, errorListener);
    }

    public <T> GsonRequest<T> sendRequest(int httpMethod, Object tag, Map<String, String> params, String url, Response.Listener<T> listener,
                                          Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<T>(httpMethod, params, url, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * 取消请求
     *
     * @param tag
     */
    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
