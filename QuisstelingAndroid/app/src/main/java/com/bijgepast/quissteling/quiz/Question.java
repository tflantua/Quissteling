package com.bijgepast.quissteling.quiz;

import java.util.HashMap;

public class Question {
    private String id;
    private String question;
    private HashMap<String, Boolean> answers;

    public Question(String id, String question) {
        this.id = id;
        this.question = question;
        this.answers = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void addAnswer(boolean check, String answer) throws Exception {
        if (this.answers.size() < 4) {
            if (!check) {
                this.answers.put(answer, check);
            } else if
            (!this.answers.containsValue(check)) {
                this.answers.put(answer, check);
            } else throw new Exception("De vraag heeft al een antwoord dat correct is.");
        } else
            throw new Exception("De vraag heeft al 4 antwoorden, meer mogen er niet.");
    }
}
