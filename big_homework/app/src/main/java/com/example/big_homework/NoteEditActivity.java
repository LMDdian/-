package com.example.big_homework;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class NoteEditActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextContent;
    private Button buttonSaveNote;
    private String noteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editTextTitle = findViewById(R.id.et_note_title);
        editTextContent = findViewById(R.id.et_note_content);
        buttonSaveNote = findViewById(R.id.btn_save_note);

        // Retrieve note title if available
        noteTitle = getIntent().getStringExtra("noteTitle");
        if (!TextUtils.isEmpty(noteTitle)) {
            editTextTitle.setText(noteTitle);
        }

        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

        FileOutputStream fos = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            fos = openFileOutput(title + ".txt", Context.MODE_PRIVATE);
            outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(content);
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
            finish(); // Finish this activity and go back to NoteDirectoryActivity
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
