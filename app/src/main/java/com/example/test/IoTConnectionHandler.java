package com.example.test;

import android.util.Log;

import java.io.IOException;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IoTConnectionHandler {
    private static IoTConnectionHandler me;
    private OkHttpClient client;
    private static final String TAG = IoTConnectionHandler.class.getName();
    private static final String IOT_URL =
            "http://192.168.1.9:8080/ring?param=0";
    private IoTConnectionHandler() {
        client = new OkHttpClient();
    }
    public static IoTConnectionHandler getInstance() {
        if (me == null)
            me = new IoTConnectionHandler();
        return me;
    }
    public void sendData(String data) {
        Request req = new Request.Builder()
                .url(IOT_URL + data)
                .build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Connection error", e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "Command sent");
            }
        });
    }
}
