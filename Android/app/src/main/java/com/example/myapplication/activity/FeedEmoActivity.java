package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.utils.MockData;
import com.example.myapplication.utils.PostsAdapter;
import com.example.myapplication.utils.VkClusterItem;

import java.util.ArrayList;

public class FeedEmoActivity extends BaseActivity {

    private ListView postsLV;
    private String emo;
    private ArrayList<VkClusterItem> localItems;
    private ImageView mapIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_emo);
        emo = getIntent().getExtras().getString("emo");
        mapIV = (ImageView) findViewById(R.id.mapIV);
        mapIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("emo",emo);
                startActivity(intent);
            }
        });


        Log.d("myLogs","category = " + emo);
        Log.d("myLogs","MockData.getItems().size = " + MockData.getItems().size());
        postsLV = findViewById(R.id.postsLV);

        localItems = new ArrayList<VkClusterItem>();
        for(VkClusterItem item: MockData.getItems()){
            Log.d("myLogs","item.getPhotoUrl() = " + item.getPhotoUrl());
            if(item.getTypeEmocion().equals(emo)){
                Log.d("myLogs","equals = " + emo);
                localItems.add(item);
            }
        }
        PostsAdapter postsAdapter = new PostsAdapter(getActivity(),localItems);


        postsLV.setAdapter(postsAdapter);
    }
}