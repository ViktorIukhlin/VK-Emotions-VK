package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.controller.DataProcessor;

public class SelectTypeDonation extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_donation);

        LinearLayout targetDonatLL = (LinearLayout) findViewById(R.id.targetDonatLL);
        LinearLayout regularDonatLL = (LinearLayout) findViewById(R.id.regularDonatLL);

        targetDonatLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                DataProcessor.getInstance().newDonation(1);
                Intent intent = new Intent(getActivity(), FormDonationActivity.class);
                startActivity(intent);
            }
        });

        regularDonatLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                DataProcessor.getInstance().newDonation(2);
                Intent intent = new Intent(getActivity(), FormDonationActivity.class);
                startActivity(intent);
            }
        });

    }



}