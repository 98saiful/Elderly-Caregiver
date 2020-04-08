package com.sazs.fyptest1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class OrderActivity extends AppCompatActivity {

    Button btnCancel;
    TextView tvStartDate, tvEndDate, tvStartTime, tvEndTime;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvStartDate = findViewById(R.id.tv_startDate);
        btnCancel = findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelOrder();
            }
        });


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        OrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
//                        String date = day+"-"+month+"-"+year;
                        String date = year+"-"+month+"-"+day;
                        tvStartDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    private void cancelOrder() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
