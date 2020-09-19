package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.controller.DataProcessor;
import com.example.myapplication.utils.MockData;
import com.example.myapplication.utils.VkClusterItem;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class CreatePostActivity extends BaseActivity {

    private EditText textPostET;
    private ImageView createPostBTN;
    private Button emotionBTN;
    private Button categoryBTN;
    private LinearLayout bottom_sheet;
    private BottomSheetBehavior sheetBehavior;
    private ImageView closeEmoSheetIV;

    private String selectedTypeEmo = "";
    private Boolean clickedPostBTN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        MockData.generateData(getActivity());

        textPostET = (EditText) findViewById(R.id.textPostET);
        textPostET.post(new Runnable() {
            @Override
            public void run() {
                textPostET.requestFocus();
                InputMethodManager imm = (InputMethodManager) textPostET.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(textPostET, InputMethodManager.SHOW_IMPLICIT);
            }
        });


        createPostBTN = (ImageView) findViewById(R.id.createPostBTN);
        textPostET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                DataProcessor.getInstance().setPostText(s.toString());
            }
        });



        createPostBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataProcessor.getInstance().getPostText() == null || DataProcessor.getInstance().getPostText().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Введите пожалуйста текст для записи", Toast.LENGTH_LONG).show();
                    return;
                }
                if(selectedTypeEmo.equals("")){
                    clickedPostBTN = true;
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    return;
                }
                addItem();
                Intent intent = new Intent(getActivity(), FeedEmoActivity.class);
                intent.putExtra("emo",selectedTypeEmo);
                startActivity(intent);
                finish();

            }
        });

        initBottomSheetEmotions();
    }

    private void addItem() {
        String testPhotoUrl = "https://instarlike.com/icons_vk/";
        VkClusterItem vkClusterItem = new VkClusterItem(testPhotoUrl+"work.png","25","Матвей Правосудов",textPostET.getText().toString(),59.936282, 30.345080,"Addr1",false,"ScreenName1",0,selectedTypeEmo);
        MockData.addItems(vkClusterItem);
        Log.d("myLogs","selectedTypeEmo = " + selectedTypeEmo);
        MockData.selectedTypeEmo = selectedTypeEmo;
    }

    private void initBottomSheetEmotions() {
        Log.d("MyLogs","initBottomSheetEmotions");
        closeEmoSheetIV = (ImageView) findViewById(R.id.closeEmoSheetIV);
        bottom_sheet = findViewById(R.id.bottom_sheet);
        emotionBTN = (Button) findViewById(R.id.emotionBTN);
        categoryBTN = (Button) findViewById(R.id.categoryBTN);

        closeEmoSheetIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        categoryBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Изменить тематику нельзя. У вас выбрана категория работа.",Toast.LENGTH_LONG).show();
            }
        });
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);
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
        boolean checked = ((RadioButton) view).isChecked();

        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.radioEmo1:
                if (checked){
                    selectedTypeEmo = "best";
                    emotionBTN.setText("Хорошее");
                }
                break;
            case R.id.radioEmo2:
                if (checked){
                    selectedTypeEmo = "scver";
                    emotionBTN.setText("Скверное");
                }
                break;
            case R.id.radioEmo3:
                if (checked){
                    selectedTypeEmo = "middle";
                    emotionBTN.setText("Умиротворённое");
                }
                break;
            case R.id.radioEmo4:
                if (checked){
                    selectedTypeEmo = "energy";
                    emotionBTN.setText("Энергичное");
                }
                break;
        }
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        if(clickedPostBTN){
            addItem();
            Intent intent = new Intent(getActivity(), FeedEmoActivity.class);
            intent.putExtra("emo",selectedTypeEmo);
            startActivity(intent);
            finish();
        }
        Log.d("myLogs","selected emo = " + selectedTypeEmo);
    }
}