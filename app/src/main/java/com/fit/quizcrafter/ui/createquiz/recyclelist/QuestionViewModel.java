package com.fit.quizcrafter.ui.createquiz.recyclelist;

import static com.fit.quizcrafter.api.FirebaseApi.getQuizByuserId;
import static com.fit.quizcrafter.api.FirebaseApi.userId;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fit.quizcrafter.domain.Question;
import com.fit.quizcrafter.domain.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel extends ViewModel {
    private MutableLiveData<List<Question>> questionList;

    private boolean flag = false ;

    public LiveData<List<Question>> getQuizs()
    {
        if (questionList == null)
        {
//          get data from data manager layers, init data
            questionList = new MutableLiveData<>();
            questionList.setValue(new ArrayList<>());
            loadQuestions();
        }
        return questionList;
    }
    private void loadQuestions() {
        // Do an asynchronous operation to fetch users.
        //        load data from firebase
//        getQuizByuserId(userId, new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                List<Quiz> quizs = new ArrayList<>();
//                System.out.println("/////////////////////////////////");
//                for(DataSnapshot dataSnapshot: task.getResult().getChildren())
//                {
//                    Quiz quiz =  dataSnapshot.getValue(Quiz.class);
//                    quizs.add(quiz);
//                }
//                quizList.setValue(quizs);
//            }
//        });
    }

    public void setData(List<Question> questionList1)
    {
        questionList = new MutableLiveData<>();
        questionList.setValue(questionList1);
    }

    public void addQuestion(Question question)
    {

        List<Question> questioncur = questionList.getValue();
        questioncur.add(question);
        questionList.setValue(questioncur);
    }
    public void refresh( )
    {
        questionList = new MutableLiveData<>();
        questionList.setValue(new ArrayList<>());
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }
}
