package com.example.myapplication.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class VkClusterItem implements ClusterItem {
    String photoUrl = "";
    String id = "";
    String name = "";
    String description = "";
    Double lat = 0.0;
    Double lon = 0.0;
    String address = "";
    Boolean isEvent = false;
    String screenName = "";
    Integer photoUrlBig = 0;
    Integer resDrawableId = 0;
    String typeEmocion = "";

    public VkClusterItem(String photoUrl, String id, String name, String description, Double lat, Double lon, String address, Boolean isEvent, String screenName, Integer photoUrlBig, String typeEmocion) {
        this.photoUrl = photoUrl;
        this.id = id;
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.isEvent = isEvent;
        this.screenName = screenName;
        this.photoUrlBig = photoUrlBig;
        this.resDrawableId = resDrawableId;
        this.typeEmocion = typeEmocion;
    }

    public String getTypeEmocion() {
        return typeEmocion;
    }

    public void setTypeEmocion(String typeEmocion) {
        this.typeEmocion = typeEmocion;
    }

    public Integer getResDrawableId() {
        return resDrawableId;
    }

    public void setResDrawableId(Integer resDrawableId) {
        this.resDrawableId = resDrawableId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEvent() {
        return isEvent;
    }

    public void setEvent(Boolean event) {
        isEvent = event;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getPhotoUrlBig() {
        return photoUrlBig;
    }

    public void setPhotoUrlBig(Integer photoUrlBig) {
        this.photoUrlBig = photoUrlBig;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(lat,lon);
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getSnippet() {
        return "";
    }
}
