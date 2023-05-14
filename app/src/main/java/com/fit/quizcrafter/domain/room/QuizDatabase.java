package com.fit.quizcrafter.domain.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fit.quizcrafter.domain.Quiz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {Quiz.class}, version = 1, exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
    private static QuizDatabase INSTANCE;
    //we create an ExecutorService with a fixed thread pool so we can later rundatabase operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //A synchronized method in a multi threaded environment means that two threadsare not allowed to access data at the same time
    public static synchronized QuizDatabase getInstance(final Context
                                                                    context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "QuizDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
