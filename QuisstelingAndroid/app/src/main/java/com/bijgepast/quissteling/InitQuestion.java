package com.bijgepast.quissteling;

import com.bijgepast.quissteling.quiz.Location;
import com.bijgepast.quissteling.quiz.Question;
import com.bijgepast.quissteling.quiz.Quiz;

public class InitQuestion {

    public static Quiz add() {
        Quiz quiz = new Quiz();
        try {
            Location location = new Location("01", "Roodkapje");
            Question question = new Question("01", "Wat is blablabla");
            question.addAnswer(true, "dit is waar");
            question.addAnswer(false, "dit is niet waar1");
            question.addAnswer(false, "dit is niet waar2");
            question.addAnswer(false, "dit is niet waar3");

            location.addQuestion(question);
            quiz.addLocation(location);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return quiz;
    }
}
