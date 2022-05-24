package com.school.electronics.ui.all;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.school.electronics.R;

public class GpuDescription extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpu_description);
        TextView gpuName = findViewById(R.id.gpuname);

        String gpu = "cpu not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            gpu = extras.getString("gpu");
        }
        gpuName.setText(gpu);
    }
}
