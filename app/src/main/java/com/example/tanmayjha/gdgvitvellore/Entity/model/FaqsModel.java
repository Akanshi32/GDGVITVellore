package com.example.tanmayjha.gdgvitvellore.Entity.model;

/**
 * Created by tanmay jha on 16-10-2016.
 */
public class FaqsModel {

    public String question;
    public String answer;

    public FaqsModel(){

    }

    public FaqsModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}

