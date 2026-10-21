package inter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CovidApiService {
    @GET("v3/covid-19/countries")
    Call<List<CovidData.CountryData>> getAllCountriesData();

    @GET("v3/covid-19/all")
    Call<CovidData.GlobalStats> getGlobalData();

    @GET("v3/covid-19/countries/{country}")
    Call<CovidData.CountryData> getCountryData(@Path("country") String country);
}
