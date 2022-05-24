package com.school.electronics.ui.all;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("DOCUMENT", "document" + document.getData());
                            String infofull = null;
                            List<String> infoc = (List<String>) document.getData();
                            for (int i = 0; i < infoc.size(); i++){
                                infofull = infofull + infoc.get(i) + "\n";
                            }
                        } else {
                            Log.d("DOCUMENT", "No such document");
                        }
                    }
                });



        cpuName.setText(cpu);
    }
}
