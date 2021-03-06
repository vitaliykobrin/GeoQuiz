package com.example.user.geoquiz.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.geoquiz.R;
import com.example.user.geoquiz.model.Quiz;
import com.example.user.geoquiz.model.Result;
import com.google.gson.Gson;

public class StartQuizActivity extends AppCompatActivity implements View.OnClickListener {

    private Quiz quiz;

    private TextView quizNameText;
    private ImageView image;
    private TextView quizDescriptionText;
    private TextView quizAttemptsText;
    private TextView quizBestResultText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        quiz = (Quiz) getIntent().getSerializableExtra(Quiz.class.getSimpleName());
        loadResult();

        initViews();
        setListeners();
        prepareQuizInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start: {
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra(Quiz.class.getSimpleName(), quiz);
                startActivity(intent);
            }
            case R.id.btn_reset: {
                resetResults();
            }
        }
    }

    private void initViews() {
        quizNameText = (TextView) findViewById(R.id.text_quiz_name);
        image = (ImageView) findViewById(R.id.img_main);
        quizDescriptionText = (TextView) findViewById(R.id.text_quiz_description);
        quizAttemptsText = (TextView) findViewById(R.id.text_quiz_attempts);
        quizBestResultText = (TextView) findViewById(R.id.text_best_result);

    }

    private void setListeners() {
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);
    }

    private void prepareQuizInfo() {
        quizNameText.setText(quiz.getName());
        image.setImageResource(quiz.getMainImage());
        quizDescriptionText.setText(quiz.getDescription());
        quizAttemptsText.setText(Integer.toString(quiz.getAttempts()));
        quizBestResultText.setText(Integer.toString(quiz.getBestResult()));
    }

    private void resetResults() {
        quiz.setAttempts(0);
        quiz.setBestResult(0);

        Result resultObject = new Result(quiz.getAttempts(), quiz.getBestResult());
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(quiz.getName(), new Gson().toJson(resultObject));
        editor.commit();
    }

    private void loadResult() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String resultString = sharedPref.getString(quiz.getName(), "");
        Result result = new Gson().fromJson(resultString, Result.class);

        if (result != null) {
            quiz.setAttempts(result.getAttempts());
            quiz.setBestResult(result.getBestResult());
        }
    }
}
