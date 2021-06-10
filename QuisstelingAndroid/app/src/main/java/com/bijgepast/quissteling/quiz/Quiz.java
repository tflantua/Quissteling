package com.bijgepast.quissteling.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bijgepast.quissteling.HomeActivity.HomeActivity;
import com.bijgepast.quissteling.util.PopUpClass;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.util.UserSetting;

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
        this.answers.clear();
        this.answers.addAll(answers.keySet());

        return this.answers;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public void checkAnswer(String answer, View view, Context context) {
        HashMap<String, Boolean> answers = this.locations.get(Integer.parseInt(locationId) - 1).getQuestions().get(Integer.parseInt(questionId) - 1).getAnswers();
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.popup_quizanswerscore, null);
        UserSetting userSetting = new UserSetting(context);

        if (answers.containsKey(answer)) {
            if (answers.get(answer)) {
                TextView topText = v.findViewById(R.id.answerpopuptext);
                topText.setText(context.getString(R.string.correctAnswerText));
                userSetting.addScore(100);
                TextView bottomText = v.findViewById(R.id.answerpopupscoretext);
                bottomText.setText(context.getString(R.string.correctAnswerScoreText) + userSetting.getScore());
            } else {
                TextView topText = v.findViewById(R.id.answerpopuptext);
                topText.setText(context.getString(R.string.wrongAnswerText));
                TextView bottomText = v.findViewById(R.id.answerpopupscoretext);
                bottomText.setText(context.getString(R.string.wrongAnswerScoreText) + userSetting.getScore());
            }
        } else {
            TextView topText = v.findViewById(R.id.answerpopuptext);
            topText.setText(context.getString(R.string.wrongAnswerText));
            TextView bottomText = v.findViewById(R.id.answerpopupscoretext);
            bottomText.setText(context.getString(R.string.wrongAnswerScoreText) + userSetting.getScore());
        }

        new PopUpClass(view, R.layout.popup_quizanswerscore, context, view1 -> {
            Intent intent = new Intent(context, HomeActivity.class);
            ((QuizActivity) context).startActivity(intent);
        }).show(v);

    }
}
