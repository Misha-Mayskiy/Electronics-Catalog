package com.school.electronics.ui.all;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.school.electronics.R;

import java.util.Map;

public class GpuDescription extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpu_description);
        TextView gpuName = findViewById(R.id.gpuname);
        TextView gpuInfo = findViewById(R.id.gpuinfo);
        String gpu = "gpus not set";
        Button btnM = findViewById(R.id.button);
        Button btnD = findViewById(R.id.button2);
        Button btnS = findViewById(R.id.button3);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            gpu = extras.getString("gpu");
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("products").document("electronics").collection("GPUs").document(gpu).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String cpuifull = "";
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("DOCUMENT", "document" + document.getData());
                            Map<String, Object> cpui = (Map<String, Object>) document.getData();
                            cpuifull = cpuifull + (String) cpui.get("bus") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("connectiontype") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("cpufrequency") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("frequency") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("interfaces") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("maxmonitors") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("maxresolution") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("memory") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("memorytype") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("powerconnector") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("tdp") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("techprocess") + "\n";



                        } else {
                            Log.d("DOCUMENT", "No such document");
                        }
                        gpuInfo.setText(cpuifull);
                    }
                });
        gpuName.setText(gpu);
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.mvideo.ru/product-list-page?q="+gpuName.getText();
                goToUrl(url);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.dns-shop.ru/search/?q="+gpuName.getText();
                goToUrl(url);
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.citilink.ru/search/?text="+gpuName.getText();
                goToUrl(url);
            }
        });
    }
    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url) ;
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl) ;
        startActivity(launchBrowser) ;
    }
}
