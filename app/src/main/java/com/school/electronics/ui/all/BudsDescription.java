package com.school.electronics.ui.all;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.school.electronics.R;

public class BudsDescription extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buds_description);
        TextView budsName = findViewById(R.id.budsname);

        String buds = "buds not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            buds = extras.getString("buds");
        }
        budsName.setText(buds);
    }
}
