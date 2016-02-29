package com.example.allen.volleymanager.volley;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数基类
 * Created by HuangJW on 2016/2/29 0029 下午 2:16.
 * Mail: 499655607@qq.com
 * Powered by Vcolco
 */
public abstract class BaseRequestParameter {
    /**
     * 参数Map
     */
    protected Map<String, String> params = new HashMap<String, String>();

    /**
     * 添加请求参数
     */
    protected abstract void addParameters();

    /**
     * 获取请求参数Map
     *
     * @return Map<String, Object>
     */
    public Map<String, String> getParams() {
        addParameters();
        return params;
    }

}
