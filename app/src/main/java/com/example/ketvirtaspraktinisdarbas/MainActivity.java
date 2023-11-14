package com.example.ketvirtaspraktinisdarbas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int DELETE_NOTE_REQUEST = 2;

    private ArrayList<String> notes;
    private ArrayAdapter<String> adapter;
    private int selectedNotePosition = -1;

    private FloatingActionButton fabAddNote;
    private FloatingActionButton fabDeleteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = loadNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Set context menu for long-click on list items
        registerForContextMenu(listView);

        TextView hintTextView = findViewById(R.id.hintTextView);
        hintTextView.setVisibility(View.VISIBLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteButton(position);
                hideDeleteHint();
            }
        });

        fabAddNote = findViewById(R.id.fabAddNote);
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(AddNoteActivity.createIntent(MainActivity.this), ADD_NOTE_REQUEST);
            }
        });

        fabDeleteNote = findViewById(R.id.fabDeleteNote);
        fabDeleteNote.setVisibility(View.GONE); // Initially hide delete button
        fabDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedNotePosition != -1) {
                    deleteNote();
                } else {
                    Toast.makeText(MainActivity.this, "No note selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_NOTE_REQUEST) {
                if (data != null && data.hasExtra("note")) {
                    String note = data.getStringExtra("note");
                    addNote(note);
                }
            } else if (requestCode == DELETE_NOTE_REQUEST) {
                if (data != null && data.hasExtra("position")) {
                    int position = data.getIntExtra("position", -1);
                    if (position != -1) {
                        deleteNote(position);
                    }
                }
            }
        }
    }

    private void showDeleteButton(int position) {
        selectedNotePosition = position;
        fabAddNote.setVisibility(View.GONE);
        fabDeleteNote.setVisibility(View.VISIBLE);
    }

    private void hideDeleteButton() {
        selectedNotePosition = -1;
        fabAddNote.setVisibility(View.VISIBLE);
        fabDeleteNote.setVisibility(View.GONE);
    }

    private void deleteNote() {
        if (selectedNotePosition != -1) {
            deleteNote(selectedNotePosition);
            hideDeleteButton(); // Hide delete button after deletion
        } else {
            Toast.makeText(MainActivity.this, "No note selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteNote(int position) {
        notes.remove(position);
        adapter.notifyDataSetChanged();
        saveNotes();
    }

    private void hideDeleteHint() {
        TextView hintTextView = findViewById(R.id.hintTextView);
        hintTextView.setVisibility(View.GONE);
    }

    private ArrayList<String> loadNotes() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Set<String> defaultSet = new HashSet<>();
        return new ArrayList<>(preferences.getStringSet("notes", defaultSet));
    }

    private void saveNotes() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("notes", new HashSet<>(notes));
        editor.apply();
    }

    private void addNote(String note) {
        notes.add(note);
        adapter.notifyDataSetChanged();
        saveNotes();
    }
}
