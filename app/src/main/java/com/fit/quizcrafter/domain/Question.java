/**
  * Copyright 2023 bejson.com 
  */
package com.fit.quizcrafter.domain;

import java.util.List;

/**
 * Auto-generated: 2023-05-07 21:50:23
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Question {

    private int id;
    private String question;
    private String description;
    private Answers answers;
    private String multiple_correct_answers;
    private Correct_answers correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;
    private List<Tags> tags;
    private String category;
    private String difficulty;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public Question() {
        answers = new Answers();
        correct_answers = new Correct_answers();
    }

    public void setQuestion(String question) {
         this.question = question;
     }
     public String getQuestion() {
         return question;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setAnswers(Answers answers) {
         this.answers = answers;
     }
     public Answers getAnswers() {
         return answers;
     }

    public void setMultiple_correct_answers(String multiple_correct_answers) {
         this.multiple_correct_answers = multiple_correct_answers;
     }
     public String getMultiple_correct_answers() {
         return multiple_correct_answers;
     }

    public void setCorrect_answers(Correct_answers correct_answers) {
         this.correct_answers = correct_answers;
     }
     public Correct_answers getCorrect_answers() {
         return correct_answers;
     }

    public void setCorrect_answer(String correct_answer) {
         this.correct_answer = correct_answer;
     }
     public String getCorrect_answer() {
         return correct_answer;
     }

    public void setExplanation(String explanation) {
         this.explanation = explanation;
     }
     public String getExplanation() {
         return explanation;
     }

    public void setTip(String tip) {
         this.tip = tip;
     }
     public String getTip() {
         return tip;
     }

    public void setTags(List<Tags> tags) {
         this.tags = tags;
     }
     public List<Tags> getTags() {
         return tags;
     }

    public void setCategory(String category) {
         this.category = category;
     }
     public String getCategory() {
         return category;
     }

    public void setDifficulty(String difficulty) {
         this.difficulty = difficulty;
     }
     public String getDifficulty() {
         return difficulty;
     }

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", description='" + description + '\'' +
                ", answers=" + answers +
                ", multiple_correct_answers='" + multiple_correct_answers + '\'' +
                ", correct_answers=" + correct_answers +
                ", correct_answer='" + correct_answer + '\'' +
                ", explanation='" + explanation + '\'' +
                ", tip='" + tip + '\'' +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}