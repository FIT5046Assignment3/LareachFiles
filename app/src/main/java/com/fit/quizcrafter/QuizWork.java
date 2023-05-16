package com.fit.quizcrafter;

import static com.fit.quizcrafter.api.FirebaseApi.addQuiz;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.fit.quizcrafter.R;
import com.fit.quizcrafter.domain.Quiz;
import com.fit.quizcrafter.domain.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class QuizWork extends Worker {

    private String User_id= "test";
    public static Quiz quiz = new Quiz();

    public QuizWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @Override
    public Result doWork() {

        // Do the work here--in this case, upload the images.
        // Indicate whether the work finished successfully with the Result
        addQuiz(quiz, User_id,null);
        return Result.success();

    }

}
