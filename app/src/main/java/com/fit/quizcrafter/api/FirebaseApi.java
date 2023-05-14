package com.fit.quizcrafter.api;

import static com.fit.quizcrafter.domain.Quiz.parseQuiz;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Response;
import com.fit.quizcrafter.domain.Question;
import com.fit.quizcrafter.domain.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FirebaseApi {
    public static DatabaseReference mDatabase;

    public static String userId = "quizId-1";
    public static String collectionName = "create-quiz";

    public static void initData(Context context)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        new ApiQuiz().getQuiz(context, "10", null, null, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Quiz quiz = parseQuiz(result);
                mDatabase.child(collectionName).child(userId).child("1").setValue(quiz);
            }
        });
        new ApiQuiz().getQuiz(context, "1", null, null, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Quiz quiz = parseQuiz(result);
                mDatabase.child(collectionName).child(userId).child("2").setValue(quiz);
            }
        });

    }


    public static void addQuiz(Quiz quiz,String userId, OnCompleteListener onCompleteListener)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Task task= mDatabase.child(collectionName).child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                mDatabase.child(collectionName).child(userId).child(String.valueOf(task.getResult().getChildrenCount() + 1)).setValue(quiz).addOnCompleteListener(onCompleteListener);
            }
        });

    }



    public static void  getQuizByuserId(String userId, OnCompleteListener onCompleteListener)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Task task= mDatabase.child(collectionName).child(userId).get().addOnCompleteListener(onCompleteListener);
    }

    public static void  getQuizByuserIdandIndex(String userId, String index,OnCompleteListener onCompleteListener)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Task task= mDatabase.child(collectionName).child(userId).child("2").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("/////////////////////////////////");
                System.out.println(task.getResult().getValue(Quiz.class));
                System.out.println("end");
            }
        });
    }

}



