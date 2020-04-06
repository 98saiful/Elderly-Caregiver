package com.sazs.fyptest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OrderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
