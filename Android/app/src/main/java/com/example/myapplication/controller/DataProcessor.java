package com.example.myapplication.controller;

import android.net.Uri;

public class DataProcessor {
    private static final DataProcessor INSTANCE = new DataProcessor();
    private DataProcessor(){}

    public static DataProcessor getInstance(){
        return INSTANCE;
    }
    private Integer donationType= null; // required 1 - целевые донаты ; 2 - регулярные донаты
    private Uri donationPhotoURL = null; // required
    private String donationName = null; // required
    private Integer donationAmount = null; // required
    private float currentAmount = 0; // required
    private String donationTarget = null; // required
    private String donationDescription = null; // required
    private String postText = null; // required
    private float percentProgress = 0;

    public float getPercentProgress() {
        return percentProgress;
    }

    public void setPercentProgress(float percentProgress) {
        this.percentProgress = percentProgress;
    }

    public void setCurrentAmount(float currentAmount) {
        if(currentAmount>=donationAmount){
            currentAmount = donationAmount;
        }
        this.currentAmount = currentAmount;
    }

    public float getCurrentAmount() {
        return currentAmount;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostText() {
        return postText;
    }

    public String donationDate = null; // нужно если donationType == 1

    public void newDonation(Integer mDonationType){
        clear();
        donationType = mDonationType;
    }

    public void setDonationAmount(Integer donationAmount) {
        this.donationAmount = donationAmount;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public void setDonationDescription(String donationDescription) {
        this.donationDescription = donationDescription;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public void setDonationPhotoURL(Uri donationPhotoURL) {
        this.donationPhotoURL = donationPhotoURL;
    }

    public void setDonationTarget(String donationTarget) {
        this.donationTarget = donationTarget;
    }


    private void clear(){
        this.donationType= null; // required 1 - одноразовый ; 2 - многоразовый
        this.donationPhotoURL = null; // required
        this.donationName = null; // required
        this.donationAmount = null; // required
        this.donationTarget = null; // required
        this.donationDescription = null; // required
        this.donationDate = null; // нужно если donationType == 1
        this.currentAmount = 0; // required
        this.postText = null; // required
        this.percentProgress = 0;
    }

    public Integer getDonationAmount() {
        return donationAmount;
    }

    public Integer getDonationType() {
        return donationType;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public String getDonationDescription() {
        return donationDescription;
    }

    public String getDonationName() {
        return donationName;
    }

    public Uri getDonationPhotoURL() {
        return donationPhotoURL;
    }

    public String getDonationTarget() {
        return donationTarget;
    }
}