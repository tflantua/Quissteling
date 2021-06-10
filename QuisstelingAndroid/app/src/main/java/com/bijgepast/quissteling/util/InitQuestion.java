package com.bijgepast.quissteling.util;

import com.bijgepast.quissteling.quiz.Location;
import com.bijgepast.quissteling.quiz.Question;
import com.bijgepast.quissteling.quiz.Quiz;

public class InitQuestion {
    public static Quiz quiz = new Quiz();

    public static void add() {
        try {
            //TODO zet alles nog over naar @strings
            Location location = new Location("01", "Roodkapje");
            Question question = new Question("01", "Wat is de haarkleur van Roodkapje?");
            question.addAnswer(true, "Blond");
            question.addAnswer(false, "Rood");
            question.addAnswer(false, "Zwart");
            question.addAnswer(false, "Bruin");
            location.addQuestion(question);

            question = new Question("02", "Roodkapje is wel eens uit het park ontvoerd, in welk jaar was dit voor het eerst gebeurd?");
            question.addAnswer(true, "1998");
            question.addAnswer(false, "1987");
            question.addAnswer(false, "1960");
            question.addAnswer(false, "2006");
            location.addQuestion(question);

            question = new Question("03", "Welk dier komt voor in het verhaal van Roodkapje?");
            question.addAnswer(false, "Een leeuw");
            question.addAnswer(false, "Een vos");
            question.addAnswer(false, "Een kangaroe");
            question.addAnswer(true, "Een wolf");
            location.addQuestion(question);

            question = new Question("04", "Welk familielid van Roodkapje komt in het verhaal voor?");
            question.addAnswer(false, "Haar opa");
            question.addAnswer(true, "Haar oma");
            question.addAnswer(false, "Haar moeder");
            question.addAnswer(false, "Haar zus");
            location.addQuestion(question);

            question = new Question("05", "Wat is de haarkleur van Roodkapje?");
            question.addAnswer(false, "Red Cap");
            question.addAnswer(false, "Girl in red");
            question.addAnswer(true, "Red Riding Hood");
            question.addAnswer(false, "Red Hood");
            location.addQuestion(question);

            question = new Question("06", "Vanaf welk jaar is Roodkapje te vinden in de Essteling?");
            question.addAnswer(true, "1953");
            question.addAnswer(false, "1956");
            question.addAnswer(false, "1970");
            question.addAnswer(false, "2000");
            location.addQuestion(question);

            quiz.addLocation(location);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }
}
