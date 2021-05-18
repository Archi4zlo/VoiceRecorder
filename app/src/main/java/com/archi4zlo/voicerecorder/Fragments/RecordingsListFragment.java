package com.archi4zlo.voicerecorder.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.archi4zlo.voicerecorder.R;
import com.archi4zlo.voicerecorder.RecordRecyclerViewAdapter;
import com.archi4zlo.voicerecorder.onSelectedListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecordingsListFragment extends Fragment implements onSelectedListener{

    View view;

    private RecyclerView recyclerView;
    private List<File> fileList;

    private RecordRecyclerViewAdapter recAdapter;
    File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/VoiceRecorder");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_recordings, container, false);

        displayFiles();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(),"long press on the entry to delete",Toast.LENGTH_LONG).show();
    }

    void displayFiles() {
        recyclerView = view.findViewById(R.id.recycler_records);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        fileList = new ArrayList<>();
        fileList.addAll(findFile(path));
        recAdapter = new RecordRecyclerViewAdapter(getContext(), fileList, this);
        recyclerView.setAdapter(recAdapter);
    }

    public ArrayList<File> findFile(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for (File singleFile : files) {
            if (singleFile.getName().toLowerCase().endsWith(".amr")) {
                arrayList.add(singleFile);
            }
        }
        return arrayList;
    }

    @Override
    public void onSelected(File file) {
        Uri uri = FileProvider.getUriForFile(getContext(),getContext().getApplicationContext().getPackageName()+ ".provider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"audio/x-wav");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        getContext().startActivity(intent);
    }

    @Override
    public void onLongSelected(File file) {
        file.delete();
        displayFiles();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            displayFiles();
        }
    }

}
