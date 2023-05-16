/**
  * Copyright 2023 json.cn 
  */
package com.fit.quizcrafter.domain.weather;
import java.util.Date;

/**
 * Auto-generated: 2023-05-12 16:45:55
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Location {

    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    private String timezone_id;
    private String localtime;
    private double localtime_epoch;
    private String utc_offset;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setCountry(String country) {
         this.country = country;
     }
     public String getCountry() {
         return country;
     }

    public void setRegion(String region) {
         this.region = region;
     }
     public String getRegion() {
         return region;
     }

    public void setLat(String lat) {
         this.lat = lat;
     }
     public String getLat() {
         return lat;
     }

    public void setLon(String lon) {
         this.lon = lon;
     }
     public String getLon() {
         return lon;
     }

    public void setTimezone_id(String timezone_id) {
         this.timezone_id = timezone_id;
     }
     public String getTimezone_id() {
         return timezone_id;
     }

    public void setLocaltime(String localtime) {
         this.localtime = localtime;
     }
     public String getLocaltime() {
         return localtime;
     }

    public void setLocaltime_epoch(double localtime_epoch) {
         this.localtime_epoch = localtime_epoch;
     }
     public double getLocaltime_epoch() {
         return localtime_epoch;
     }

    public void setUtc_offset(String utc_offset) {
         this.utc_offset = utc_offset;
     }
     public String getUtc_offset() {
         return utc_offset;
     }

}