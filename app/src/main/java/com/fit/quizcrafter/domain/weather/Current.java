/**
  * Copyright 2023 json.cn 
  */
package com.fit.quizcrafter.domain.weather;
import java.util.List;

/**
 * Auto-generated: 2023-05-12 16:45:55
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Current {

    private String observation_time;
    private double temperature;
    private double weather_code;
    private List<String> weather_icons;
    private List<String> weather_descriptions;
    private double wind_speed;
    private double wind_degree;
    private String wind_dir;
    private double pressure;
    private double precip;
    private double humidity;
    private double cloudcover;
    private double feelslike;
    private double uv_index;
    private double visibility;
    private String is_day;
    public void setObservation_time(String observation_time) {
         this.observation_time = observation_time;
     }
     public String getObservation_time() {
         return observation_time;
     }

    public void setTemperature(double temperature) {
         this.temperature = temperature;
     }
     public double getTemperature() {
         return temperature;
     }

    public void setWeather_code(int weather_code) {
         this.weather_code = weather_code;
     }
     public double getWeather_code() {
         return weather_code;
     }

    public void setWeather_icons(List<String> weather_icons) {
         this.weather_icons = weather_icons;
     }
     public List<String> getWeather_icons() {
         return weather_icons;
     }

    public void setWeather_descriptions(List<String> weather_descriptions) {
         this.weather_descriptions = weather_descriptions;
     }
     public List<String> getWeather_descriptions() {
         return weather_descriptions;
     }

    public void setWind_speed(double wind_speed) {
         this.wind_speed = wind_speed;
     }
     public double getWind_speed() {
         return wind_speed;
     }

    public void setWind_degree(int wind_degree) {
         this.wind_degree = wind_degree;
     }
     public double getWind_degree() {
         return wind_degree;
     }

    public void setWind_dir(String wind_dir) {
         this.wind_dir = wind_dir;
     }
     public String getWind_dir() {
         return wind_dir;
     }

    public void setPressure(int pressure) {
         this.pressure = pressure;
     }
     public double getPressure() {
         return pressure;
     }

    public void setPrecip(int precip) {
         this.precip = precip;
     }
     public double getPrecip() {
         return precip;
     }

    public void setHumidity(int humidity) {
         this.humidity = humidity;
     }
     public double getHumidity() {
         return humidity;
     }

    public void setCloudcover(int cloudcover) {
         this.cloudcover = cloudcover;
     }
     public double getCloudcover() {
         return cloudcover;
     }

    public void setFeelslike(int feelslike) {
         this.feelslike = feelslike;
     }
     public double getFeelslike() {
         return feelslike;
     }

    public void setUv_index(int uv_index) {
         this.uv_index = uv_index;
     }
     public double getUv_index() {
         return uv_index;
     }

    public void setVisibility(int visibility) {
         this.visibility = visibility;
     }
     public double getVisibility() {
         return visibility;
     }

    public void setIs_day(String is_day) {
         this.is_day = is_day;
     }
     public String getIs_day() {
         return is_day;
     }

    @Override
    public String toString() {
        return "Current{" +
                "observation_time='" + observation_time + '\'' +
                ", temperature=" + temperature +
                ", weather_code=" + weather_code +
                ", weather_icons=" + weather_icons +
                ", weather_descriptions=" + weather_descriptions +
                ", wind_speed=" + wind_speed +
                ", wind_degree=" + wind_degree +
                ", wind_dir='" + wind_dir + '\'' +
                ", pressure=" + pressure +
                ", precip=" + precip +
                ", humidity=" + humidity +
                ", cloudcover=" + cloudcover +
                ", feelslike=" + feelslike +
                ", uv_index=" + uv_index +
                ", visibility=" + visibility +
                ", is_day='" + is_day + '\'' +
                '}';
    }
}