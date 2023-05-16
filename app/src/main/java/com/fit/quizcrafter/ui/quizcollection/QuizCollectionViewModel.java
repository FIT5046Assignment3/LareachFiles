package com.fit.quizcrafter.ui.quizcollection;

import static com.fit.quizcrafter.api.FirebaseApi.getQuizByuserId;
import static com.fit.quizcrafter.api.FirebaseApi.userId;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.domain.Quiz;
import com.fit.quizcrafter.domain.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class QuizCollectionViewModel extends ViewModel {

    private MutableLiveData<List<Quiz>> quizList;

    public LiveData<List<Quiz>> getQuizs()
    {
        if (quizList == null)
        {
//          get data from data manager layers
            quizList = new MutableLiveData<>();
            loadQuizs();
        }
        return quizList;
    }
    private void loadQuizs() {
        // Do an asynchronous operation to fetch quiz.
        //        load data from firebase
        getQuizByuserId(User.user_id, new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                List<Quiz> quizs = new ArrayList<>();
                for(DataSnapshot dataSnapshot: task.getResult().getChildren())
                {
                    Quiz quiz =  dataSnapshot.getValue(Quiz.class);
                    quizs.add(quiz);
                }
                quizList.setValue(quizs);
            }
        });

    }
}
