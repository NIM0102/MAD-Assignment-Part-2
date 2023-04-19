package com.example.demo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SneakerSize extends AppCompatActivity {

    private EditText footLengthEditText;
    private Button suggestButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sneaker_size);

        footLengthEditText = findViewById(R.id.foot_length_edit_text);
        suggestButton = findViewById(R.id.suggest_button);
        resultTextView = findViewById(R.id.result_text_view);

        suggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String footLengthString = footLengthEditText.getText().toString();
                double footLength = Double.parseDouble(footLengthString);
                int sneakerSize = suggestSneakerSize(footLength);
                String resultString = "Suggested sneaker size is EU " + sneakerSize;

                // Change color to red and set text to bold
                resultTextView.setTextColor(getResources().getColor(R.color.black));
                resultTextView.setTypeface(resultTextView.getTypeface(), Typeface.BOLD);

                resultTextView.setText(resultString);
            }
        });

    }

    public static int suggestSneakerSize(double footLength) {
        int[] sizes = {36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48};
        double[] sizeRange = {22.1, 22.8, 23.5, 24.1, 24.8, 25.5, 26.1, 26.8, 27.5, 28.1, 28.8, 29.5, 30.1};
        int suggestedSize = -1;
        for (int i = 0; i < sizeRange.length; i++) {
            if (footLength <= sizeRange[i]) {
                suggestedSize = sizes[i];
                break;
            }
        }
        if (suggestedSize == -1) {
            suggestedSize = sizes[sizes.length - 1];
        }
        return suggestedSize;
    }
}
