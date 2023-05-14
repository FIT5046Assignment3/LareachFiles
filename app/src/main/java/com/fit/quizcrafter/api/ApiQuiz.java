package com.fit.quizcrafter.api;




import static com.fit.quizcrafter.domain.Quiz.parseQuiz;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fit.quizcrafter.domain.Quiz;


public class ApiQuiz {
    static String auth = "apiKey=UhbZHI5OLZEFRcRfjqbU8JGAoFp0MLiNtExInlVD";
    static String url = "https://quizapi.io/api/v1/questions";

    public void getRandomQuiz(Context context, Response.Listener<String> listener)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "?" + auth,listener,null);
        // Get a RequestQueue
        RequestQueue queue = VolleyQueue.getInstance(context.getApplicationContext()).
                getRequestQueue();
        queue.add(stringRequest);
    }

    public void getQuiz(Context context ,String limit,String category,String difficulty,VolleyCallback volleyCallback)
    {
        String realUrl = url;
        String para="";
        if(limit != null)
        {
            para = para + "limit="+ limit + "&";
        }
        if(category != null)
        {
            para = para + "category="+ category + "&";
        }
        if(difficulty != null)
        {
            para = para + "difficulty="+ difficulty + "&";
        }
        if(para!=null)
        {
            realUrl  = realUrl + "?" + para  + auth;
        }
        else
        {
            realUrl  = realUrl + "?"  + auth;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, realUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
            }
        });

        RequestQueue queue = VolleyQueue.getInstance(context.getApplicationContext()).getRequestQueue();
        queue.add(stringRequest);
    }

}
