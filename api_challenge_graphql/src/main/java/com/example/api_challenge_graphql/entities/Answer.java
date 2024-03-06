package com.example.api_challenge_graphql.entities;

public class Answer {
    private String answer;

    //Constructor Method
    public Answer(String answer) {
        this.answer = answer;
    }

    //Getter and Setter Methods
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}