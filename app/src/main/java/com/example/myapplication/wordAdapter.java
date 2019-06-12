package com.example.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class wordAdapter extends RecyclerView.Adapter<wordAdapter.ViewHolder> {
    ArrayList<word> data;
    Context c;
    int ColorId;
    final private ListItemClickListner mOnClickListner;


    public wordAdapter(Context c, ArrayList<word> data, int ColorId, ListItemClickListner listner) {
        this.data = data;
        this.c = c;
        mOnClickListner = listner;
        this.ColorId = ColorId;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image1;
        public TextView txt1;
        public TextView txt2;
        public RelativeLayout secondlayout;


        public ViewHolder(final View view) {
            super(view);
            image1 = view.findViewById(R.id.imageview);
            txt1 = view.findViewById(R.id.txt1);
            txt2 = view.findViewById(R.id.txt2);
            secondlayout = view.findViewById(R.id.secondlayout);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mOnClickListner.onListItemClick(getAdapterPosition());
                }
            });

        }

    }


    @NonNull
    @Override
    public wordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View DataViews = LayoutInflater.from(c).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder hold = new ViewHolder(DataViews);
        return hold;
    }


    @Override
    public void onBindViewHolder(@NonNull wordAdapter.ViewHolder viewHolder, int i) {
        word AssignData = data.get(i);
        ImageView image = viewHolder.image1;
        TextView txt1 = viewHolder.txt1;
        TextView txt2 = viewHolder.txt2;
        txt1.setText(AssignData.getMowTreanslation());
        txt2.setText(AssignData.getDefaultTreanslation());
        if (!(mOnClickListner instanceof PhrasesFragment)) {
            image.setImageResource(AssignData.getImageResourceId());
        } else {
            image.setVisibility(View.GONE);
        }
// taking resource id and find the color that id mapped to
        RelativeLayout secondLayout = viewHolder.secondlayout;
        //converting it to integer value
        int color = ContextCompat.getColor(c, ColorId);
        //setting the background to color
        secondLayout.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ListItemClickListner {
        void onListItemClick(int clickedItemIndex);
    }


}


