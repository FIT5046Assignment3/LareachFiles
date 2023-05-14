/**
  * Copyright 2023 json.cn 
  */
package com.fit.quizcrafter.domain.weather;

/**
 * Auto-generated: 2023-05-12 16:45:55
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Weather {

    private Request request;
    private Location location;
    private Current current;
    public void setRequest(Request request) {
         this.request = request;
     }
     public Request getRequest() {
         return request;
     }

    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

    public void setCurrent(Current current) {
         this.current = current;
     }
     public Current getCurrent() {
         return current;
     }

    @Override
    public String toString() {
        return "Weather{" +
                "request=" + request +
                ", location=" + location +
                ", current=" + current +
                '}';
    }
}