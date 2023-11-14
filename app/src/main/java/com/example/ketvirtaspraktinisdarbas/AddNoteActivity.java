package com.example.ketvirtaspraktinisdarbas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editNoteName, editNoteContent;

    public static Intent createIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editNoteName = findViewById(R.id.editNoteName);
        editNoteContent = findViewById(R.id.editNoteContent);

        Button btnSaveNote = findViewById(R.id.btnSaveNote);
        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String name = editNoteName.getText().toString().trim();
        String content = editNoteContent.getText().toString().trim();

        if (!name.isEmpty() && !content.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("note", name + ": " + content);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "Note name and content cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}
