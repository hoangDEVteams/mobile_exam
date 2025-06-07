package com.example.mobile_exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;

public class challenge1 extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.challenge1);
        Button btn_addition = findViewById(R.id.btn_addition);
        Button btn_subtraction = findViewById(R.id.btn_subtraction);
        Button btn_multiplication = findViewById(R.id.btn_multiplication);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String op;
                if (v.getId() == R.id.btn_addition) op = "addition";
                else if (v.getId() == R.id.btn_subtraction) op = "subtraction";
                else op = "multiplication";

                Intent intent = new Intent(challenge1.this, challenge1_1.class);
                intent.putExtra("Operation", op);
                startActivity(intent);
            }
        };

        btn_addition.setOnClickListener(listener);
        btn_subtraction.setOnClickListener(listener);
        btn_multiplication.setOnClickListener(listener);
    }
}
