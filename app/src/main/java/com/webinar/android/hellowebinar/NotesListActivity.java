package com.webinar.android.hellowebinar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.webinar.android.hellowebinar.data.Note;
import com.webinar.android.hellowebinar.data.NoteRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aktor on 08/09/16.
 */

public class NotesListActivity extends Activity {

    private static final String TAG = "NoteList";
    private ListView mListView;
    private NotesAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity_list);
        mListView = (ListView)findViewById(R.id.list);
        // creiamo dei dati stub
        NoteRepository repo = NoteRepository.getInstance();
        repo.fillRandomNotes(5);

        List<Note> allNotes = repo.getAllNotes();

        final Button addRandomNote = (Button)findViewById(R.id.add_random_note);
        addRandomNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRandomNote();
            }
        });
        // ---
        mAdapter = new NotesAdapter(this,allNotes);
        //mAdapter.swap(allNotes);
        mListView.setAdapter(/*allNotes <-> mListView */mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Note item = mAdapter.getItem(position);
                Log.d(TAG, "onItemClick: "+item.toString());
            }
        });
    }

    private void addRandomNote(){
        Note note = new Note("Nota dinamica","Nuova Nota",false,new Date());
        NoteRepository repo = NoteRepository.getInstance();
        repo.addNote(note);
        List<Note> allNotes = repo.getAllNotes();
        mAdapter.swap(allNotes);
    }

    private class NotesAdapter extends BaseAdapter {

        List<Note> mNotes;
        LayoutInflater mInflater;

        NotesAdapter(Context context, List<Note> notes){
            mNotes = new ArrayList<>();
            mNotes.addAll(notes);
            mInflater = LayoutInflater.from(context);
        }

        public void swap(List<Note> allNotes) {
            mNotes.clear();
            mNotes.addAll(allNotes);
            notifyDataSetChanged(); // <------
        }

        @Override
        public int getCount() {
            return mNotes.size();
        }

        @Override
        public Note getItem(int position) {
            return mNotes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View recycledView, ViewGroup parent) {
            //* prendere la nota a posizione position
            Note item = getItem(position);

            ViewHolder holder;
            //* creare la view note_item_view che la rappresenta
            if (recycledView == null) {
                recycledView = mInflater.inflate(R.layout.note_item_view, parent, false);
                holder = new ViewHolder(recycledView);
                recycledView.setTag(holder);
            } else {
                holder = (ViewHolder)recycledView.getTag();
            }

            holder.bind(item);
            //* collegare i dati a note_item_view

            return recycledView;
        }


    }

    private static class ViewHolder {

        private TextView titleView;
        private TextView contentView;

        private ViewHolder(View view){
            titleView =(TextView)view.findViewById(R.id.note_title);
            contentView =(TextView)view.findViewById(R.id.note_content);
        }

        public void bind(Note item) {
            titleView.setText(item.getTitle());
            contentView.setText(item.getContent());
        }
    }
}
