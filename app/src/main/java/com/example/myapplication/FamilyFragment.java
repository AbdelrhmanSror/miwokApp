package com.example.myapplication;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment  {
    MediaPlayer mediaPlayer;
    int state;
    AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener focus=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {
                mediaPlayer.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mediaPlayer.pause();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {
                mediaPlayer.stop();
            }
        }
    };
    MediaPlayer.OnCompletionListener Moncompletion=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_x,container,false);
        audioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        wordAdapter items = new wordAdapter(getActivity(), Fmaily(), R.color.category_family,x);
        RecyclerView recyclerView =rootView.findViewById(R.id.Recycle);
        recyclerView.setAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }
    public ArrayList<word> Fmaily()
    {

        ArrayList<word>words=new ArrayList<>();
        words.add(new word("father","әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new word("son","angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        return words;
    }

wordAdapter.ListItemClickListner x=new wordAdapter.ListItemClickListner() {
    @Override
    public void onListItemClick(int clickedItemIndex) {
        releaseMediaPlayer();
        state=audioManager.requestAudioFocus(focus,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if(state==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            word k =Fmaily().get(clickedItemIndex);
            mediaPlayer = MediaPlayer.create(getActivity(), k.getmAudioResourceId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(Moncompletion);
        }

    }
};


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer()
    {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(focus);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
