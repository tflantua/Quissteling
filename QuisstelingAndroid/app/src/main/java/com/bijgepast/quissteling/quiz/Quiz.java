package com.bijgepast.quissteling.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bijgepast.quissteling.HomeActivity.HomeActivity;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.util.PopUpClass;
import com.bijgepast.quissteling.util.UserSetting;

import java.util.ArrayList;
import java.util.HashMap;

public class Quiz {
    private String locationId;
    private String questionId;
    private ArrayList<Location> locations;
    private ArrayList<String> answers;
    private View v;
    private PopUpClass popUpClass;
    private PopUpClass secondPopup;
    public static final String CORRECT_KEY = "answer";

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
        this.v = inflater.inflate(R.layout.popup_quizanswerscore, null);
        UserSetting userSetting = new UserSetting(context);

        if (answers.containsKey(answer)) {
            if (answers.get(answer)) {
                TextView topText = this.v.findViewById(R.id.answerpopuptext);
                topText.setText(context.getString(R.string.correctAnswerText));
                userSetting.addScore(100);
                TextView bottomText = this.v.findViewById(R.id.answerpopupscoretext);
                bottomText.setText(context.getString(R.string.correctAnswerScoreText) + userSetting.getScore());
                chanceAtFLPass(view, context);
            } else {
                TextView topText = this.v.findViewById(R.id.answerpopuptext);
                topText.setText(context.getString(R.string.wrongAnswerText));
                TextView bottomText = this.v.findViewById(R.id.answerpopupscoretext);
                bottomText.setText(context.getString(R.string.wrongAnswerScoreText) + userSetting.getScore());

                new PopUpClass(view, R.layout.popup_quizanswerscore, context, view1 -> {
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra(Quiz.CORRECT_KEY, false);
                    ((QuizActivity) context).startActivity(intent);
                    popUpClass.dismiss();
                    ((QuizActivity) context).finish();
                }).show(v, context);

            }
        } else {
            TextView topText = this.v.findViewById(R.id.answerpopuptext);
            topText.setText(context.getString(R.string.wrongAnswerText));
            TextView bottomText = this.v.findViewById(R.id.answerpopupscoretext);
            bottomText.setText(context.getString(R.string.wrongAnswerScoreText) + userSetting.getScore());
            new PopUpClass(view, R.layout.popup_quizanswerscore, context, view1 -> {
                Intent intent = new Intent(context, HomeActivity.class);
                boolean correct = answers.containsKey(answer);
                intent.putExtra(CORRECT_KEY, false);
                ((QuizActivity) context).startActivity(intent);
                popUpClass.dismiss();
                ((QuizActivity) context).finish();
            }).show(v, context);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void chanceAtFLPass(View view, Context context) {
        double chance = 0.5;
        if (Math.random() < chance) {
            UserSetting userSetting = new UserSetting(context);
            userSetting.setPrize5(true);
            this.popUpClass = new PopUpClass(view, R.layout.popup_fastlaneticket, context, view1 -> {

                secondPopup = new PopUpClass(view, R.layout.popup_quizanswerscore, context, v -> {
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra(CORRECT_KEY, true);
                    ((QuizActivity) context).startActivity(intent);
                    this.popUpClass.dismiss();
                    secondPopup.dismiss();
                    ((QuizActivity) context).finish();
                });
                secondPopup.show(context);

            });
            this.popUpClass.show(context);
        } else {
            new PopUpClass(view, R.layout.popup_quizanswerscore, context, view1 -> {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra(CORRECT_KEY, true);
                ((QuizActivity) context).startActivity(intent);
                ((QuizActivity) context).finish();
            }).show(v, context);
        }
    }
}
