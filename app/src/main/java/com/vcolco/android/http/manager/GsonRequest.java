package com.vcolco.android.http.manager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * GsonRequest请求返回指定的实体
 * Created by HuangJW on 2016/2/29 0029 下午 2:16.
 * Mail: 499655607@qq.com
 * Powered by Vcolco
 */
public class GsonRequest<T> extends Request<T> {

    private final Listener<T> mListener;
    private static Gson mGson = new Gson();
    private Map<String, String> mParams;//post Params
    private Type responseType; // 返回数据实体转换类型Type

    public GsonRequest(int method, Map<String, String> params, String url, Type type, Listener<T> listener,
                       ErrorListener errorListener) {
        super(method, url, errorListener);
        if (type == null)
            throw new IllegalArgumentException("GsonRequest with type==null");
        mListener = listener;
        mParams = params;
        this.responseType = type;
        setMyRetryPolicy();
    }

    private void setMyRetryPolicy() {
        setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    //get
    public GsonRequest(String url, Type type, Listener<T> listener, ErrorListener errorListener) {
        this(Method.GET, null, url, type, listener, errorListener);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response<T>) Response.success(mGson.fromJson(jsonString, responseType),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}