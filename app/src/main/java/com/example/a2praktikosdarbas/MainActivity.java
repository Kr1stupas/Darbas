package com.example.a2praktikosdarbas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2praktikosdarbas.R;
import com.example.a2praktikosdarbas.WordCounter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Spinner countTypeSpinner;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        countTypeSpinner = findViewById(R.id.countTypeSpinner);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.count_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countTypeSpinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCount();
            }
        });
    }

    private void calculateCount() {
        String inputText = editText.getText().toString().trim();

        if (inputText.isEmpty()) {
            Toast.makeText(this, R.string.toast_empty_field, Toast.LENGTH_SHORT).show();
            resultTextView.setText("");
        } else {
            String selectedCountType = countTypeSpinner.getSelectedItem().toString();
            int count = 0;

            if (selectedCountType.equals("Žodžiai")) {
                count = WordCounter.countWords(inputText);
            } else if (selectedCountType.equals("Skyrybos ženklai")) {
                count = WordCounter.countPunctuation(inputText);
            }else if (selectedCountType.equals("Skyrybos ženklai ir tarpai")) {
                count = WordCounter.countPunctuationAndSpaces(inputText);
            }

            resultTextView.setText(getString(R.string.text_view_label) + count);
        }
    }
}

