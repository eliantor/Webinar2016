package com.webinar.android.hellowebinar.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webinar.android.hellowebinar.R;
import com.webinar.android.hellowebinar.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aktor on 13/09/16.
 */

public class ListFragment extends Fragment {

    public interface NotesProvider {
        public void requestUpdate();
    }

    private RecyclerView mListView;
    private NotesAdapter mAdapter;
    private NotesProvider mProvider;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof NotesProvider){
            mProvider = (NotesProvider)getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes_list, container, false);
        mListView = (RecyclerView)view.findViewById(R.id.items_list);
        LinearLayoutManager manager =
                new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mListView.setLayoutManager(manager);
        mAdapter = new NotesAdapter();
        mListView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mProvider != null){
            mProvider.requestUpdate();
        }

    }

    public void showData(ArrayList<Note> notes) {
        mAdapter.swap(notes);
    }


    private class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

        private List<Note> mNotes;

        NotesAdapter(){
            mNotes = new ArrayList<>();
        }

        public void swap(List<Note> notes){
            mNotes.clear();
            mNotes.addAll(notes);
            notifyDataSetChanged();
        }


        @Override
        public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new NotesViewHolder(inflater.inflate(R.layout.note_item_view,parent,false));
        }


        @Override
        public void onBindViewHolder(NotesViewHolder holder, int position) {
            holder.bind(mNotes.get(position));
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }


    private static class NotesViewHolder
            extends RecyclerView.ViewHolder{

        private TextView mTitle;
        private TextView mContent;

        public NotesViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.note_title);
            mContent = (TextView)itemView.findViewById(R.id.note_content);
        }

        private void bind(Note note){
            mTitle.setText(note.getTitle());
            mContent.setText(note.getContent());
        }

    }
}
