package com.sazs.fyptest1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.sazs.fyptest1.adapter.GuideAdapter;
import com.sazs.fyptest1.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_GUIDE = "http://lrgs.ftsm.ukm.my/users/a166118/FYP/api_guide.php";

    List<Guide> guideList;
    RecyclerView recyclerView;
    ExtendedFloatingActionButton efab;
    SessionManager sessionManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        recyclerView = findViewById(R.id.guide_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        guideList = new ArrayList<>();

        efab = findViewById(R.id.fab);
        efab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuideActivity();
            }
        });

        recyclerView.addOnScrollListener(scrollListener);

        loadGuide();
    }

    private void openGuideActivity() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_order:
                Intent intent2 = new Intent(this, OrderStatusActivity.class);
                startActivity(intent2);
                return true;
            case R.id.logout:
                sessionManager.logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadGuide() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GUIDE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject guide = array.getJSONObject(i);

                                //adding the product to product list
                                guideList.add(new Guide(
                                        guide.getInt("id"),
                                        guide.getString("title"),
                                        guide.getString("subtitle"),
                                        guide.getString("description"),
                                        guide.getString("picture")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            GuideAdapter adapter = new GuideAdapter(MainActivity.this, guideList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy > 0) {
                efab.shrink();
            } else {
                efab.extend();
            }


        }
    };


}