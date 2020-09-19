package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class MainActivity extends BaseActivity {

    private Button createDonatBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDonatBTN = (Button) findViewById(R.id.createDonatBTN);
        createDonatBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectTypeDonation.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1001);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant that should be quite unique

                return;
            }
        }
    }
}