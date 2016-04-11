package com.avinash.downloadtemplate.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.avinash.downloadtemplate.R;
import com.avinash.downloadtemplate.constants.DownloadConstants;

import org.json.JSONArray;

/**
 * Created by Dell on 4/11/2016.
 */
public class DownloadActivity extends Activity {

    private ProgressDialog pDialog = null;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_activity);
        start = (Button)findViewById(R.id.startDownload);
        final DownloadConstants dc= new DownloadConstants();

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(DownloadActivity.this);
                pDialog.setMessage("Loading...");
                pDialog.show();
                performRequest(dc.getUrl());

            }
        });


    }

    public void performRequest(String url) {
        JsonArrayRequest jar = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                Log.d("JSON Response", response.toString());

                if(pDialog!=null)
                    pDialog.dismiss();
                pDialog=null;

                Toast.makeText(getApplicationContext(), "Response success", Toast.LENGTH_SHORT).show();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                Log.d("Volley error", error.toString() + " ----------- " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error in response", Toast.LENGTH_SHORT).show();
            }
        });

        MainApplicationController.getInstance().addToRequestQueue(jar);
    }

}
