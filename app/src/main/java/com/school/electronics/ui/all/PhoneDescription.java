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

public class PhoneDescription extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_description);
        TextView phoneName = findViewById(R.id.phonename);
        TextView phoneInfo = findViewById(R.id.phoneinfo);
        String phone = "cpu not set";
        Bundle extras = getIntent().getExtras();
        Button btnM = findViewById(R.id.button);
        Button btnD = findViewById(R.id.button2);
        Button btnS = findViewById(R.id.button3);
        String imageID = "";
        ImageView phoneView = findViewById(R.id.cpuimg);

        if(extras != null){
            phone = extras.getString("phone");
            imageID = extras.getString("phone");
        }

        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference("smartphones/" + imageID + ".jpg");
        try {
            File localfile = File.createTempFile("tempfile", ".jpg");
            storageReference.getFile(localfile)
                    .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            phoneView.setImageBitmap(bitmap);

                        }}).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("products").document("electronics").collection("Phones").document(phone).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String cpuifull = "";
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("DOCUMENT", "document" + document.getData());
                            Map<String, Object> cpui = (Map<String, Object>) document.getData();
                            cpuifull = cpuifull + (String) cpui.get("batery") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("camers") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("communication") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("cpu") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("display") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("interfaces") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("m") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("memory") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("os") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("protection") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("ram") + "\n";
                            cpuifull = cpuifull + (String) cpui.get("slots") + "\n";


                        } else {
                            Log.d("DOCUMENT", "No such document");
                        }
                        phoneInfo.setText(cpuifull);
                    }
                });

        phoneName.setText(phone);
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.mvideo.ru/product-list-page?q="+phoneName.getText();
                goToUrl(url);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.dns-shop.ru/search/?q="+phoneName.getText();
                goToUrl(url);
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.citilink.ru/search/?text="+phoneName.getText();
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
