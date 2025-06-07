package com.example.mobile_exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class challenge1_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge1_2);

        TextView tvLabel    = findViewById(R.id.textView8);
        TextView tvFinal    = findViewById(R.id.textView10);
        Button  btnReplay   = findViewById(R.id.btn_playAgain);
        Button  btnExit     = findViewById(R.id.button8);

        int finalScore = getIntent().getIntExtra("final_score", 0);
        tvFinal.setText(String.valueOf(finalScore));

        btnReplay.setOnClickListener(v -> {
            Intent it = new Intent(this, challenge1_1.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
            finish();
        });

        btnExit.setOnClickListener(v -> finishAffinity());
    }
}
