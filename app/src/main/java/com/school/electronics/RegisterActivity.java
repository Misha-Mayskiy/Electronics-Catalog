package com.school.electronics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText email_register;
    private EditText password_register;
    private Button button_register;
    private TextView login_text;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        email_register = findViewById(R.id.editEmailRegister);
        password_register = findViewById(R.id.editTextPasswordRegister);
        button_register = findViewById(R.id.buttonRegister);
        login_text = findViewById(R.id.loginTxt);

        login_text.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        button_register.setOnClickListener(view -> {
            if (email_register.getText().toString().isEmpty() ||
                    password_register.getText().toString().isEmpty()) {
                Toast.makeText(RegisterActivity.this,
                        "Поля не могут быть пустыми",
                        Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email_register.getText().toString(),
                                password_register.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(RegisterActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this,
                                        "Неизвестная ошибка",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
