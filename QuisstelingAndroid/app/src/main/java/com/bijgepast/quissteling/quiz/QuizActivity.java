package com.bijgepast.quissteling.quiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bijgepast.quissteling.InitQuestion;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.databinding.ActivityQuizBinding;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_QUIZ_ID = "QuizId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQuizBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        String id = getIntent().getExtras().getString(EXTRA_QUIZ_ID);
        Quiz quiz = InitQuestion.add();
        quiz.setId(id);
        binding.setData(quiz);
    }
}