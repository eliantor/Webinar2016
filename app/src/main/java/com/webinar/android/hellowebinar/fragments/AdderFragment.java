package com.webinar.android.hellowebinar.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.webinar.android.hellowebinar.R;

/**
 * Created by aktor on 13/09/16.
 */

public class AdderFragment extends Fragment {

    public interface OnAddNoteListener {
        public void onAddNote(String title,String content);
    }

    private EditText mNoteTitle;
    private EditText mNoteContent;
    private Button mAdder;
    private OnAddNoteListener mListener;

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = mNoteTitle.getText().toString();
            String content = mNoteContent.getText().toString();
            mNoteTitle.setText(null);
            mNoteContent.setText(null);
            addNote(title,content);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        FragmentActivity activity = getActivity();
//        if (activity instanceof OnAddNoteListener){
//            setListener((OnAddNoteListener)activity);
//        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_adder,container,false);

        mNoteTitle = (EditText)view.findViewById(R.id.note_title);
        mNoteContent = (EditText)view.findViewById(R.id.note_contents);
        mAdder = (Button)view.findViewById(R.id.add_note_to_list);
        mAdder.setOnClickListener(listener);
        return view;
    }

    public void setListener(OnAddNoteListener listener){
        mListener = listener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        setListener(null);
    }

    private void addNote(String title, String content){
        //TODO
        /// SBAGLIATO
//        FragmentsListActivity activity =
//                (FragmentsListActivity) getActivity();
//         activity.addNewNote(title,content);

        if (mListener!=null){
            mListener.onAddNote(title,content);
        }
    }
}
