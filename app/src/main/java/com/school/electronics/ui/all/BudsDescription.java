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

public class BudsDescription extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buds_description);
        TextView budsName = findViewById(R.id.budsname);
        TextView budsInfo = findViewById(R.id.budsinfo);
        String buds = "buds not set";
        Button btnM = findViewById(R.id.button);
        Button btnD = findViewById(R.id.button2);
        Button btnS = findViewById(R.id.button3);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            buds = extras.getString("buds");
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("products").document("electronics").collection("Earbuds").document(buds).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String cpuifull = "";
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("DOCUMENT", "document" + document.getData());
                            Map<String, Object> cpui = (Map<String, Object>) document.getData();
                            cpuifull = cpuifull + (String) cpui.get("connection") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("construction") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("diapason") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("kodeki") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("m") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("typeemitters") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("usbtype") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("worktime") + "\n";



                        } else {
                            Log.d("DOCUMENT", "No such document");
                        }
                        budsInfo.setText(cpuifull);
                    }
                });
        budsName.setText(buds);
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.mvideo.ru/product-list-page?q="+budsName.getText();
                goToUrl(url);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.dns-shop.ru/search/?q="+budsName.getText();
                goToUrl(url);
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.citilink.ru/search/?text="+budsName.getText();
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
