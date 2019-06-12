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
public class NumbersFragment extends Fragment implements wordAdapter.ListItemClickListner{

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    int state;
    public NumbersFragment() {
        // Required empty public constructor
    }
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
    MediaPlayer.OnCompletionListener Moncompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_x,container,false);
        audioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        wordAdapter item = new wordAdapter(getActivity(), Numbers(), R.color.category_numbers,this);
        RecyclerView recyclerView =rootView.findViewById(R.id.Recycle);
        recyclerView.setAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    public ArrayList<word> Numbers() {
        ArrayList<word> words = new ArrayList<>();
        words.add(new word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        return words;
    }

    private void releaseMediaPlayer() {
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
    public void onListItemClick(int clickedItemIndex) {
        releaseMediaPlayer();
        state=audioManager.requestAudioFocus(focus,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if(state==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            word k =Numbers().get(clickedItemIndex);
            mediaPlayer = MediaPlayer.create(getActivity(), k.getmAudioResourceId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(Moncompletion);
        }
    }
}
