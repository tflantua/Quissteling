package com.bijgepast.quissteling.quiz;

import java.util.ArrayList;
import java.util.HashMap;

public class Quiz {
    private String locationId;
    private String questionId;
    private ArrayList<Location> locations;
    private ArrayList<String> answers;

    public Quiz(String id) {
        this.locations = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.locationId = id.substring(0, 2);
        this.questionId = id.substring(2);
    }

    public Quiz() {
        this.locations = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    public void setId(String id) {
        this.locationId = id.substring(0, 2);
        this.questionId = id.substring(2);
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public String getQuestion() {
        return this.locations.get(Integer.parseInt(locationId) - 1).getQuestions().get(Integer.parseInt(questionId) - 1).getQuestion();
    }

    public ArrayList<String> answers() {
        HashMap<String, Boolean> answers = this.locations.get(Integer.parseInt(locationId) - 1).getQuestions().get(Integer.parseInt(questionId) - 1).getAnswers();
        this.answers.addAll(answers.keySet());

        return this.answers;
    }
}
