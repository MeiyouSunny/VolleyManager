package com.example.allen.volleymanager.volley;

import com.example.allen.volleymanager.config.Urls;
import com.example.allen.volleymanager.entity.LoginRs;
import com.example.allen.volleymanager.entity.Result;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by HuangJW on 2016/2/29 0029 下午 2:46.
 * Mail: 499655607@qq.com
 * Powered by Vcolco
 */
public class ResponseTypeUtil {
    private static HashMap<String, Type> typeMap = new HashMap<String, Type>();

    static {
        typeMap.put(Urls.URL_GoodDrive,
                new TypeToken<Result<LoginRs>>() {
                }.getType());
    }

    public static Type getApiResponseType(String methodName) {
        Type responseType = typeMap.get(methodName);
        return responseType != null ? responseType
                : new TypeToken<Result<String>>() {
        }.getType();
    }

    public static void registerResponseType(String methodName, Type responseType) {
        typeMap.put(methodName, responseType);
    }
}
