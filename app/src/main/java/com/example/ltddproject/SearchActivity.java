package com.example.ltddproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText searchInput;
    private RecyclerView searchResultsRV;
    private CountryAdapter countryAdapter;
    private List<CountryModel> countryList = new ArrayList<>();
    private List<CountryModel> filteredList = new ArrayList<>();
    private ProgressBar loadingPB;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = findViewById(R.id.idETSearchInput);
        searchResultsRV = findViewById(R.id.idRVSearchResults);
        loadingPB = findViewById(R.id.idPBLoading);

        requestQueue = Volley.newRequestQueue(this);
        countryAdapter = new CountryAdapter(filteredList, this);
        searchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRV.setAdapter(countryAdapter);

        fetchCountriesData();

        // Lắng nghe thay đổi từ EditText
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCountries(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void fetchCountriesData() {
        loadingPB.setVisibility(View.VISIBLE);
        String countriesUrl = "https://disease.sh/v3/covid-19/countries";

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                countriesUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject countryData = response.getJSONObject(i);
                                String countryName = countryData.getString("country");
                                String flagUrl = countryData.getJSONObject("countryInfo").getString("flag");
                                String cases = countryData.getString("cases");
                                String deaths = countryData.getString("deaths");
                                String recovered = countryData.getString("recovered");
                                String todayCases = countryData.getString("todayCases");
                                String active = countryData.getString("active");
                                String critical = countryData.getString("critical");

                                countryList.add(new CountryModel(countryName, cases, deaths, recovered, todayCases, "0", active, critical, flagUrl));
                            }
                            filterCountries(searchInput.getText().toString());
                            loadingPB.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            Toast.makeText(SearchActivity.this, "Lỗi xử lý dữ liệu", Toast.LENGTH_SHORT).show();
                            loadingPB.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchActivity.this, "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show();
                        loadingPB.setVisibility(View.GONE);
                    }
                }
        );

        requestQueue.add(request);
    }

    private void filterCountries(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(countryList);
        } else {
            for (CountryModel country : countryList) {
                if (country.getCountryName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(country);
                }
            }
        }
        countryAdapter.notifyDataSetChanged();
    }
}

