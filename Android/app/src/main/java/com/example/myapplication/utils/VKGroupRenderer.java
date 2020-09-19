package com.example.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.myapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;


public class VKGroupRenderer extends DefaultClusterRenderer<VkClusterItem> {
    private Context context;
    private int iconWidth = 0;
    private int photoWidth = 0;
    private int textPaddingLeft = 0;
    private int textPaddingTop = 0;
    private int textPaddingLeft12 = 0;
    private int photoCounterWidth = 0;
    private int pickedColor = 0;
    private View myCounterView;
    private View myMarker;
    private View photosMarker;
    private View photosCounterMarker;
    private RoundedImageView markerImage;
    private RoundedImageView photosImage;
    private RoundedImageView photoCounterImage;
    private ArrayList<Integer> clusterTextBackgrounds = new ArrayList<Integer>();
    private Handler handler;
    boolean isPhoto = false;
    @SuppressLint("WrongViewCast")
    public VKGroupRenderer(Context context, GoogleMap map, ClusterManager<VkClusterItem> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
        iconWidth = Math.round(ConvertUtils.convertDpToPixel(72f, context));
        photoWidth = Math.round(ConvertUtils.convertDpToPixel(88f, context));
        photoCounterWidth = Math.round(ConvertUtils.convertDpToPixel(52f, context));
        textPaddingLeft = Math.round(ConvertUtils.convertDpToPixel(7f, context));
        textPaddingLeft12 = Math.round(ConvertUtils.convertDpToPixel(12f, context));
        textPaddingTop = Math.round(ConvertUtils.convertDpToPixel(5f, context));
        myCounterView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cluster_text_layout, null);
        myMarker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cluster_item_layout, null);
        photosMarker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cluster_item_photo, null);
        photosCounterMarker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cluster_photo_counter, null);

        markerImage = (RoundedImageView) myMarker.findViewById(R.id.photoIV);
        photosImage = (RoundedImageView) photosMarker.findViewById(R.id.photoIV);
        photoCounterImage = (RoundedImageView) photosCounterMarker.findViewById(R.id.photoIV);
        setClusterTextBackgrounds();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onBeforeClusterItemRendered(VkClusterItem item, MarkerOptions markerOptions) {

    }

    @Override
    protected void onBeforeClusterRendered(Cluster<VkClusterItem> cluster, MarkerOptions markerOptions) {
        if (context != null && markerOptions != null){
            ArrayList<VkClusterItem> groups = new ArrayList<VkClusterItem>(cluster.getItems());
            for (VkClusterItem p : cluster.getItems()) {
                if (!isPhoto){
                    loadGroupIcon(p, markerOptions);
                } else {

                    loadPhotosIcon(p, markerOptions, groups.size());
                }

            }
            if (!isPhoto){
                loadGroupsCounter(groups.size(), markerOptions);
            } else {
                loadPhotosIconCounter(groups.get(0), markerOptions, groups.size());
            }
        }



    }



    @Override
    protected void onClusterItemRendered(VkClusterItem clusterItem, Marker marker) {
        if (context != null && marker != null) {
            if (!isPhoto) {
                renderGroupItem(clusterItem, marker);
            } else {
                renderPhotoItem(clusterItem, marker);
            }
        }

    }

    @Override
    protected void onClusterRendered(Cluster<VkClusterItem> cluster, Marker marker) {
        if (context != null && marker != null) {
            if (isPhoto) {
                ArrayList<VkClusterItem> groups = new ArrayList<VkClusterItem>(cluster.getItems());
                renderPhotosIconCounter(groups.get(0), marker, groups.size());
            }
        }

    }



    private void renderGroupItem(VkClusterItem clusterItem, final Marker marker){
        if (context != null && marker != null) {
            Glide.with(context).asBitmap().load(clusterItem.getPhotoUrl()).override(iconWidth, iconWidth).centerCrop().into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    try {
                        Bitmap myBitmap = getGroupIconBitmap(resource);
                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(myBitmap));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                }
            });
//        Picasso.get().load(clusterItem.getPhotoUrl()).centerCrop().resize(iconWidth, iconWidth).into(new PicassoMarker(bitmap -> {
//            Bitmap myBitmap = getGroupIconBitmap(bitmap);
//            marker.setIcon(BitmapDescriptorFactory.fromBitmap(myBitmap));
//        }));
        }
    }

    private void loadGroupsCounter(int groupsSize, MarkerOptions markerOptions){

        try {
            TextView counterTV = myCounterView.findViewById(R.id.counterTV);
            counterTV.setBackground(ContextCompat.getDrawable(context, clusterTextBackgrounds.get(pickedColor)));
            if (groupsSize > 9){
                counterTV.setPadding(textPaddingLeft, textPaddingTop, 0,0);
            } else {
                counterTV.setPadding(textPaddingLeft12, textPaddingTop, 0,0);
            }
            if (groupsSize > 99){
                counterTV.setText("99");
            } else {
                counterTV.setText(String.valueOf(groupsSize));
            }

            myCounterView.setLayoutParams(new ViewGroup.LayoutParams(iconWidth, iconWidth));
            myCounterView.measure(0, 0);
            myCounterView.layout(0, 0, iconWidth, iconWidth);
            myCounterView.buildDrawingCache();
            Bitmap myBitmap = Bitmap.createBitmap(iconWidth, iconWidth, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(myBitmap);
            myCounterView.draw(canvas);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(myBitmap));
            pickedColorIteration();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private void loadGroupIcon(VkClusterItem group, final MarkerOptions markerOptions){
        Glide.with(context).asBitmap().load(group.getResDrawableId()).override(iconWidth, iconWidth).centerCrop().into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    Bitmap myBitmap = getGroupIconBitmap(resource);
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(myBitmap));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });


