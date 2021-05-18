package com.archi4zlo.voicerecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class RecordRecyclerViewAdapter extends RecyclerView.Adapter<RecordViewHolder> {

    private Context context;
    private List<File> fileList;

    private RecordRecyclerViewAdapter recAdapter;
    private onSelectedListener listener;

    public RecordRecyclerViewAdapter(Context context, List<File> fileList, onSelectedListener listener) {
        this.listener = listener;
        this.context = context;
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecordViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        holder.tvName.setText(fileList.get(position).getName());
        holder.tvName.setSelected(true);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected(fileList.get(position));
            }
        });
        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongSelected(fileList.get(position));
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }
}
