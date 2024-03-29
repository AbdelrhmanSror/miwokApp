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
public class PhrasesFragment extends Fragment implements wordAdapter.ListItemClickListner {


    public PhrasesFragment() {
        // Required empty public constructor
    }
    MediaPlayer mediaPlayer;
    int state;
    AudioManager audioManager;
    View rootView;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.activity_x,container,false);
        audioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        wordAdapter();
        return rootView;
    }
    public ArrayList<word> Phrases()
    {
        // MAKING A LIST OF PHRASES

        ArrayList<word>words=new ArrayList<>();
        words.add(new word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        words.add(new word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        words.add(new word("Come here.","әnni'nem",R.raw.phrase_come_here));

        return words;
    }
    public void wordAdapter()
    {//INTILIZE THE ARRAY ADAPTER WITH CONTEXT AND THE LIST OF PHRASES
        wordAdapter items=new wordAdapter(getActivity(),Phrases(),R.color.category_phrases, this);
        //POINTNG THE ID OF PHRASES LISTview  so it can be accessed
        RecyclerView recyclerView = rootView.findViewById(R.id.Recycle);
        //passing the word adapter to list view
        recyclerView.setAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
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

    @Override
    public void onListItemClick(int ClickedItemIdex)
    {
        releaseMediaPlayer();
        state=audioManager.requestAudioFocus(focus,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if(state==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            word k =Phrases().get(ClickedItemIdex);
            mediaPlayer = MediaPlayer.create(getActivity(), k.getmAudioResourceId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(Moncompletion);
        }
    }


}