//        Picasso.get().load(group.getPhotoUrl()).centerCrop().resize(iconWidth, iconWidth).into(new PicassoMarker(bitmap -> {
//            Bitmap myBitmap = getGroupIconBitmap(bitmap);
//            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(myBitmap));
//        }));
    }

    private void loadPhotosIcon(VkClusterItem group, final MarkerOptions markerOptions, int size){

        Glide.with(context).asBitmap().load(group.getResDrawableId()).override(photoWidth, photoWidth).centerCrop().into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    Bitmap myBitmap = getPhotoIconBitmap(resource);
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(myBitmap));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
//        Picasso.get().load(group.getPhotoUrl()).centerCrop().resize(photoWidth,photoWidth).into(new PicassoMarker(bitmap -> {
//            Bitmap myBitmap = getPhotoIconBitmap(bitmap);
//            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(myBitmap));
//        }));
    }
    private void renderPhotoItem(VkClusterItem clusterItem, final Marker marker){
        Glide.with(context).asBitmap().load(clusterItem.getResDrawableId()).override(photoWidth, photoWidth).centerCrop().into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    Bitmap myBitmap = getPhotoIconBitmap(resource);
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(myBitmap));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
//        Picasso.get().load(clusterItem.getPhotoUrl()).centerCrop().resize(photoWidth,photoWidth).into(new PicassoMarker(bitmap -> {
//            Bitmap myBitmap = getPhotoIconBitmap(bitmap);
//            marker.setIcon(BitmapDescriptorFactory.fromBitmap(myBitmap));
//        }));
    }

    private void loadPhotosIconCounter(VkClusterItem group, final MarkerOptions markerOptions, final int size){
        Glide.with(context).asBitmap().load(group.getResDrawableId()).override(photoWidth, photoWidth).centerCrop().into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    Bitmap myBitmap = getClusterPhotoBitmap(resource, size);
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(myBitmap));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
    }

    private void renderPhotosIconCounter(VkClusterItem group, final Marker marker, final int size){
        Glide.with(context).asBitmap().load(group.getResDrawableId()).override(photoWidth, photoWidth).centerCrop().into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            Bitmap myBitmap = getClusterPhotoBitmap(resource, size);
                            marker.setIcon(BitmapDescriptorFactory.fromBitmap(myBitmap));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

    }

    private Bitmap getPhotoIconBitmap(Bitmap bitmap){
        photosImage.setImageBitmap(bitmap);

        photosMarker.layout(0, 0, photoWidth, photoWidth);
        photosMarker.buildDrawingCache();
        Bitmap myBitmap = Bitmap.createBitmap(photoWidth, photoWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(myBitmap);
        photosMarker.draw(canvas);
        return myBitmap;
    }
    private Bitmap getGroupIconBitmap(Bitmap bitmap){
        markerImage.setImageBitmap(bitmap);

        myMarker.setLayoutParams(new ViewGroup.LayoutParams(iconWidth, iconWidth));
        myMarker.measure(iconWidth, iconWidth);
        myMarker.layout(0, 0, iconWidth, iconWidth);
        myMarker.buildDrawingCache();
        Bitmap myBitmap = Bitmap.createBitmap(myMarker.getMeasuredWidth(), myMarker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(myBitmap);
        myMarker.draw(canvas);
        return myBitmap;
    }
    private Bitmap getClusterPhotoBitmap(Bitmap bitmap, int size){
        photoCounterImage.setImageBitmap(bitmap);
        TextView counterTV = photosCounterMarker.findViewById(R.id.counterTV);
        counterTV.setText(String.valueOf(size));
        photosCounterMarker.setLayoutParams(new ViewGroup.LayoutParams(photoCounterWidth, photoCounterWidth));
        photosCounterMarker.measure(photoCounterWidth, photoCounterWidth);
        photosCounterMarker.layout(0, 0, photoCounterWidth, photoCounterWidth);
        photosCounterMarker.buildDrawingCache();
        Bitmap myBitmap = Bitmap.createBitmap(photoCounterWidth, photoCounterWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(myBitmap);
        photosCounterMarker.draw(canvas);
        return myBitmap;
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        return cluster.getSize() > 3;
    }

    private void setClusterTextBackgrounds(){
        clusterTextBackgrounds.add(R.drawable.cluster_counter_blue);
        clusterTextBackgrounds.add(R.drawable.cluster_counter_purple);
        clusterTextBackgrounds.add(R.drawable.cluster_counter_gray);
        clusterTextBackgrounds.add(R.drawable.cluster_counter_red);
    }
    private void pickedColorIteration(){
        pickedColor ++;
        if (pickedColor == clusterTextBackgrounds.size()){
            pickedColor = 0;
        }
    }
    public void setIsPhoto(boolean isPhoto){
        this.isPhoto = isPhoto;
    }
}

