package com.school.electronics.ui.all;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.school.electronics.R;

public class PhoneDescription extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_description);
        TextView phoneName = findViewById(R.id.phonename);

        String phone = "phone not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            phone = extras.getString("phone");
        }
        phoneName.setText(phone);
    }
}
