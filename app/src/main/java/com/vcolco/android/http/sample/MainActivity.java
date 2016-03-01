package com.vcolco.android.http.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.vcolco.android.http.R;
import com.vcolco.android.http.config.Urls;
import com.vcolco.android.http.entity.Person;
import com.vcolco.android.http.entity.Result;
import com.vcolco.android.http.volley.HttpManager;
import com.vcolco.android.http.volley.LoginRequest;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @Bind(R.id.textview)
    TextView mTextview;
    @Bind(R.id.imageview)
    ImageView mImageview;
    @Bind(R.id.circleimageview)
    CircleImageView mCircleimageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sendPost();
    }

    private void sendPost() {
        LoginRequest request = new LoginRequest();
        request.phoneNum = "13540154458";
        request.pwd = "123456";
        HttpManager.getInstance().sendRequest(this, request, Urls.URL_GoodDrive,
                new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        Log.v(TAG, result.toString());
                        mTextview.setText(result.getData().toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
    }

    private void sendGet() {
        HttpManager.getInstance().sendRequestGet(this, Urls.mJsonUrl, new TypeToken<Person>() {
        }.getType(), new Response.Listener<Person>() {
            public void onResponse(Person person) {
                Log.v(TAG, person.toString());
                mTextview.setText(person.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
