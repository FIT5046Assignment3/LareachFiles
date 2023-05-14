package com.fit.quizcrafter.domain.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fit.quizcrafter.domain.Quiz;

import java.util.List;

@Dao
public interface QuizDao {
    @Query("select * from quiz")
     LiveData<List<Quiz>> getAll();

//    @Query("SELECT * FROM Quiz WHERE id = :customerId LIMIT 1")
//    Quiz findByID(int customerId);

    @Insert
    void insert(Quiz customer);

    @Delete
    void delete(Quiz customer);
    @Update
    void updateCustomer(Quiz quiz);

    @Query("DELETE FROM quiz")
    void deleteAll();

    @Query("SELECT * FROM quiz")
    List<Quiz> getAllQuiz();
}
