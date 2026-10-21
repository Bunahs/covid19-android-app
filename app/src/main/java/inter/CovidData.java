package inter;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidData {
    @SerializedName("global")
    private GlobalStats global;

    @SerializedName("countries")
    private List<CountryData> countries;

    public GlobalStats getGlobal() {
        return global;
    }

    public void setGlobal(GlobalStats global) {
        this.global = global;
    }

    public List<CountryData> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryData> countries) {
        this.countries = countries;
    }


    public static class GlobalStats {
        @SerializedName("cases")
        private long totalConfirmed;

        @SerializedName("deaths")
        private long totalDeaths;

        @SerializedName("recovered")
        private long totalRecovered;

        @SerializedName("active")
        private long activeCases;

        @SerializedName("critical")
        private long criticalCases;

        @SerializedName("todayCases")
        private long todayCases;


        public long getTotalConfirmed() {
            return totalConfirmed;
        }

        public long getTotalDeaths() {
            return totalDeaths;
        }

        public long getTotalRecovered() {
            return totalRecovered;
        }

        public long getActiveCases() {
            return activeCases;
        }

        public long getCriticalCases() {
            return criticalCases;
        }

        public long getTodayCases() {
            return todayCases;
        }
    }


    public static class CountryData {
        @SerializedName("country")
        private String countryName;

        @SerializedName("cases")
        private long totalConfirmed;

        @SerializedName("deaths")
        private long totalDeaths;

        @SerializedName("recovered")
        private long totalRecovered;

        @SerializedName("countryInfo")
        private CountryInfo countryInfo;


        public String getCountryName() {
            return countryName;
        }

        public long getTotalConfirmed() {
            return totalConfirmed;
        }

        public long getTotalDeaths() {
            return totalDeaths;
        }

        public long getTotalRecovered() {
            return totalRecovered;
        }

        public String getFlagUrl() {
            return countryInfo != null ? countryInfo.flag : null;
        }


        public static class CountryInfo {
            @SerializedName("flag")
            private String flag;
        }
    }
}



