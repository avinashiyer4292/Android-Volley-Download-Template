package com.avinash.downloadtemplate.main;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Dell on 4/11/2016.
 */
public class MainApplicationController extends Application {
    private RequestQueue mRequestQueue;
    private static MainApplicationController mInstance;
    public static MainApplicationController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue==null)
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue!=null)
            mRequestQueue.cancelAll(tag);
    }
}
