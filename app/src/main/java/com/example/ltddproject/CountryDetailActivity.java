package com.example.ltddproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.List;

public class CountryDetailActivity extends AppCompatActivity {

    private LineChart lineChart;
    private TextView countryNameTextView;
    Button btnVeTrangChu;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        lineChart = findViewById(R.id.idLineChart);
        countryNameTextView = findViewById(R.id.countryName);
        btnVeTrangChu = findViewById(R.id.btnVeTrangChu);
        String countryName = getIntent().getStringExtra("countryName");
        String totalCases = getIntent().getStringExtra("totalCases");
        String recovered = getIntent().getStringExtra("recovered");
        String deaths = getIntent().getStringExtra("deaths");
        int totalCasesInt = parseSafe(totalCases);
        int recoveredInt = parseSafe(recovered);
        int deathsInt = parseSafe(deaths);
        countryNameTextView.setText(countryName);
        setupLineChart(Integer.parseInt(totalCases), Integer.parseInt(recovered), Integer.parseInt(deaths));





        btnVeTrangChu.setOnClickListener(v -> {
            Intent intent = new Intent(CountryDetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private int parseSafe(String value) {
        try {
            return value != null ? Integer.parseInt(value) : 0;
        } catch (NumberFormatException e) {
            Log.e("CountryDetailActivity", "Error parsing value: " + value, e);
            return 0;
        }
    }
    private void setupLineChart(int totalCases, int recovered, int deaths) {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, totalCases));
        entries.add(new Entry(1, recovered));
        entries.add(new Entry(2, deaths));

        LineDataSet dataSet = new LineDataSet(entries, "Thống Kê COVID");
        dataSet.setColor(getResources().getColor(R.color.blue));
        dataSet.setCircleColor(getResources().getColor(R.color.blue));

        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(6f);
        dataSet.setValueTextColor(getResources().getColor(R.color.black));
        dataSet.setValueTextSize(10f);
        dataSet.setDrawValues(true);
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);

        lineChart.getAxisRight().setEnabled(false);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setForm(Legend.LegendForm.LINE);


        lineChart.setBackgroundColor(getResources().getColor(R.color.white));
    }
}