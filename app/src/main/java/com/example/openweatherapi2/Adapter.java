package com.example.openweatherapi2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Holder> {

    Context c;
    ArrayList<Double> arrayList;
    int[] count;

    public Adapter(Context c, ArrayList<Double> number, int[] count) {
        this.c = c;
        this.arrayList = number;
        this.count = count;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(arrayList.get(position).toString() + " C");
        holder.countTV.setText("Temperature " + String.valueOf(count[position]));
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.relativeLayout.setBackgroundColor(color);

        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(c, R.anim.fade_scale_animation));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
