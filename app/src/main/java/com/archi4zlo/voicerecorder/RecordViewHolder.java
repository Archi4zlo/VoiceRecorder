package com.archi4zlo.voicerecorder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public LinearLayout container;

    public RecordViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.txtName);
        container = itemView.findViewById(R.id.containers);

    }
}
