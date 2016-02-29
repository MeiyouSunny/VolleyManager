package com.example.allen.volleymanager.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.allen.volleymanager.R;
import com.example.allen.volleymanager.config.Urls;
import com.example.allen.volleymanager.entity.Result;
import com.example.allen.volleymanager.volley.HttpManager;
import com.example.allen.volleymanager.volley.LoginRequest;

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
//        getJson();
//        getImage();
//        getCircleImage();

        testGoodDrive();
    }

    private void testGoodDrive() {
        LoginRequest request = new LoginRequest();
        request.phoneNum = "13540154458";
        request.pwd = "123456";
        HttpManager.getInstance().sendRequest(TAG, request, Urls.URL_GoodDrive,
                new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        Log.v(TAG, result.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
    }

    private void getJson() {
//        HttpManager.getInstance().sendRequestGet(TAG, Urls.mJsonUrl, Person.class,
//                new Response.Listener<Person>() {
//                    @Override
//                    public void onResponse(Person person) {
//                        Log.v(TAG, person.toString());
//                        mTextview.setText(person.toString());
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, error.getMessage(), error);
//                    }
//                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (HttpManager.getInstance() != null) {
            HttpManager.getInstance().cancel(TAG);
        }

    }
}
