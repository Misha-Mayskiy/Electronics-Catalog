package com.school.electronics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.school.electronics.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;

    private final List<String> cpus = new ArrayList<String>();
    private final List<String> gpus = new ArrayList<String>();
    private final List<String> phones = new ArrayList<String>();
    private final List<String> earbuds = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_mainpage)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("products").document("electronics").collection("CPUs").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                cpus.add(document.getId());
                            }
                        } else {
                            Log.d("DOCUMENT", "Error getting documents: ", task.getException());
                        }
                    }
                });
        db.collection("products").document("electronics").collection("GPUs").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                gpus.add(document.getId());
                            }
                        } else {
                            Log.d("DOCUMENT", "Error getting documents: ", task.getException());
                        }
                    }
                });
        db.collection("products").document("electronics").collection("Earbuds").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                earbuds.add(document.getId());
                            }
                        } else {
                            Log.d("DOCUMENT", "Error getting documents: ", task.getException());
                        }
                    }
                });
        db.collection("products").document("electronics").collection("Phones").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                phones.add(document.getId());
                            }
                        } else {
                            Log.d("DOCUMENT", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
            case R.id.action_settings:
                System.out.println("Settings showed");
                return true;
            case R.id.action_authors:
                System.out.println("Authors showed");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public String[] getCPUs() {
        String[] allcpus = new String[cpus.size()];
        cpus.toArray(allcpus);
        return allcpus;
    }
    public String[] getGPUs() {
        String[] allgpus = new String[gpus.size()];
        gpus.toArray(allgpus);
        return allgpus;
    }
    public String[] getEarbuds() {
        String[] allbuds = new String[earbuds.size()];
        earbuds.toArray(allbuds);
        return allbuds;
    }
    public String[] getPhones() {
        String[] allphones = new String[phones.size()];
        phones.toArray(allphones);
        return allphones;
    }

    public void nextFragment(int fragment_id) {
        navController.navigate(fragment_id);
    }
}