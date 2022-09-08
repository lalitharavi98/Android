package com.learningjavaandroid.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    //Queue all the network transactions in the background
    RequestQueue queue;
    String stringUrl = "https://www.google.com";
    String jsonArrayUrl = "https://jsonplaceholder.typicode.com/todos";
    String jsonObjectUrl = "https://jsonplaceholder.typicode.com/todos/1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView1 = findViewById(R.id.textView1);
        queue = Volley.newRequestQueue(this);

        //request queue using singleton class
        queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

//      Simple string Request
//        stringRequest();
//        JSON Array request
//        JSONArrayRequest();
//       JSON Object request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, jsonObjectUrl, null, response -> {
            try {
                textView1.setText(response.getString("title"));
                Log.d("JSON", "onCreate:  Object" + response.getString("title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.d("JSON", "Failed to fetch JSON Object" );
        });
        queue.add(jsonObjectRequest);



    }


    private void JSONArrayRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, jsonArrayUrl, null,
                response -> Log.d("JSON", "onCreate: " + response.toString()),
                error -> Log.d("JSON", "Failed to fetch JSON" ));

        queue.add(jsonArrayRequest);
    }

    private void stringRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, stringUrl, response -> {
            //display the contents of the url
            Log.d("MAIN", "onCreate: " + response.substring(0,500));
        }, error -> {
            Log.d("MAIN", "Failed to get info");
        });

        queue.add(stringRequest);
    }
}