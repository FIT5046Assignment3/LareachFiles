package com.fit.quizcrafter.fragment;

public class QuizQuestion {

    //Attributes for all types of questions
    String questionTitle;
    Boolean isMcqQuestion;

    //Attributes for only MCQ questions
    String option1;
    String option2;
    String option3;
    String option4;
    String option5;
    String option6;
    int correctOption;

    //Attributes for only written questions
    String correctWrittenAnswer;

    public QuizQuestion(){

    }

    //constructor for all question types
    public QuizQuestion(String questionTitle, Boolean isMcqQuestion,
                        String option1, String option2, String option3,
                        String option4, int correctOption,String correctWrittenAnswer,
                        String option5, String option6) {
        this.questionTitle = questionTitle;
        this.isMcqQuestion = isMcqQuestion;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.correctOption = correctOption;
        this.correctWrittenAnswer = correctWrittenAnswer;
    }

    //constructor for only MCQ questions
    public QuizQuestion(String questionTitle, Boolean isMcqQuestion,
                        String option1, String option2, String option3,
                        String option4, int correctOption, String option5,
                        String option6) {
        this.questionTitle = questionTitle;
        this.isMcqQuestion = isMcqQuestion;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.correctOption = correctOption;

        if (isMcqQuestion){
            correctWrittenAnswer = null;
        }
    }

    //constructor for only written questions
    public QuizQuestion(String questionTitle, Boolean isMcqQuestion,
                        String correctWrittenAnswer) {
        this.questionTitle = questionTitle;
        this.isMcqQuestion = isMcqQuestion;
        this.correctWrittenAnswer = correctWrittenAnswer;

        if(!isMcqQuestion){
            option1 = null;
            option2 = null;
            option3 = null;
            option4 = null;
            option5 = null;
            option6 = null;
            correctOption = 0;
        }
    }

}
