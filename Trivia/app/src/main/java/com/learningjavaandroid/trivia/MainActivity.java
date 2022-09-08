package com.learningjavaandroid.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.snackbar.Snackbar;
import com.learningjavaandroid.trivia.data.AnswerListAsyncResponse;
import com.learningjavaandroid.trivia.data.Repository;
import com.learningjavaandroid.trivia.databinding.ActivityMainBinding;
import com.learningjavaandroid.trivia.model.Question;
import com.learningjavaandroid.trivia.model.Score;
import com.learningjavaandroid.trivia.util.Prefs;

import java.util.ArrayList;
import java.util.List;

//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public class MainActivity extends AppCompatActivity {
    private static final String HIGHEST_SCORE_ID = "highest_score_id";
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    List<Question> questionList;
    private int scoreCounter = 0;
    private Score score;
    private Prefs prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        score = new Score();
        prefs = new Prefs(MainActivity.this);

        // retrieve the last state
        currentQuestionIndex = prefs.getState();

        questionList = new Repository().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
//                Log.d("MAIN", "processFinished: " + questionArrayList);
                binding.questionTextView.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                binding.textViewOutOf.setText("Question : " + currentQuestionIndex + "/" + questionArrayList.size());

            }
        });

        /*
            multiple buttons so we make the class implement setonClickListener interface.
            This can be achieved by binding.buttonNext.setOnClickListener(this) -> hint ->
            make main.activity implement setOnClickListener;
        */
        binding.buttonNext.setOnClickListener(v -> {
            getToNextQuestion();
        });
        binding.buttonTrue.setOnClickListener(v -> {
            checkAnswer(true);
            updateQuestion();

        });
        binding.buttonFalse.setOnClickListener(v -> {
            checkAnswer(false);
            updateQuestion();
        });


    }

    @Override
    protected void onPause() {
        Log.d("Pause", "onPause: cscore" + score.getScore());
        prefs.saveHighestScore(score.getScore());
        binding.highestScore.setText("Highest Score : " + prefs.getHighestScore());
        prefs.setState(currentQuestionIndex);
        Log.d("Pause", "onPause: " + prefs.getHighestScore());
        super.onPause();

    }

    private void getToNextQuestion() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
        updateQuestion();
    }

    private void checkAnswer(boolean userChoseCorrect) {
        boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
        int snackMessage;
        if (answer == userChoseCorrect) {
            snackMessage = R.string.correct_answer;
            fadeAnimation();
//            Log.d("CURRENTSCORE", "checkAnswer: currentscore: " + scoreCounter);
            addPoints();

        } else {
            snackMessage = R.string.incorrect_answer;
            shakeAnimation();
            deductPoints();

        }

        Snackbar.make(binding.cardView, snackMessage, Snackbar.LENGTH_SHORT).show();
    }

    private void deductPoints() {
        scoreCounter = Math.max((scoreCounter - 50), 0);
        score.setScore(scoreCounter);
        Log.d("Deduction", "deductPoints: " +score.getScore());
        binding.currentScore.setText("Current Score : " + score.getScore());
    }

    private void addPoints() {
        scoreCounter = scoreCounter + 100;
        score.setScore(scoreCounter);
        Log.d("Score ", "addPoints: " + score.getScore());

        binding.currentScore.setText("Current Score : " + score.getScore());
    }


    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        binding.questionTextView.setText(question);
        updateCounter();
    }

    private void updateCounter() {
        binding.textViewOutOf.setText("Question : " + currentQuestionIndex + "/" + questionList.size());
    }

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_animation);
        binding.cardView.setAnimation(shake);
    }

    private void fadeAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.cardView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
                getToNextQuestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                binding.questionTextView.setTextColor(Color.GREEN);
            }
        });
    }




    //switch cases for multiple buttons one way to do Gradle 5.0 will get deprecated
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.button_next:
//
//                break;
//            case R.id.button_true:
//                break;
//            case R.id.button_false:
//                break;
//
//
//        }
//    }

}