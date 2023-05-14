package com.fit.quizcrafter.data;

import android.content.Context;

import com.android.volley.Response;
import com.fit.quizcrafter.api.ApiQuiz;
import com.fit.quizcrafter.api.VolleyCallback;
import com.fit.quizcrafter.domain.Quiz;

import java.util.ArrayList;
import java.util.List;

public class mockData {

    public static List<String>  recentQuiz = new ArrayList<>();
    static{
        recentQuiz.add("Linux");
        recentQuiz.add("Devops");
        recentQuiz.add("Networking");
        recentQuiz.add("Programming");
        recentQuiz.add("Cloud");
        recentQuiz.add("Docker");
        recentQuiz.add("Kubernetes");
        recentQuiz.add("And lots more");
        recentQuiz.add("Java");
        recentQuiz.add("JS");
    }

    public static Quiz getQuizDetail(Context context,String title,VolleyCallback volleyCallback)
    {
        final Quiz[] quiz = {null};
//                simulate real data
        new ApiQuiz().getQuiz(context,"1",null,null,volleyCallback);
        quiz[0].setTitle("title");
        quiz[0].setDescription("description");
        return quiz[0];
    }
}
