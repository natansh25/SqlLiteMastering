package com.example.natan.sqllitemastering;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.natan.sqllitemastering.Adapter.DbHelper;
import com.example.natan.sqllitemastering.Adapter.MyRecyclerView;
import com.example.natan.sqllitemastering.Database.NotesContract;
import com.example.natan.sqllitemastering.pojo.Notes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    MyRecyclerView mMyRecyclerView;

    private SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ArrayList<Notes> note = new ArrayList<>();


        DbHelper dbHelper = new DbHelper(this);
        mSQLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = getAllGuests();


        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMyRecyclerView = new MyRecyclerView(cursor, new MyRecyclerView.RecyclerViewClickListener() {
            @Override
            public void onClick(Notes notes) {

                Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
                i.putExtra("title", notes.getTitle());
                i.putExtra("note", notes.getNote());
                startActivityForResult(i, 2);

                //Toast.makeText(MainActivity.this, notes.getTitle(), Toast.LENGTH_SHORT).show();

            }
        });
        mRecyclerView.setAdapter(mMyRecyclerView);

        // on Swipe

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                long id = (long) viewHolder.itemView.getTag();

                removeGuest(id);


            }
        }).attachToRecyclerView(mRecyclerView);


        Intent intent = getIntent();
        if (intent.getParcelableExtra("Note") == null) {

            //Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        } else {
            Notes notes = intent.getParcelableExtra("Note");
            /*Toast.makeText(this, notes.getTitle(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, notes.getNote(), Toast.LENGTH_SHORT).show();*/

            String title = notes.getTitle();
            String Note = notes.getNote();
            addNotes(title, Note);
            mMyRecyclerView.swapCursor(getAllGuests());
            /*Notes notes1 = new Notes(title, Note);
            note.add(notes1);
            mMyRecyclerView.notifyDataSetChanged();*/


        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(i);
                finish();




                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private boolean removeGuest(long id) {

        return mSQLiteDatabase.delete(NotesContract.NotesEntry.TABLE_NAME, NotesContract.NotesEntry._ID + "=" + id, null) > 0;


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            Intent intent = getIntent();

            Notes notes = intent.getParcelableExtra("Note1");
            Toast.makeText(this, notes.getTitle(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, notes.getNote(), Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private Cursor getAllGuests() {
        return mSQLiteDatabase.query(
                NotesContract.NotesEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                NotesContract.NotesEntry._ID


        );


    }


    long addNotes(String title, String notes) {
        ContentValues cv = new ContentValues();
        cv.put(NotesContract.NotesEntry.COLUMN_NAME_TITLE, title);
        cv.put(NotesContract.NotesEntry.COLUMN_NAME_NOTES, notes);
        return mSQLiteDatabase.insert(NotesContract.NotesEntry.TABLE_NAME, null, cv);


    }


}
