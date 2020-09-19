package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.controller.DataProcessor;

import java.util.Calendar;

public class SelectDateActivity extends BaseActivity {

    private Button authorBTN;
    private Boolean isNeedDate = true;
    private LinearLayout dateLL;
    private Button pickDateBTN;
    private Button createDonatBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        authorBTN = (Button) findViewById(R.id.authorBTN);
        RadioButton radio_date = (RadioButton) findViewById(R.id.radio_date);
        dateLL = (LinearLayout) findViewById(R.id.dateLL);
        pickDateBTN = (Button) findViewById(R.id.pickDateBTN);
        createDonatBTN = (Button) findViewById(R.id.createDonatBTN);
        radio_date.setChecked(true);

        pickDateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                pickDateBTN.setText(date);
                                DataProcessor.getInstance().setDonationDate(date);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        authorBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"По задания здесь заглушка", Toast.LENGTH_LONG).show();
            }
        });
        createDonatBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreatePostActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();

        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.radio_amount:
                if (checked){
                    isNeedDate = false;
                    dateLL.setVisibility(View.GONE);
                    DataProcessor.getInstance().setDonationDate(null);
                    pickDateBTN.setText("Выберите дату");
                }
                break;
            case R.id.radio_date:
                if (checked){
                    isNeedDate = true;
                    dateLL.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}