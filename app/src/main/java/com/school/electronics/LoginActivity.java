package com.school.electronics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText email_login;
    private EditText password_login;
    private Button button_login;
    private TextView register_text;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_login = findViewById(R.id.editEmailAddress);
        password_login = findViewById(R.id.editTextPassword);
        button_login = findViewById(R.id.buttonLogin);
        register_text = findViewById(R.id.registerTxt);

        mAuth = FirebaseAuth.getInstance();

        register_text.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        button_login.setOnClickListener(view -> {
            if (email_login.getText().toString().isEmpty() ||
                    password_login.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,
                        "Поля не могут быть пустыми",
                        Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email_login.getText().toString(),
                        password_login.getText().toString()).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        "Неизвестная ошибка",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}