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

    EditText edt_title,edt_note;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        edt_note=findViewById(R.id.editTextNote);
        edt_title=findViewById(R.id.editTextTitle);
    }


    public void save()
    {
        Notes notes=new Notes(edt_title.getText().toString(),edt_note.getText().toString());
        Intent i=new Intent(AddTaskActivity.this,MainActivity.class);
        i.putExtra("Note", (Parcelable) notes);
        startActivity(i);
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
            save();
            finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
