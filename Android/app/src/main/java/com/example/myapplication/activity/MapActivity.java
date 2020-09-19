package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.utils.MockData;
import com.example.myapplication.utils.VKGroupRenderer;
import com.example.myapplication.utils.VkClusterItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.Random;

public class MapActivity extends BaseActivity {

    private GoogleMap googleMap = null;
    private VKGroupRenderer vkRenderer;
    private ClusterManager<VkClusterItem> mClusterManager;

    private Button emotionBTN;
    private LinearLayout bottom_sheet;
    private BottomSheetBehavior sheetBehavior;
    private String selectedTypeEmo = "";
    private String selectedTypeCategory = null;
    private TextView radioEmo1;
    private TextView radioEmo2;
    private TextView radioEmo3;
    private TextView radioEmo4;
    private EditText searchET;
    private ImageView musicBTN;
    private ImageView cinemaBTN;
    private ImageView autumnBTN;
    private ImageView workBTN;
    private ImageView karantinBTN;
    private ImageView itBTN;
    private ImageView carBTN;
    private ImageView gameBTN;
    private ImageView artBTN;
    private ImageView humorBTN;
    private ImageView photoBTN;
    private ArrayList<ImageView> arrayCatButtons;
    private ArrayList<String> arrayCatTexts;
    private ArrayList<String> arrayCatTextsEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Log.d("myLogs","selected2 emo = " + getIntent().getExtras().getString("emo"));
        selectedTypeEmo = getIntent().getExtras().getString("emo");

        Log.d("myLogs","selected emo = " + selectedTypeEmo);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mGoogleMap) {
                googleMap = mGoogleMap;
                LatLng sydney = new LatLng(59.936282, 30.345080);
                //googleMap.addMarker(MarkerOptions().position(sydney).title("").snippet(""))
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(14f).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                createClusterManager();
                addPhotoItems();
                Log.d("myLogs","getMapAsync");
            }
        });
        LinearLayout mapLL = findViewById(R.id.mapLL);
        mapLL.animate().alpha(1f)
                .setDuration(500);


    }




    private void addPhotoItems(){

        mClusterManager.clearItems();
        mClusterManager.cluster();
        ArrayList<VkClusterItem> localItems = new ArrayList<VkClusterItem>();

        if(selectedTypeEmo != null && selectedTypeCategory != null){
            for(VkClusterItem item: MockData.getItems()){
                if(item.getPhotoUrl().contains("/"+selectedTypeCategory) && item.getTypeEmocion().equals(selectedTypeEmo)){
                    localItems.add(item);
                }
            }
        }else if(selectedTypeEmo != null){
            for(VkClusterItem item: MockData.getItems()){
                if(item.getTypeEmocion().equals(selectedTypeEmo)){
                    localItems.add(item);
                }
            }
        }else{
            for(VkClusterItem item:MockData.getItems()){
                localItems.add(item);
            }
        }

        mClusterManager.addItems(localItems);
        mClusterManager.cluster();
    }

    private void createClusterManager(){

        mClusterManager = new ClusterManager<VkClusterItem>(this, googleMap);
        vkRenderer = new VKGroupRenderer(
                getApplicationContext(),
                googleMap,
                mClusterManager);
        mClusterManager.setRenderer(vkRenderer);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        googleMap.setOnInfoWindowClickListener(mClusterManager);
        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

            }
        });
        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener() {
            @Override
            public boolean onClusterClick(Cluster cluster) {
                return false;
            }
        });
        mClusterManager.setOnClusterInfoWindowClickListener(new ClusterManager.OnClusterInfoWindowClickListener() {
            @Override
            public void onClusterInfoWindowClick(Cluster cluster) {

            }
        });
        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener() {
            @Override
            public boolean onClusterItemClick(ClusterItem clusterItem) {
                String category = ((VkClusterItem)clusterItem).getPhotoUrl().replace("https://instarlike.com/icons_vk/","");
                Intent intent = new Intent(getActivity(), FeedActivity.class);
                intent.putExtra("category",category);
                startActivity(intent);
                return false;
            }
        });
        mClusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener() {
            @Override
            public void onClusterItemInfoWindowClick(ClusterItem clusterItem) {

            }
        });
        initBottomSheetEmotions();
        initCategoryView();
