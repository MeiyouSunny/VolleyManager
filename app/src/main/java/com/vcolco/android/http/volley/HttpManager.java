package com.vcolco.android.http.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.vcolco.android.http.App;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.OkHttpClient;

/**
 * Created by Allen Lin on 2016/02/18.
 */
public class HttpManager {
    private static HttpManager mHttpManager;
    /**
     * 请求队列
     */
    private RequestQueue mRequestQueue;
    /**
     * 请求/方法 Map
     */
    private static Map<String, Request> requestMap;

    private HttpManager(Context context) {
        mRequestQueue = Volley.newRequestQueue(context, new OkHttp3Stack(new OkHttpClient()));
    }

    public static synchronized HttpManager getInstance() {
        if (mHttpManager == null) {
            mHttpManager = new HttpManager(App.getContext());
            requestMap = Collections
                    .synchronizedMap(new WeakHashMap<String, Request>());
        }
        return mHttpManager;
    }

    /**
     * 添加请求到队列
     */
    private <T> Request<T> add(Request<T> request) {
        return mRequestQueue.add(request);
    }

    public <T> GsonRequest<T> sendRequest(Object tag, BaseRequestParameter requestParameter, String method, Response.Listener<T> listener,
                                          Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.POST, tag, requestParameter.getParams(), method, null, listener, errorListener);
    }

    public <T> GsonRequest<T> sendRequest(Object tag, BaseRequestParameter requestParameter, String method, Type type, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.POST, tag, requestParameter.getParams(), method, type, listener, errorListener);
    }

    public <T> GsonRequest<T> sendRequest(Object tag, Map<String, String> params, String method, Response.Listener<T> listener,
                                          Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.POST, tag, params, method, null, listener, errorListener);
    }

    public <T> GsonRequest<T> sendRequest(Object tag, Map<String, String> params, String method, Type type, Response.Listener<T> listener,
                                          Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.POST, tag, params, method, type, listener, errorListener);
    }

    public <T> GsonRequest<T> sendRequestGet(Object tag, String method, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.GET, tag, null, method, null, listener, errorListener);
    }

    public <T> GsonRequest<T> sendRequestGet(Object tag, String method, Type type, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        return sendRequest(Request.Method.GET, tag, null, method, type, listener, errorListener);
    }

    /**
     * 发送请求
     *
     * @param httpMethod    请求方法GET/POST
     * @param tag           请求TAG,由于取消请求
     * @param params        Post请求参数
     * @param method        请求方法名
     * @param listener      请求成功回调
     * @param errorListener 请求失败回调
     * @param <T>
     * @return
     */
    public <T> GsonRequest<T> sendRequest(int httpMethod, Object tag, Map<String, String> params, String method, Type type, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        if (type == null)
            type = ResponseTypeUtil.getApiResponseType(method);
        GsonRequest<T> request = new GsonRequest<T>(httpMethod, params, method, type, listener, errorListener);
        request.setTag(tag);
        if (!TextUtils.isEmpty(method)) {
            synchronized (requestMap) {
                requestMap.put(method, request);
            }
        }
        add(request);
        return request;
    }

    /**
     * 通过请求方法名取消对应的网络请求
     *
     * @param methodName 请求对应的方法名
     */
    public void cancelRequestByMethodName(String methodName) {
        if (requestMap != null) {
            synchronized (requestMap) {
                if (requestMap.containsKey(methodName)) {
                    Request request = requestMap.remove(methodName);
                    if (request != null && !request.isCanceled())
                        request.cancel();
                }
            }
        }
    }

    /**
     * 取消tag对应的所有的请求
     *
     * @param tag 对应的tag
     */
    public void cancelRequestsByContext(Object tag) {
        if (tag == null)
            return;
        // 取消对应的请求
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
        // 将对应的请求从requestMap中移除
        if (requestMap != null && !requestMap.isEmpty()) {
            synchronized (requestMap) {
                Iterator<Map.Entry<String, Request>> mIterator = requestMap
                        .entrySet().iterator();
                while (mIterator.hasNext()) {
                    Map.Entry<String, Request> entry = mIterator.next();
                    Request request = entry.getValue();
                    if (request.getTag() == tag)
                        mIterator.remove();
                }
            }
        }
    }

}
