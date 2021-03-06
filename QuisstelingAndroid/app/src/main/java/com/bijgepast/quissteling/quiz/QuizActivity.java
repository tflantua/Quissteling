package com.bijgepast.quissteling.quiz;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bijgepast.quissteling.util.InitQuestion;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.util.UserSetting;
import com.bijgepast.quissteling.databinding.ActivityQuizBinding;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_QUIZ_ID = "QuizId";
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button backButton;
    private Button homeButtonAfterQuiz;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQuizBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        Quiz quiz = InitQuestion.quiz;
        binding.setData(quiz);
        binding.setScore(new UserSetting(this));

        //init button
        this.answer1 = findViewById(R.id.answer1);
        this.answer2 = findViewById(R.id.answer2);
        this.answer3 = findViewById(R.id.answer3);
        this.answer4 = findViewById(R.id.answer4);
        this.backButton = findViewById(R.id.goBack);

        //Onclick listeners
        this.answer1.setOnClickListener(v -> {
            quiz.checkAnswer(this.answer1.getText().toString(), v, this);
        });
        this.answer2.setOnClickListener(v -> {
            quiz.checkAnswer(this.answer2.getText().toString(), v, this);
        });
        this.answer3.setOnClickListener(v -> {
            quiz.checkAnswer(this.answer3.getText().toString(), v, this);
        });
        this.answer4.setOnClickListener(v -> {
            quiz.checkAnswer(this.answer4.getText().toString(), v, this);
        });
        this.backButton.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    public void closeQuizActivity() {
        this.finish();
    }
}