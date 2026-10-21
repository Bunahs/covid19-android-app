package com.example.ltddproject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView totalCasesTV, recoveredTV, activeTV, criticalTV, todayCasesTV;
    private ProgressBar loadingPB;
    private RecyclerView countryRV;
    private List<CountryModel> countryModelList;
    private CountryAdapter countryAdapter;
    private RequestQueue requestQueue;
    private FirebaseAuth mAuth;
    private Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        initializeViews();


        fetchGlobalCovidData();


        fetchCountriesData();

        Button searchButton = findViewById(R.id.idBtnSearch);
        searchButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();


                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeViews() {
        totalCasesTV = findViewById(R.id.idTVTotalCases);
        recoveredTV = findViewById(R.id.idTVRecovered);
        activeTV = findViewById(R.id.idTVActiveCases);
        criticalTV = findViewById(R.id.idTVCriticalCases);
        todayCasesTV = findViewById(R.id.idTVTodayCases);
        loadingPB = findViewById(R.id.idPBLoading);
        countryRV = findViewById(R.id.idRVCountries);

        countryModelList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        countryAdapter = new CountryAdapter(countryModelList, this);
        countryRV.setLayoutManager(new LinearLayoutManager(this));
        countryRV.setAdapter(countryAdapter);
    }

    private void fetchGlobalCovidData() {
        String globalUrl = "https://disease.sh/v3/covid-19/all";

        JsonObjectRequest globalDataRequest = new JsonObjectRequest(
                Request.Method.GET,
                globalUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            totalCasesTV.setText(response.getString("cases"));
                            recoveredTV.setText(response.getString("recovered"));
                            activeTV.setText(response.getString("active"));
                            criticalTV.setText(response.getString("critical"));
                            todayCasesTV.setText(response.getString("todayCases"));

                            loadingPB.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Lỗi xử lý dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show();
                        loadingPB.setVisibility(View.GONE);
                    }
                }
        );


        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(globalDataRequest);
    }
    private void fetchCountriesData() {
        String countriesUrl = "https://disease.sh/v3/covid-19/countries";

        JsonArrayRequest countriesDataRequest = new JsonArrayRequest(
                Request.Method.GET,
                countriesUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject countryData = response.getJSONObject(i);
                                Log.d("CountryData", countryData.toString());

                                String countryName = countryData.getString("country");
                                String cases = countryData.getString("cases");
                                String deaths = countryData.getString("deaths");
                                String recovered = countryData.getString("recovered");
                                String todayCases = countryData.getString("todayCases");
                                String todayDeaths = countryData.getString("todayDeaths");
                                String active = countryData.getString("active");
                                String critical=countryData.getString("critical");

                                String flagUrl = countryData.getJSONObject("countryInfo").optString("flag", "");


                                CountryModel country = new CountryModel(countryName, cases, deaths, recovered,
                                        todayCases, todayDeaths, active, critical, flagUrl);
                                countryModelList.add(country);
                            }


                            countryAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Lỗi xử lý dữ liệu quốc gia", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi tải danh sách quốc gia", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        requestQueue.add(countriesDataRequest);
    }

}
