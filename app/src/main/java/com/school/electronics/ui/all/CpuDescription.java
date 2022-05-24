package com.school.electronics.ui.all;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.school.electronics.R;

public class CpuDescription extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu_description);
        TextView cpuName = findViewById(R.id.cpuname);

        String cpu = "cpu not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            cpu = extras.getString("cpu");
        }
        cpuName.setText(cpu);
    }
}
