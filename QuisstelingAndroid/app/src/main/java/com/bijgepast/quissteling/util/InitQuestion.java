package com.bijgepast.quissteling.util;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.quiz.Location;
import com.bijgepast.quissteling.quiz.Question;
import com.bijgepast.quissteling.quiz.Quiz;

public class InitQuestion {
    public static Quiz quiz = new Quiz();

    public static void add(Context context) {
        try {
            Location location = new Location("01", context.getString(R.string.red_riding_hood));
            Question question = new Question("01", context.getString(R.string.red_riding_hood_question_hearcolor));
            question.addAnswer(true, context.getString(R.string.red_riding_hood_question_hearcolor_blond));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_hearcolor_red));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_hearcolor_black));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_hearcolor_brown));
            location.addQuestion(question);

            question = new Question("02", context.getString(R.string.red_riding_hood_question_kidnapping));
            question.addAnswer(true, "1998");
            question.addAnswer(false, "1987");
            question.addAnswer(false, "1960");
            question.addAnswer(false, "2006");
            location.addQuestion(question);

            question = new Question("03", context.getString(R.string.red_riding_hood_question_animal));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_animal_lion));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_animal_fox));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_animal_kangaroo));
            question.addAnswer(true, context.getString(R.string.red_riding_hood_question_animal_wolf));
            location.addQuestion(question);

            question = new Question("04", context.getString(R.string.red_riding_hood_question_familymember));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_familymember_grandpa));
            question.addAnswer(true, context.getString(R.string.red_riding_hood_question_familymember_grandma));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_familymember_father));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_familymember_sister));
            location.addQuestion(question);

            question = new Question("05", context.getString(R.string.red_riding_hood_question_otherLanguage));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_otherLanguage_first));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_otherLanguage_second));
            question.addAnswer(true, context.getString(R.string.red_riding_hood_question_otherLanguage_third));
            question.addAnswer(false, context.getString(R.string.red_riding_hood_question_otherLanguage_fourth));
            location.addQuestion(question);

            question = new Question("06", context.getString(R.string.red_riding_hood_question_yearSinceInPark));
            question.addAnswer(true, "1953");
            question.addAnswer(false, "1956");
            question.addAnswer(false, "1970");
            question.addAnswer(false, "2000");
            location.addQuestion(question);

            quiz.addLocation(location);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
