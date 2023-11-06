package com.example.currentplacedetailsonmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.Cleaner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Post> adapter;

    private final List<Post> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, items); // Assuming you have a List<Post> called postList

        listView.setAdapter(adapter);


        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<List<Post>> call = apiService.getPosts();



        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    //List<Post> posts = response.body();
                    // Handle the successful response, e.g., update UI
                    Log.e("er11", response.body().toString());
                    items.clear();
                    List x = response.body();
                   // Log.e("er22", response.body());
                    items.addAll(response.body() != null ?  response.body() : Collections.emptyList());
                    adapter.notifyDataSetChanged();

                } else {
                    // Handle the error response
                    Log.e("er", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Handle network errors or request failures
                Log.e("er2", t.toString());
            }
        });

    }


}