package com.sazs.fyptest1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.sazs.fyptest1.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    TextInputEditText tiName, tiAge, tiHeight, tiWeight, tiLocation, tiInfo, tiNote;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale, radioButton;
    Button btnCancel, btnOrder;
    TextView tvStartDate, tvEndDate, tvStartTime, tvEndTime, tempname;
    DatePickerDialog.OnDateSetListener setListener;
    RequestQueue requestQueue;
    String insertURL = "http://lrgs.ftsm.ukm.my/users/a166118/FYP/order.php";
    SessionManager sessionManager;
    String getId;
    private static final String TAG = ProfileActivity.class.getSimpleName();
    private static final String URL_READ = "http://lrgs.ftsm.ukm.my/users/a166118/FYP/read_order.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);

        tempname = findViewById(R.id.tempname);
        tiName = findViewById(R.id.ti_name);
        tiAge = findViewById(R.id.ti_age);
        tiHeight = findViewById(R.id.ti_height);
        tiWeight = findViewById(R.id.ti_weight);
        tiLocation = findViewById(R.id.ti_location);
        tiInfo = findViewById(R.id.ti_patientInfo);
        tiNote = findViewById(R.id.ti_patientNote);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rgGender = findViewById(R.id.rg);

        tvStartDate = findViewById(R.id.tv_startDate);

        btnCancel = findViewById(R.id.btn_cancel);
        btnOrder = findViewById(R.id.btn_order);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelOrder();
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getUserDetail();

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {



                if (tiName.length()==0){
                    tiName.setError("Enter Name");
                }
                else if (tiAge.length()==0){
                    tiAge.setError("Enter Age");
                }
                else if (tiHeight.length()==0){
                    tiHeight.setError("Enter Height");
                }
                else if (tiWeight.length()==0){
                    tiWeight.setError("Enter Weight");
                }
                else if (tiLocation.length()==0){
                    tiLocation.setError("Enter Location");
                }
                else if (tiInfo.length()==0){
                    tiInfo.setError("Enter Info");
                }
                else if (tiNote.length()==0){
                    tiNote.setError("Enter Note");
                }
                else {
                    StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            System.out.println(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> parameters  = new HashMap<String, String>();
                            parameters.put("elderly_name",tiName.getText().toString());
                            System.out.println(tiName.getText());
                            parameters.put("elderly_age",tiAge.getText().toString());
                            parameters.put("elderly_height",tiHeight.getText().toString());
                            parameters.put("elderly_weight",tiWeight.getText().toString());
                            parameters.put("elderly_location",tiLocation.getText().toString());
                            parameters.put("elderly_info",tiInfo.getText().toString());
                            parameters.put("elderly_note",tiNote.getText().toString());
                            int radioId = rgGender.getCheckedRadioButtonId();
                            radioButton = findViewById(radioId);
                            parameters.put("elderly_gender", radioButton.getText().toString());
                            parameters.put("user_name", tempname.getText().toString());

                            return parameters;
                        }
                    };

                    requestQueue.add(request);

                    Snackbar.make(v, "Order Created", Snackbar.LENGTH_LONG)
                            .setCallback(new Snackbar.Callback(){
                                @Override
                                public void onDismissed(Snackbar transientBottomBar, int event) {
                                    super.onDismissed(transientBottomBar, event);
                                    orderCreated();
                                }
                            }).show();
//
                }

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

    public void orderCreated() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void getUserDetail(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String strName = object.getString("name").trim();

                                    tempname.setText(strName);
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Snackbar.make(getWindow().getDecorView().getRootView(), "Error Read "+e.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(getWindow().getDecorView().getRootView(), "Error Read "+error.toString(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}