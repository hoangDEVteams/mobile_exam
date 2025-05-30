package com.example.mobile_exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
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

    private TextToSpeech tts;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.FRENCH);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button btnBlack = findViewById(R.id.button);
        Button btnGreen = findViewById(R.id.button2);
        Button btnPurple = findViewById(R.id.button3);
        Button btnRed = findViewById(R.id.button4);
        Button btnYellow = findViewById(R.id.button5);

        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tiếng Pháp: Black = "noir"
                speakColor("noir");
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tiếng Pháp: Green = "vert"
                speakColor("vert");
            }
        });

        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tiếng Pháp: Purple = "violet"
                speakColor("violet");
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tiếng Pháp: Red = "rouge"
                speakColor("rouge");
            }
        });

        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tiếng Pháp: Yellow = "jaune"
                speakColor("jaune");
            }
        });
    }

    /**
     * Phát âm một chuỗi bằng TextToSpeech.
     * @param text Chuỗi tiếng Pháp cần nói (ví dụ: "noir", "vert", v.v).
     */
    private void speakColor(String text) {
        if (tts != null) {
            // QUEUE_FLUSH: xóa hết hàng đợi trước đó, rồi nói ngay từ đầu
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "ColorUtteranceId");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Giải phóng tài nguyên TTS để tránh leak
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}