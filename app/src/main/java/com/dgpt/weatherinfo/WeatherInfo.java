package com.dgpt.weatherinfo;

public class WeatherInfo {
    private String city;
    private String cityid;
    private String temp;
    private String WD;
    private String WS;
    private String sm;

    public WeatherInfo(String city, String cityid, String temp, String WD, String WS, String sm) {
        this.city = city;
        this.cityid = cityid;
        this.temp = temp;
        this.WD = WD;
        this.WS = WS;
        this.sm = sm;
    }

    public WeatherInfo() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "city='" + city + '\'' +
                ", cityid='" + cityid + '\'' +
                ", temp='" + temp + '\'' +
                ", WD='" + WD + '\'' +
                ", WS='" + WS + '\'' +
                ", sm='" + sm + '\'' +
                '}';
    }
}
