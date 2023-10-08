package com.example.mipt_praktikos_darbas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Button buttonSpalva, buttonTekstas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTekstas = findViewById(R.id.button);
        buttonSpalva = findViewById(R.id.button1);
        buttonTekstas.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textView);
                tv.setText("Sveiki");
            }
        });
        buttonSpalva.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                TextView tv = (TextView) findViewById(R.id.textView);
                tv.setTextColor(color);
            }
        });
    }
}