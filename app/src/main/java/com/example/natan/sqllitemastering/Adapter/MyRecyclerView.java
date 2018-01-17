package com.example.natan.sqllitemastering.Adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.natan.sqllitemastering.Database.NotesContract;
import com.example.natan.sqllitemastering.R;
import com.example.natan.sqllitemastering.pojo.Notes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by natan on 1/16/2018.
 */

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.MyViewHolder> {


    private RecyclerViewClickListener mListener;
    private Cursor mCursor;


    public interface RecyclerViewClickListener {

        //if we want to on click the item index value
        void onClick(View view, int position);

        //if we want the whole object to retrive the items
        //void onClick(Notes notes);
    }

    public MyRecyclerView(Cursor cursor, RecyclerViewClickListener listener) {

        mListener = listener;
        mCursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        /*Notes notes = mNotes.get(position);
        holder.txt_Title.setText(notes.getTitle());
        holder.txt_Notes.setText(notes.getNote());*/
        if (!mCursor.moveToPosition(position))
            return;

        String title = mCursor.getString(mCursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_NAME_TITLE));
        String note = mCursor.getString(mCursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_NAME_NOTES));
        long id = mCursor.getInt(mCursor.getColumnIndex(NotesContract.NotesEntry._ID));

        holder.txt_Title.setText(title);
        holder.txt_Notes.setText(note);
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_Title, txt_Notes;


        public MyViewHolder(View itemView) {
            super(itemView);

            txt_Title = itemView.findViewById(R.id.txt_title);
            txt_Notes = itemView.findViewById(R.id.txt_Notes);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //for index value on click
            mListener.onClick(v, getAdapterPosition());


            // for whole object to be passed
            /*int adapterPosition = getAdapterPosition();
            Notes notesClicked = mNotes.get(adapterPosition);

            mListener.onClick(notesClicked);*/
        }
    }


}
