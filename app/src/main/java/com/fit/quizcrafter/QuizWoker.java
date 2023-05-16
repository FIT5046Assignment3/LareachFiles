package com.fit.quizcrafter;

import static com.fit.quizcrafter.api.FirebaseApi.addQuiz;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.fit.quizcrafter.domain.Quiz;
import com.fit.quizcrafter.domain.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class QuizWoker extends Worker {
    public static List<Quiz> quizList = new ArrayList<>();

    public QuizWoker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        for (Quiz quiz: quizList)
        {
            addQuiz(quiz, String.valueOf(User.user_id), null);
        }

        return Result.success();
    }
}
