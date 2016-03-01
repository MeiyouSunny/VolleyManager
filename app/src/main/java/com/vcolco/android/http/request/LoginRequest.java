package com.vcolco.android.http.request;

import com.vcolco.android.http.manager.BaseRequestParameter;

/**
 * Created by HuangJW on 2016/2/29 0029 下午 2:26.
 * Mail: 499655607@qq.com
 * Powered by Vcolco
 */
public class LoginRequest extends BaseRequestParameter {
    public String phoneNum;
    public String pwd;

    @Override
    protected void addParameters() {
        params.put("phoneNum", phoneNum);
        params.put("pwd", pwd);
    }
}
