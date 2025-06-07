package com.example.mobile_exam;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class challenge1_1 extends AppCompatActivity {
    private TextView tvScore, tvLifeCount, tvTimer;
    private TextView tvQuestion;
    private EditText etAnswer;
    private Button btnOk, btnNext;

    // Game state
    private int score = 0;
    private int lives = 3;
    private long timeLeftMs = 60_000;
    private CountDownTimer timer;
    private String operation;
    private int operand1, operand2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge1_1);

        // 1) Bind tất cả view theo ID từ XML
        tvScore     = findViewById(R.id.tvScore);
        tvLifeCount = findViewById(R.id.tvHeart);
        tvTimer     = findViewById(R.id.tvTimer);
        tvQuestion  = findViewById(R.id.textView9);

        etAnswer    = findViewById(R.id.editTextText2);
        btnOk       = findViewById(R.id.btn_Ok);
        btnNext     = findViewById(R.id.btn_nextQuestion);

        operation = getIntent().getStringExtra("Operation");

        tvScore.setText(String.valueOf(score));
        tvLifeCount.setText(String.valueOf(lives));
        tvTimer.setText((timeLeftMs/1000) + "s");
        btnNext.setEnabled(false);

        nextQuestion();
        startTimer();

        btnOk.setOnClickListener(v -> {
            String in = etAnswer.getText().toString().trim();
            if (in.isEmpty()) return;

            int userAns = Integer.parseInt(in);
            int correct;
            switch (operation) {
                case "subtraction":    correct = operand1 - operand2; break;
                case "multiplication": correct = operand1 * operand2; break;
                default:               correct = operand1 + operand2; break;
            }

            if (userAns == correct) {
                score++;
                tvScore.setText(String.valueOf(score));
            } else {
                lives--;
                tvLifeCount.setText(String.valueOf(lives));
                if (lives == 0) { gameOver(); return; }
            }

            btnOk.setEnabled(false);
            btnNext.setEnabled(true);
        });

        btnNext.setOnClickListener(v -> {
            btnOk.setEnabled(true);
            nextQuestion();
        });
    }

    private void nextQuestion() {
        // random 2 số
        Random rnd = new Random();
        operand1 = rnd.nextInt(10) + 1;
        operand2 = rnd.nextInt(10) + 1;

        String symbol = "+";
        if ("subtraction".equals(operation))      symbol = "-";
        else if ("multiplication".equals(operation)) symbol = "×";

        tvQuestion.setText(operand1 + " " + symbol + " " + operand2 + " = ?");
        etAnswer.setText("");
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeftMs, 1000) {
            @Override
            public void onTick(long ms) {
                timeLeftMs = ms;
                tvTimer.setText((ms/1000) + "s");
            }
            @Override
            public void onFinish() {
                gameOver();
            }
        }.start();
    }

    private void gameOver() {
        if (timer != null) timer.cancel();
        Intent it = new Intent(this, challenge1_2.class);
        it.putExtra("final_score", score);
        startActivity(it);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) timer.cancel();
    }
}
