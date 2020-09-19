package com.example.myapplication.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controller.DataProcessor;

import org.w3c.dom.Text;

public class BaseActivity extends AppCompatActivity {
    private float percentProgress = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageView homeUpIV = findViewById(R.id.homeUpIV);
        if(homeUpIV != null){
            homeUpIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        showSnippet();
    }

    private void showSnippet() {
        ImageView photoIV = (ImageView) findViewById(R.id.photoIV);
        TextView nameDonationTV = (TextView) findViewById(R.id.nameDonationTV);
        final TextView authorAndDateTV = (TextView) findViewById(R.id.authorAndDateTV);
        final TextView currentAmountTV = (TextView) findViewById(R.id.currentAmountTV);
        final View progressView = (View) findViewById(R.id.progressView);
        final TextView postTextTV = (TextView) findViewById(R.id.postTextTV);


        Button helpBTN = (Button) findViewById(R.id.helpBTN);
        if(nameDonationTV == null){
            return;
        }
        progressView.animate().scaleX(0.01f).setDuration(1);
        progressView.setPivotX(0);
        if(helpBTN.getAlpha() > 0.5){
            helpBTN.setEnabled(true);
            percentProgress = DataProcessor.getInstance().getPercentProgress();
            Handler handler = new Handler(getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressView.animate().scaleX(percentProgress).setDuration(300);
                }
            },500);

            helpBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataProcessor.getInstance().setCurrentAmount(DataProcessor.getInstance().getCurrentAmount() + (float)DataProcessor.getInstance().getDonationAmount()/10);
                    Log.d("myLogs","getCurrentAmount = " +  DataProcessor.getInstance().getCurrentAmount());
                    currentAmountTV.setText("Собрано: " + DataProcessor.getInstance().getCurrentAmount() + "₽ из " +DataProcessor.getInstance().getDonationAmount());
                    percentProgress += 0.1;
                    DataProcessor.getInstance().setPercentProgress(percentProgress);
                    if(percentProgress >= 1){
                        percentProgress = 1;
                        currentAmountTV.setText("Сбор завершен!");
                    }
                    progressView.animate().scaleX(percentProgress).setDuration(100);
                }
            });
        }
        if(DataProcessor.getInstance().getDonationPhotoURL() != null){

            photoIV.setImageURI(DataProcessor.getInstance().getDonationPhotoURL());
        }
        if(DataProcessor.getInstance().getDonationName() != null){
            nameDonationTV.setText(DataProcessor.getInstance().getDonationName());
        }
        if(authorAndDateTV != null){
            if(DataProcessor.getInstance().getDonationDate() != null){
                authorAndDateTV.setText("Матвей Правосудов · Закончится: "+DataProcessor.getInstance().getDonationDate());
            }else{
                authorAndDateTV.setText("Матвей Правосудов · Регулярный сбор");
            }
        }

        if(DataProcessor.getInstance().getDonationAmount() != null){
            currentAmountTV.setText("Собрано: " + DataProcessor.getInstance().getCurrentAmount() + "₽ из " +DataProcessor.getInstance().getDonationAmount());
        }

        if(postTextTV != null){
            if(DataProcessor.getInstance().getPostText() != null){
                postTextTV.setText(DataProcessor.getInstance().getPostText());
            }
        }


    }

    public Activity getActivity(){
        return this;
    }


}
