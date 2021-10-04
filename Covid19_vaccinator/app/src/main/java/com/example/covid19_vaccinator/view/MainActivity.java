package com.example.covid19_vaccinator.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19_vaccinator.R;
import com.example.covid19_vaccinator.adapter.VaccineInfoAdapter;
import com.example.covid19_vaccinator.model.VaccineModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

   String baseURL="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";
   private EditText areaPINcode;
   private Button forwardbtn;
   ProgressBar holdOnProgress;
   private ArrayList<VaccineModel> vaccination_centers;
   private RecyclerView resultRecyclerView;
   String areaPIN,avlDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mapViews();
        onClickSetup();

    }

    private void onClickSetup() {
      forwardbtn.setOnClickListener(view -> {
          holdOnProgress.setVisibility(View.VISIBLE);
          DialogFragment dp= new PickDate();
          dp.show(getSupportFragmentManager(),"Pick a Date");
      });
    }

    private void mapViews() {
        forwardbtn=findViewById(R.id.getResults);
        holdOnProgress=findViewById(R.id.progress_circular);
        areaPINcode=findViewById(R.id.enterPincode);
        resultRecyclerView=findViewById(R.id.recyclerView);
        resultRecyclerView.setHasFixedSize(true);
        vaccination_centers = new ArrayList<VaccineModel>();


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        Calendar k= Calendar.getInstance();
        k.set(Calendar.YEAR,year);
        k.set(Calendar.MONTH,monthOfYear);
        k.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone(k.getTimeZone());
        String d=dateFormat.format(k.getTime());
        setup(d);


    }

    private void setup(String d) {
        avlDate=d;
        fetchDataNow();


    }

    private void fetchDataNow() {

        vaccination_centers.clear();
        areaPIN=areaPINcode.getText().toString();
        String url_api= baseURL + "?pincode="+areaPIN+"&date="+avlDate;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray sessonArray = object.getJSONArray("sessions");
                    for (int i = 0; i < sessonArray.length(); i++) {
                        JSONObject sesObject = sessonArray.getJSONObject(i);
                        VaccineModel vaccineModel = new VaccineModel();
                        vaccineModel.setVaccineCenter(sesObject.getString("name"));
                        vaccineModel.setVaccinationCenterAddress(sesObject.getString("address"));
                        vaccineModel.setVaccineTimings(sesObject.getString("from"));
                        vaccineModel.setVaccineCenterTimings(sesObject.getString("to"));
                        vaccineModel.setVaccineName(sesObject.getString("vaccine"));
                        vaccineModel.setVaccinationCharges(sesObject.getString("fee_type"));
                        vaccineModel.setVaccineAge(sesObject.getString("min_age_limit"));
                        vaccineModel.setVaccineAvailable(sesObject.getString("available_capacity"));
                        vaccination_centers.add(vaccineModel);
                    }

                    VaccineInfoAdapter vaccineInfoAdapter = new VaccineInfoAdapter(getApplicationContext(), vaccination_centers);
                    resultRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    resultRecyclerView.setAdapter(vaccineInfoAdapter);
                    holdOnProgress.setVisibility(View.INVISIBLE);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, error -> {
            holdOnProgress.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}