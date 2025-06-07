package com.example.mobile_exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class test extends AppCompatActivity {
    private EditText edtName;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Button btnlucky = findViewById(R.id.btn_lucky);
        edtName = findViewById(R.id.edtName);

        btnlucky.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(test.this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
                return;
            }

            int luckyNumber = generateLuckyNumber(name);

            Intent intent = new Intent(test.this, test1.class);
            intent.putExtra("KEY_LUCKY", luckyNumber);
            startActivity(intent);
        });
    }

    public static int generateLuckyNumber(String name) {
        int hash = Math.abs(name.hashCode());
        return (hash % 1000) + 1;
    }
}
