package com.example.big_homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private Button musicButton;
    private Button noteButton;
    private Button calculatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到布局中的按钮
        musicButton = findViewById(R.id.music_button);
        noteButton = findViewById(R.id.note_button);
        calculatorButton = findViewById(R.id.calculator_button);


        // 为音乐按钮添加点击事件
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到音乐播放器界面(MusicActivity)
                startActivity(new Intent(MainActivity.this, MusicPlayerActivity.class));
            }
        });

        // 为记事本按钮添加点击事件
        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到记事本界面(NoteActivity)
                startActivity(new Intent(MainActivity.this, NoteListActivity.class));
            }
        });

        // 为计算器按钮添加点击事件
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到计算器界面(CalculatorActivity)
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
            }
        });
    }
}
