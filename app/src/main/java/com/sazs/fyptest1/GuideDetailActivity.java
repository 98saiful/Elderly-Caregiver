package com.sazs.fyptest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GuideDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        String description = intent.getExtras().getString("Description");
        String picture = intent.getExtras().getString("Picture");

        ImageView ivPicture = findViewById(R.id.iv_guide_img);
        TextView tvTitle = findViewById(R.id.tv_guide_title);
        TextView tvDescription = findViewById(R.id.tv_guide_desc);

        Glide.with(this).load(picture).fitCenter().into(ivPicture);
        tvTitle.setText(title);
        tvDescription.setText(description);

    }


}
