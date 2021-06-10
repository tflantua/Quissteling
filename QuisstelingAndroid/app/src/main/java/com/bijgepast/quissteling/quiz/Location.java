package com.bijgepast.quissteling.quiz;

import java.util.ArrayList;

public class Location {
    private String id;
    private String location;
    private ArrayList<Question> questions;

    public Location(String id, String location) {
        this.questions = new ArrayList<>();
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
