package com.example.ketvirtaspraktinisdarbas;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {

    private final int selectedNotePosition = -1;

    private Spinner spinnerNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinnerNotes);

        ArrayList<String> notes = getIntent().getStringArrayListExtra("notes");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, notes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNotes.setAdapter(adapter);

        FloatingActionButton fabDeleteNote = findViewById(R.id.fabDeleteNote);
        fabDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote();
            }
        });
    }

    private void deleteNote() {
        Intent intent = new Intent();
        intent.putExtra("position", selectedNotePosition);
        setResult(RESULT_OK, intent);
        finish();
    }
}