//        tsb = TimerSearchBreaker(applicationContext, TimerSearchBreaker.ISearchTask { loadNewPhotos(it) })
    }

    private void initCategoryView() {
        searchET = (EditText) findViewById(R.id.searchET);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int i = 0;
                for(String text:arrayCatTexts){
                    if(text.toLowerCase().contains(s.toString())){
                        arrayCatButtons.get(i).setVisibility(View.VISIBLE);
                    }else{
                        arrayCatButtons.get(i).setVisibility(View.GONE);
                    }
                    i++;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        musicBTN = (ImageView) findViewById(R.id.musicBTN);
        cinemaBTN = (ImageView) findViewById(R.id.cinemaBTN);
        autumnBTN = (ImageView) findViewById(R.id.autumnBTN);
        workBTN = (ImageView) findViewById(R.id.workBTN);
        karantinBTN = (ImageView) findViewById(R.id.karantinBTN);
        itBTN = (ImageView) findViewById(R.id.itBTN);
        carBTN = (ImageView) findViewById(R.id.carBTN);
        gameBTN = (ImageView) findViewById(R.id.gameBTN);
        artBTN = (ImageView) findViewById(R.id.artBTN);
        humorBTN = (ImageView) findViewById(R.id.humorBTN);
        photoBTN = (ImageView) findViewById(R.id.photoBTN);




        arrayCatButtons = new ArrayList<ImageView>();
        arrayCatButtons.add(musicBTN);
        arrayCatButtons.add(cinemaBTN);
        arrayCatButtons.add(autumnBTN);
        arrayCatButtons.add(workBTN);
        arrayCatButtons.add(karantinBTN);
        arrayCatButtons.add(itBTN);
        arrayCatButtons.add(carBTN);
        arrayCatButtons.add(gameBTN);
        arrayCatButtons.add(artBTN);
        arrayCatButtons.add(humorBTN);
        arrayCatButtons.add(photoBTN);


        for(ImageView button:arrayCatButtons){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getAlpha() != 1){
                        v.setAlpha(1);
                        selectedTypeCategory = null;
                    }else{
                        int i = 0;
                        for(ImageView button:arrayCatButtons) {
                            if(v.equals(button) ){
                                selectedTypeCategory = arrayCatTextsEng.get(i);
                                Log.d("myLogs","selectedTypeCategory = " + selectedTypeCategory);
                            }else{
                                button.setAlpha(1f);
                            }
                            i++;
                        }
                        v.setAlpha(0.7f);

                    }
                    addPhotoItems();
                }
            });

        }

        arrayCatTexts = new ArrayList<String>();
        arrayCatTexts.add("музыка");
        arrayCatTexts.add("фильмы");
        arrayCatTexts.add("осень");
        arrayCatTexts.add("работа");
        arrayCatTexts.add("карантин");
        arrayCatTexts.add("it");
        arrayCatTexts.add("авто");
        arrayCatTexts.add("игры");
        arrayCatTexts.add("искусство");
        arrayCatTexts.add("юмор");
        arrayCatTexts.add("фотографии");

        arrayCatTextsEng = new ArrayList<String>();
        arrayCatTextsEng.add("music");
        arrayCatTextsEng.add("cinema");
        arrayCatTextsEng.add("autumn");
        arrayCatTextsEng.add("work");
        arrayCatTextsEng.add("karantin");
        arrayCatTextsEng.add("info_tech");
        arrayCatTextsEng.add("car");
        arrayCatTextsEng.add("game");
        arrayCatTextsEng.add("art");
        arrayCatTextsEng.add("humor");
        arrayCatTextsEng.add("photo");

    }

    private void initBottomSheetEmotions() {
        Log.d("MyLogs","initBottomSheetEmotions");

        emotionBTN = (Button) findViewById(R.id.emotionBTN);
        bottom_sheet = findViewById(R.id.bottom_sheet);
        radioEmo1 = (TextView) findViewById(R.id.radioEmo1);
        radioEmo2 = (TextView) findViewById(R.id.radioEmo2);
        radioEmo3 = (TextView) findViewById(R.id.radioEmo3);
        radioEmo4 = (TextView) findViewById(R.id.radioEmo4);

        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);

        updateEmoView();
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
// click event for show-dismiss bottom sheet
        emotionBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyLogs","onClick");
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    Log.d("MyLogs","Close Sheet");
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    Log.d("MyLogs","Expand Sheet");
                }
            }
        });

// callback for do something
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        Log.d("MyLogs","Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        Log.d("MyLogs","Expand Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.radioEmo1:
                selectedTypeEmo = "best";
                emotionBTN.setText("Хорошее настроение");
                break;
            case R.id.radioEmo2:
                selectedTypeEmo = "scver";
                emotionBTN.setText("Скверное настроение");
                break;
            case R.id.radioEmo3:
                selectedTypeEmo = "middle";
                emotionBTN.setText("Умиротворённое настроение");
                break;
            case R.id.radioEmo4:
                selectedTypeEmo = "energy";
                emotionBTN.setText("Энергичное настроение");
                break;
        }
        updateEmoView();
        addPhotoItems();
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        Log.d("myLogs","selected emo = " + selectedTypeEmo);
    }

    private void updateEmoView(){
        radioEmo1.setAlpha(0.5f);
        radioEmo2.setAlpha(0.5f);
        radioEmo3.setAlpha(0.5f);
        radioEmo4.setAlpha(0.5f);
        if(selectedTypeEmo.equals("best")){
            radioEmo1.setAlpha(1f);
            emotionBTN.setText("Хорошее настроение");
            emotionBTN.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_best, 0, R.drawable.ic_dropdown_16, 0);
        }
        if(selectedTypeEmo.equals("scver")){
            radioEmo2.setAlpha(1f);
            emotionBTN.setText("Скверное настроение");
            emotionBTN.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_scver, 0, R.drawable.ic_dropdown_16, 0);
        }
        if(selectedTypeEmo.equals("middle")){
            radioEmo3.setAlpha(1f);
            emotionBTN.setText("Умиротворённое настроение");
            emotionBTN.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_middle, 0, R.drawable.ic_dropdown_16, 0);
        }
        if(selectedTypeEmo.equals("energy")){
            radioEmo4.setAlpha(1f);
            emotionBTN.setText("Энергичное настроение");
            emotionBTN.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_energy, 0, R.drawable.ic_dropdown_16, 0);
        }
    }

}