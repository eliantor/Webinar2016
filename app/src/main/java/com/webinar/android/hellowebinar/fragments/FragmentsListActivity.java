package com.webinar.android.hellowebinar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.webinar.android.hellowebinar.R;
import com.webinar.android.hellowebinar.data.Note;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by aktor on 13/09/16.
 */

public class FragmentsListActivity
        extends AppCompatActivity
    implements AdderFragment.OnAddNoteListener, ListFragment.NotesProvider {
    private static final String NOTES = "NOTES_STATE";
    private static final String RANDOMS = "RANDOMS";

    private ArrayList<Note> mNotes;
    private int mNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null){
            mNotes = new ArrayList<>();
        } else {
            mNotes = savedInstanceState.getParcelableArrayList(NOTES);
        }
        setContentView(R.layout.activity_fragmentlist);
        mNumber=42;
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        AdderFragment adder =(AdderFragment)
//                fragmentManager.findFragmentById(R.id.InputForm);
//


        Button changer =(Button) findViewById(R.id.replace_fragment);
        changer.setOnClickListener(
                new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFragment();
                }
            });
    }

    private void changeFragment(){
        ColorFragment color = new ColorFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
               .replace(R.id.hole_content,color,RANDOMS)
//                .add(0,color,"NOME")
//                .remove()
                .addToBackStack(null)
               .commit();
    }
//---->onactivitycreated


    @Override
    public void requestUpdate() {
        updateListUI();
    }

    private void updateListUI(){
        FragmentManager fm = getSupportFragmentManager();
        ListFragment listFragment=(ListFragment)
                fm.findFragmentById(R.id.NotesList);
        listFragment.showData(mNotes);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof AdderFragment){
            ((AdderFragment) fragment).setListener(
                    new AdderFragment.OnAddNoteListener() {
                        @Override
                        public void onAddNote(String title, String content) {
                            addNote(title,content);
                        }
            });
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(NOTES,mNotes);
    }


    private void addNote(String title,String content){
        Note note = new Note(title,content,false,new Date(System.currentTimeMillis()));
        mNotes.add(note);
        updateListUI();
    }


    @Override
    public void onAddNote(String title, String content) {
        addNote(title,content);
    }
}
