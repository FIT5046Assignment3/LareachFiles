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
public class Request {

    private String type;
    private String query;
    private String language;
    private String unit;
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setQuery(String query) {
         this.query = query;
     }
     public String getQuery() {
         return query;
     }

    public void setLanguage(String language) {
         this.language = language;
     }
     public String getLanguage() {
         return language;
     }

    public void setUnit(String unit) {
         this.unit = unit;
     }
     public String getUnit() {
         return unit;
     }

}