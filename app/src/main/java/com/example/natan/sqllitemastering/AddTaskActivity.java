package com.example.natan.sqllitemastering;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.natan.sqllitemastering.pojo.Notes;

public class AddTaskActivity extends AppCompatActivity {

    EditText edt_title, edt_note;
    Bundle data;
    int id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        edt_note = findViewById(R.id.editTextNote);
        edt_title = findViewById(R.id.editTextTitle);


        data = getIntent().getExtras();
        if (data != null) {
            String title = data.getString("title");
            String note = data.getString("note");
            id=data.getInt("id");
            Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
        /*    Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, note, Toast.LENGTH_SHORT).show();*/
            edt_title.setText(title);
            edt_note.setText(note);
        }


    }


    public void save() {
        Notes notes = new Notes(edt_title.getText().toString(), edt_note.getText().toString());
        Intent i = new Intent(AddTaskActivity.this, MainActivity.class);
        i.putExtra("Note", (Parcelable) notes);
        startActivity(i);
    }

    public void update() {

        Notes notes = new Notes(edt_title.getText().toString(), edt_note.getText().toString());
        Intent i = new Intent();
        i.putExtra("Note1", (Parcelable) notes);
        i.putExtra("id1",id);
        setResult(2, i);
        finish();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.done) {

            //Toast.makeText(this, "Saved Successfully !!", Toast.LENGTH_SHORT).show();
            if (data != null) {
                update();
            } else {
                save();
            }
            finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
