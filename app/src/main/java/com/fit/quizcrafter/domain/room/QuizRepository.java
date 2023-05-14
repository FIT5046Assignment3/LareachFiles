package com.fit.quizcrafter.domain.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fit.quizcrafter.domain.Quiz;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;


public class QuizRepository {
    private QuizDao quizDao;
    private LiveData<List<Quiz>> allQuiz;

    public QuizRepository(Application application){
        QuizDatabase db = QuizDatabase.getInstance(application);
         quizDao=db.quizDao();
        allQuiz= quizDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<Quiz>> getAllQuiz() {
        return allQuiz;
    }
    public List<Quiz> getAllQu() {
        QuizDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(quizDao.getAllQuiz());
            }
        });
        return null;
    }
    public void insert(final Quiz customer){
        QuizDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                quizDao.insert(customer);
            }
        });
    }
    public void deleteAll(){
        QuizDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                quizDao.deleteAll();
            }
        });
    }
    public void delete(final Quiz customer){
        QuizDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                quizDao.delete(customer);
            }
        });
    }
    public void updateCustomer(final Quiz customer){
        QuizDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                quizDao.updateCustomer(customer);
            }
        });
    }

//    public CompletableFuture<Quiz> findByIDFuture(final int customerId) {
//        return CompletableFuture.supplyAsync(new Supplier<Quiz>() {
//            @Override
//            public Quiz get() {
//                return quizDao.findByID(customerId);
//            }
//        }, QuizDatabase.databaseWriteExecutor);
//    }
}