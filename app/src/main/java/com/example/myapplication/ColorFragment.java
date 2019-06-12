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
public class ColorFragment extends Fragment implements wordAdapter.ListItemClickListner {


    public ColorFragment() {
        // Required empty public constructor
    }
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    View rootView;
    int state;
    AudioManager.OnAudioFocusChangeListener focus = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == audioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();

            } else if (focusChange == audioManager.AUDIOFOCUS_LOSS) {
                mediaPlayer.stop();
            } else if (focusChange == audioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == audioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
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
        audioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);//instantiate the audio manager
        wordAdapter();
        return  rootView;
    }

    public ArrayList<word> Colors()
    {

        ArrayList<word>words=new ArrayList<>();
        words.add(new word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new word("black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        return words;
    }
    public void wordAdapter()
    {
        wordAdapter items=new wordAdapter(getActivity(),Colors(),R.color.category_colors,this);
        RecyclerView recyclerView = rootView.findViewById(R.id.Recycle);
        recyclerView.setAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        releaseMediaPlayer();
        state=audioManager.requestAudioFocus(focus, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if(state==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            word k =Colors().get(clickedItemIndex);
            mediaPlayer = MediaPlayer.create(getActivity(), k.getmAudioResourceId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(Moncompletion);
        }
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
}
