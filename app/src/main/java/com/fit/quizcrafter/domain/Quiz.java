package com.fit.quizcrafter.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.fit.quizcrafter.domain.room.QuestionConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity(tableName = "quiz")
@TypeConverters(QuestionConverter.class)
public class Quiz {
    @NotNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private List<Question> questionList;
    public String title;
    private String description = " quiz desciption";
    private String key;

    public Quiz() {
        title = "titile" + String.valueOf(new Random().nextInt());
        questionList = new ArrayList<>();
    }

    public Quiz(int theId, List<Question>qList, String theTitle, String desc){
        id = theId;
        questionList = qList;
        title = theTitle;
        description = desc;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public String getTitle() {
        return title;
    }

    public boolean addQuestion(Question question)
    {
        return questionList.add(question);
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Quiz parseQuiz(String str)
    {
        List<Question> list =  new Gson().fromJson(str, new TypeToken<List<Question>>(){}.getType());
        Quiz quiz = new Quiz();
        quiz.setQuestionList(list);
        return quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", questionList=" + questionList +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
