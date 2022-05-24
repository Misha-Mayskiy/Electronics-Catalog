package com.school.electronics.ui.all;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.school.electronics.R;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CpuDescription extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu_description);
        TextView cpuName = findViewById(R.id.cpuname);
        TextView cpuInfo = findViewById(R.id.cpuinfo);
        String cpu = "cpu not set";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            cpu = extras.getString("cpu");
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("products").document("electronics").collection("CPUs").document(cpu).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String cpuifull = "";
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("DOCUMENT", "document" + document.getData());
                            Map<String, Object> cpui = (Map<String, Object>) document.getData();
                            cpuifull = cpuifull + (String) cpui.get("cacheL2L3") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("cores") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("cpucore") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("powerusage") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("ram") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("releasedate") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("socket") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("techprocess") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("cacheL2L3") + "\n";


                        } else {
                            Log.d("DOCUMENT", "No such document");
                        }
                        cpuInfo.setText(cpuifull);
                    }
                });



        cpuName.setText(cpu);

    }

}
