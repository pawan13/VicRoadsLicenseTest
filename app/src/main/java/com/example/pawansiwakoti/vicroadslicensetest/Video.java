package com.example.pawansiwakoti.vicroadslicensetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Vector;

public class Video extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<YouTubeVideos> youTubeVideos = new Vector<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/KZhvx4Uu4bk\"" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/50Mwks7ipXs\"" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/A8bm1ZNO5OU\"" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/o7Abr0NyL3o\"" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/O5rJQaGptLk\"" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/O5LxiptK_DA\"" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"P91QaMCGMhQ" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"5gjzDh6Q9Lc" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"Q4arM2W1Ays" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"Q-rssOso28I" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"_lZ3IScL4IE" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"BpXw2BU1Bcc" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"6ySynXZFygI" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"LugZcrbI9DA" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"f1lvDHJPk40" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"Ri-qBcha1Uk" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"-AwbA9wo9dk" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"VuXIB2GYmVc" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"hQ7otk-AYCM" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));
        youTubeVideos.add(new YouTubeVideos("<iframe width=\"100%\" src=\"https://www.youtube.com/embed/\"SRI_isvBV3I" +
                "frameBorder=\"0\" allowFullscreen></iframe>"));


        VideoAdapter videoAdapter = new VideoAdapter(youTubeVideos);
        recyclerView.setAdapter(videoAdapter);
    }
}
