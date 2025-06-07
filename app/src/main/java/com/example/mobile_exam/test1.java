package com.example.mobile_exam;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class test1  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        TextView tvlucky = findViewById(R.id.tvlucky);

        int lucky = getIntent().getIntExtra("KEY_LUCKY", -1);

        if (lucky >= 0) {
            tvlucky.setText(String.valueOf(lucky));
        } else {
            tvlucky.setText("Không có dữ liệu.");
        }
    }
}
