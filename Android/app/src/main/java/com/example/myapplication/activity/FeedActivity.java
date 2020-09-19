package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.utils.MockData;
import com.example.myapplication.utils.PostsAdapter;
import com.example.myapplication.utils.VkClusterItem;

import java.util.ArrayList;

public class FeedActivity extends BaseActivity {

    private ListView postsLV;
    private String category;
    private ArrayList<VkClusterItem> localItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        category = getIntent().getExtras().getString("category");
        Log.d("myLogs","category = " + category);
        Log.d("myLogs","MockData.getItems().size = " + MockData.getItems().size());
        postsLV = findViewById(R.id.postsLV);

        localItems = new ArrayList<VkClusterItem>();
        for(VkClusterItem item: MockData.getItems()){
            Log.d("myLogs","item.getPhotoUrl() = " + item.getPhotoUrl());
            if(item.getPhotoUrl().contains("/"+category)){
                Log.d("myLogs","contains = " + category);
                localItems.add(item);
            }
        }
        PostsAdapter postsAdapter = new PostsAdapter(getActivity(),localItems);
        postsLV.setAdapter(postsAdapter);
    }
}