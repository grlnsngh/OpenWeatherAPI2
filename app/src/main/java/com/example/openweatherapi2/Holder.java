package com.example.openweatherapi2;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    TextView textView, countTV;

    RelativeLayout relativeLayout;

    public Holder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv);
        countTV = itemView.findViewById(R.id.count);
        relativeLayout = itemView.findViewById(R.id.row);
    }


}
