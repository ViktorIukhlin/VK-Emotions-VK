package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.controller.DataProcessor;

public class FormDonationActivity extends BaseActivity {
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private ImageView photoIV;
    private RelativeLayout uploadPhotoRL;
    private CardView photoCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_donation);

        uploadPhotoRL = (RelativeLayout) findViewById(R.id.uploadPhotoRL);
        EditText nameDonationET = (EditText) findViewById(R.id.nameDonationET);
        EditText amountET = (EditText) findViewById(R.id.amountET);
        EditText targetET = (EditText) findViewById(R.id.targetET);
        EditText descriptionET = (EditText) findViewById(R.id.descriptionET);
        Button purseBTN = (Button) findViewById(R.id.purseBTN);
        Button nextBTN = (Button) findViewById(R.id.nextBTN);

        photoCV = (CardView) findViewById(R.id.photoCV);
        photoIV  = (ImageView) findViewById(R.id.photoIV);

        photoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        uploadPhotoRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        nameDonationET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                DataProcessor.getInstance().setDonationName(s.toString());
            }
        });
        amountET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                try{
                    DataProcessor.getInstance().setDonationAmount(Integer.parseInt(s.toString()));
                }catch (Exception e){

                }

            }
        });

        targetET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                DataProcessor.getInstance().setDonationTarget(s.toString());
            }
        });

        descriptionET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                DataProcessor.getInstance().setDonationDescription(s.toString());
            }
        });

        purseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"По задания здесь заглушка!", Toast.LENGTH_LONG).show();
            }
        });

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataProcessor.getInstance().getDonationAmount() == null || DataProcessor.getInstance().getDonationDescription() == null || DataProcessor.getInstance().getDonationName() == null || DataProcessor.getInstance().getDonationPhotoURL() == null || DataProcessor.getInstance().getDonationTarget() == null){
                    Toast.makeText(getApplicationContext(),"Пожалуйста введите все данные и мы двинемся дальше", Toast.LENGTH_LONG).show();
                    return;
                }
                if(DataProcessor.getInstance().getDonationAmount() == 0 || DataProcessor.getInstance().getDonationDescription().isEmpty() || DataProcessor.getInstance().getDonationName().isEmpty() || DataProcessor.getInstance().getDonationPhotoURL().toString().isEmpty() || DataProcessor.getInstance().getDonationTarget().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Пожалуйста введите все данные и мы двинемся дальше", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = null;
                if(DataProcessor.getInstance().getDonationType() == 1){
                    intent = new Intent(getActivity(), SelectDateActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    intent = new Intent(getActivity(), CreatePostActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            photoIV.setImageURI(imageUri);
            photoCV.setVisibility(View.VISIBLE);
            uploadPhotoRL.setVisibility(View.GONE);
            DataProcessor.getInstance().setDonationPhotoURL(imageUri);
        }
    }
}