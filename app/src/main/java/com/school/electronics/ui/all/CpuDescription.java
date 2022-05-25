package com.school.electronics.ui.all;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.school.electronics.R;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CpuDescription extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu_description);
        TextView cpuName = findViewById(R.id.cpuname);
        TextView cpuInfo = findViewById(R.id.cpuinfo);
        String cpu = "cpu not set";
        Bundle extras = getIntent().getExtras();
        Button btnM = findViewById(R.id.button3);
        Button btnD = findViewById(R.id.button);
        Button btnS = findViewById(R.id.button2);
        String imageID = "";
        ImageView cpuView = findViewById(R.id.cpuimg);

        if(extras != null){
            cpu = extras.getString("cpu");
            imageID = extras.getString("cpu");
        }

        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference("cpus/" + imageID + ".jpg");
        try {
            File localfile = File.createTempFile("tempfile", ".jpg");
            storageReference.getFile(localfile)
                    .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            cpuView.setImageBitmap(bitmap);

                        }}).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
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
                            cpuifull = cpuifull + (String) cpui.get("threads").toString();


                        } else {
                            Log.d("DOCUMENT", "No such document");
                        }
                        cpuInfo.setText(cpuifull);
                    }
                });
        cpuName.setText(cpu);
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.mvideo.ru/product-list-page?q="+cpuName.getText();
                goToUrl(url);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.dns-shop.ru/search/?q="+cpuName.getText();
                goToUrl(url);
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.citilink.ru/search/?text="+cpuName.getText();
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
