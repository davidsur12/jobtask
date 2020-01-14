package com.example.jobtask.con_service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ConSingleton {
    private static ConSingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private ConSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }
    public static synchronized ConSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new ConSingleton(context);
        }
        return instance;
    }
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void add(Request<T> req) {
        getRequestQueue().add(req);
    }
}