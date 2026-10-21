package com.example.ltddproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.time.Instant;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<CountryModel> countryList;
    private Context context;

    public CountryAdapter(List<CountryModel> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }
    public void updateList(List<CountryModel> newList) {
        countryList.clear();
        countryList.addAll(newList);
        notifyDataSetChanged();
    }
    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_covid_data, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        CountryModel country = countryList.get(position);

        holder.countryName.setText(country.getCountryName());
        holder.totalCases.setText("Tổng Ca: " + country.getCases());
        holder.deaths.setText("Tử Vong: " + country.getDeaths());
        holder.recovered.setText("Hồi Phục: " + country.getRecovered());
        holder.todayCases.setText("Ca Mới: " + country.getTodayCases());

        Glide.with(context).load(country.getFlagUrl()).into(holder.countryFlag);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CountryDetailActivity.class);
            intent.putExtra("countryName", country.getCountryName());
            intent.putExtra("totalCases", country.getCases() != null ? country.getCases() : "0");
            intent.putExtra("recovered", country.getRecovered() != null ? country.getRecovered() : "0");
            intent.putExtra("deaths", country.getDeaths() != null ? country.getDeaths() : "0");

            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView countryName, totalCases, todayCases, deaths, recovered;
        ImageView countryFlag;

        public CountryViewHolder(View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.idTVCountryName);
            totalCases = itemView.findViewById(R.id.idTVCases);
            deaths = itemView.findViewById(R.id.idTVDeaths);
            recovered = itemView.findViewById(R.id.idTVRecovered);
            todayCases = itemView.findViewById(R.id.idTVTodayCases);
            countryFlag = itemView.findViewById(R.id.idIVCountryFlag);
        }
    }
}


