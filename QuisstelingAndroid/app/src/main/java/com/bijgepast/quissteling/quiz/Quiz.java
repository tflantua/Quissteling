package com.bijgepast.quissteling.quiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.bijgepast.quissteling.PopUpClass;
import com.bijgepast.quissteling.R;

import org.w3c.dom.Text;

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

    public boolean checkAnswer(String answer, View view, Context context) {
        HashMap<String, Boolean> answers = this.locations.get(Integer.parseInt(locationId) - 1).getQuestions().get(Integer.parseInt(questionId) - 1).getAnswers();

        if (answers.containsKey(answer)){
            TextView topText = view.findViewById(R.id.answerpopuptext);
            topText.setText(R.string.correctAnswerText);
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            int score = sharedPreferences.getInt("scorekey", 0);
            score += 100;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("scorekey", score);
            editor.apply();
            TextView bottomText = view.findViewById(R.id.answerpopupscoretext);
            bottomText.setText(R.string.correctAnswerScoreText + score);

            new PopUpClass(view, R.layout.popup_quizanswerscore, context).show();
            return answers.get(answer);
        } else {
            TextView topText = view.findViewById(R.id.answerpopuptext);
            topText.setText(R.string.wrongAnswerText);
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            TextView bottomText = view.findViewById(R.id.answerpopupscoretext);
            bottomText.setText(R.string.wrongAnswerScoreText + sharedPreferences.getInt("scorekey", 0));

            new PopUpClass(view, R.layout.popup_quizanswerscore, context).show();
            return false;
        }

    }
}
