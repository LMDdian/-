package com.example.big_homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class NoteListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_EDIT_NOTE = 1;
    private ListView listViewNotes;
    private Button buttonAddNote;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        listViewNotes = findViewById(R.id.lv_notes);
        buttonAddNote = findViewById(R.id.btn_add_note);

        notes = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listViewNotes.setAdapter(adapter);

        loadNotes(); // Load notes from filesystem

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle click on note item
                String selectedNote = notes.get(position);
                // Start NoteEditActivity with selected note title
                Intent intent = new Intent(NoteListActivity.this, NoteEditActivity.class);
                intent.putExtra("noteTitle", selectedNote);
                startActivity(intent);
            }
        });

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start NoteEditActivity without passing any data
                Intent intent = new Intent(NoteListActivity.this, NoteEditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh note list when returning to this activity
        notes.clear();
        loadNotes();
        adapter.notifyDataSetChanged();
    }
    private void loadNotes() {
        File directory = getFilesDir();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        // Remove file extension from note title
                        String noteTitle = fileName.substring(0, fileName.length() - 4);
                        notes.add(noteTitle);
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}
