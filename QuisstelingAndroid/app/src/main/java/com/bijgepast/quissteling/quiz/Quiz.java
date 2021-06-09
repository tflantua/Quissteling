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

    public void setId(String id) throws Exception {
        int locationId = Integer.parseInt(id.substring(0, 2)) - 1;
        int questionId = Integer.parseInt(id.substring(2)) - 1;
        Location location;

        if (this.locations.size() - 1 >= locationId) {
            location = this.locations.get(locationId);
            if (location.getQuestions().size() - 1 >= questionId) {
                this.locationId = id.substring(0, 2);
                this.questionId = id.substring(2);
                return;
            }
            throw new Exception("De ingevulde code is niet correct");
        }
        throw new Exception("De ingevulde code is niet correct");
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public String getQuestion() {
        Location location;
        int locationId = Integer.parseInt(this.locationId) - 1;
        int questionId = Integer.parseInt(this.questionId) - 1;

        if (this.locations.size() - 1 >= locationId) {
            location = this.locations.get(locationId);
            if (location.getQuestions().size() - 1 >= questionId)
                return location.getQuestions().get(questionId).getQuestion();
        }
        return "";
    }

    public ArrayList<String> answers() {
        HashMap<String, Boolean> answers = this.locations.get(Integer.parseInt(locationId) - 1).getQuestions().get(Integer.parseInt(questionId) - 1).getAnswers();
        this.answers.addAll(answers.keySet());

        return this.answers;
    }

    public boolean checkAnswer(String answer) {
        HashMap<String, Boolean> answers = this.locations.get(Integer.parseInt(locationId) - 1).getQuestions().get(Integer.parseInt(questionId) - 1).getAnswers();

        if (answers.containsKey(answer)) return answers.get(answer);

        return false;
    }
}
