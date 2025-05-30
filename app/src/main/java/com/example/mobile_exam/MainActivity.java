package com.example.mobile_exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Button btnConvert = findViewById(R.id.btnConvert);
        EditText etKg = findViewById(R.id.etKg);
        TextView tvLb = findViewById(R.id.tvLb);

        btnConvert.setOnClickListener(v -> {
            String s = etKg.getText().toString().trim();
            if (!s.isEmpty()) {
                double kg = Double.parseDouble(s);
                double lb = convertKgToLb(kg);
                tvLb.setText(String.format(Locale.getDefault(), "%.2f lb", lb));
            } else {
                Toast.makeText(this, "Vui lòng nhập số kg", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static double convertKgToLb(double kg) {
        return kg * 2.20462;
    }
}