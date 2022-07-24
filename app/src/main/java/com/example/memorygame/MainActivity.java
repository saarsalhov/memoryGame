package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private View decorView;
    private boolean radioButtonChecked = false;
    String strChosenRadioButton = null;
    EditText nameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    decorView.setSystemUiVisibility(HideSystemBars());
                }
            }
        });
        nameInput = (EditText) findViewById(R.id.plain_text_input);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(HideSystemBars());

        }
    }

    private int HideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    public void onRadioButtonClicked(View view) {
        radioButtonChecked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.easy:
                if (radioButtonChecked) {
                    strChosenRadioButton = "easy";
                }
                break;
            case R.id.medium:
                if (radioButtonChecked) {
                    strChosenRadioButton = "medium";
                }
                break;
            case R.id.hard:
                if (radioButtonChecked) {
                    strChosenRadioButton = "hard";
                }
                break;

        }
    }

    public void onStartGameButtonClicked(View view) {
        Intent intent;
        switch (strChosenRadioButton) {
            case "easy":
                intent = new Intent(this, EasyActivity.class);
                intent.putExtra("radioChosen", strChosenRadioButton);
                intent.putExtra("name", nameInput.getText().toString());
                startActivity(intent);
                break;
            case "medium":
                intent = new Intent(this, MediumActivity.class);
                intent.putExtra("radioChosen", strChosenRadioButton);
                intent.putExtra("name", nameInput.getText().toString());
                startActivity(intent);
                break;
            case "hard":
                intent = new Intent(this, HardActivity.class);
                intent.putExtra("radioChosen", strChosenRadioButton);
                intent.putExtra("name", nameInput.getText().toString());
                startActivity(intent);
                break;
        }
    }
}