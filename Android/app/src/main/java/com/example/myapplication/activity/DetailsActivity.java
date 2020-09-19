package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.controller.DataProcessor;

public class DetailsActivity extends AppCompatActivity {
    private float percentProgress = 0;
    private Button helpBTN;
    private LinearLayout greenProgressLL;
    private TextView whiteProgressTV;
    private TextView greenProgressTV;
    private float widthProgress = 0;
    private TextView successTV;
    private TextView currentAmountTV;
    private View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ImageView homeUpIV = findViewById(R.id.homeUpIV);
        if(homeUpIV != null){
            homeUpIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        ImageView photoIV = (ImageView) findViewById(R.id.photoIV);
        TextView dateTV = (TextView) findViewById(R.id.dateTV);
        TextView date2TV = (TextView) findViewById(R.id.date2TV);
        TextView descriptionTV = (TextView) findViewById(R.id.descriptionTV);
        TextView allProgressTV = (TextView) findViewById(R.id.allProgressTV);
        allProgressTV.setText(DataProcessor.getInstance().getDonationAmount() + " ₽");
        descriptionTV.setText(DataProcessor.getInstance().getDonationDescription());
        if(DataProcessor.getInstance().getDonationDate() != null){
            dateTV.setText("Сбор заканчивается " + DataProcessor.getInstance().getDonationDate());
            date2TV.setText("Нужно собрать до  " + DataProcessor.getInstance().getDonationDate());
        }else{
            if(DataProcessor.getInstance().getDonationType() == 1){
                dateTV.setText("Без срока");
                date2TV.setText("Пока не наберется сумма");
            }else{
                dateTV.setText("Помощь нужна каждый день");
                date2TV.setText("Нужно собрать в сентябре");
            }

        }

        percentProgress = DataProcessor.getInstance().getPercentProgress();

        if(DataProcessor.getInstance().getDonationPhotoURL() != null){
            photoIV.setImageURI(DataProcessor.getInstance().getDonationPhotoURL());
        }
        helpBTN = (Button) findViewById(R.id.helpBTN);
        helpBTN.setAlpha(1);
        showSnippet();
        initGreenProgress();
    }
    private void showSnippet() {
        ImageView photoIV = (ImageView) findViewById(R.id.photoIV);
        TextView nameDonationTV = (TextView) findViewById(R.id.nameDonationTV);
        final TextView authorAndDateTV = (TextView) findViewById(R.id.authorAndDateTV);
        currentAmountTV = (TextView) findViewById(R.id.currentAmountTV);
        progressView = (View) findViewById(R.id.progressView);
        final TextView postTextTV = (TextView) findViewById(R.id.postTextTV);
        initGreenProgress();

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
                    percentProgress += 0.1;
                    DataProcessor.getInstance().setCurrentAmount(DataProcessor.getInstance().getCurrentAmount() + (float)DataProcessor.getInstance().getDonationAmount()/10);
                    Log.d("myLogs","getCurrentAmount = " +  DataProcessor.getInstance().getCurrentAmount());
                    currentAmountTV.setText("Собрано: " + DataProcessor.getInstance().getCurrentAmount() + "₽ из " +DataProcessor.getInstance().getDonationAmount());
                    DataProcessor.getInstance().setPercentProgress(percentProgress);
                    if(percentProgress >= 1){
                        percentProgress = 1;
                        currentAmountTV.setText("Сбор завершен!");
                    }
                    progressView.animate().scaleX(percentProgress).setDuration(100);

                    Log.d("myLogs","getCurrentAmount = " +  DataProcessor.getInstance().getCurrentAmount());


                    DataProcessor.getInstance().setPercentProgress(percentProgress);
                    updateProgress();

                }
            });
            greenProgressLL.post(new Runnable() {
                @Override
                public void run() {
                    updateProgress();
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

    public void updateProgress(){
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) greenProgressLL.getLayoutParams();
        lp.width = Math.round(widthProgress*percentProgress);
        greenProgressLL.setLayoutParams(lp);
        if(DataProcessor.getInstance().getPercentProgress() <0.5){
            greenProgressTV.setVisibility(View.VISIBLE);
            whiteProgressTV.setVisibility(View.GONE);
        }else{
            greenProgressTV.setVisibility(View.GONE);
            whiteProgressTV.setVisibility(View.VISIBLE);
        }
        if(percentProgress >= 1){
            percentProgress = 1;
            whiteProgressTV.setVisibility(View.GONE);
            successTV.setVisibility(View.VISIBLE);
            successTV.setText(DataProcessor.getInstance().getDonationAmount() + " ₽ собраны!");
        }
        whiteProgressTV.setText(DataProcessor.getInstance().getCurrentAmount() + "₽");
        greenProgressTV.setText(DataProcessor.getInstance().getCurrentAmount() + "₽");
    }



    private void initGreenProgress() {
        greenProgressLL = (LinearLayout) findViewById(R.id.greenProgressLL);
        whiteProgressTV = (TextView) findViewById(R.id.whiteProgressTV);
        greenProgressTV = (TextView) findViewById(R.id.greenProgressTV);
        successTV = (TextView) findViewById(R.id.successTV);
        greenProgressLL.post(new Runnable() {
            @Override
            public void run() {
                widthProgress = greenProgressLL.getWidth();
//
                Log.d("myLogs","greenProgressLL = " + greenProgressLL.getWidth());
            }
        });

//        greenProgressLL.animate().scaleX(0.01f).setDuration(1);
//        greenProgressLL.setPivotX(0);
        helpBTN.setEnabled(true);
        percentProgress = DataProcessor.getInstance().getPercentProgress();
        Handler handler = new Handler(getMainLooper());
        if(DataProcessor.getInstance().getPercentProgress() <0.5){
            greenProgressTV.setVisibility(View.VISIBLE);
            whiteProgressTV.setVisibility(View.GONE);
        }else{
            greenProgressTV.setVisibility(View.GONE);
            whiteProgressTV.setVisibility(View.VISIBLE);
        }
        greenProgressLL.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) greenProgressLL.getLayoutParams();
                lp.width = Math.round(widthProgress*percentProgress);
                greenProgressLL.setLayoutParams(lp);
            }
        });

    }
}