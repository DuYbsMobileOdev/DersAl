package com.mustafageldi.dersal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button loginButton, signUpButton;
    private TextInputEditText emailTxt, passwordTxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);
        emailTxt = findViewById(R.id.signUpEmailTxt);
        passwordTxt = findViewById(R.id.signUpPasswordTxt);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view -> {
            String email = emailTxt.getText().toString();
            String password = passwordTxt.getText().toString();

            if (email.equals("") || password.equals("")) {
                //Bosluk kontrolü ->>
                Toast.makeText(MainActivity.this, "Giriş bilgilerinde eksik var. Lütfen kontrol edip tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
            } else {
                //Bos degil isleme devam et ->>
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Giris basarılı kullanıcıyı iceri al ->
                            Toast.makeText(MainActivity.this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Bşarısız!", Toast.LENGTH_SHORT).show();
                        }
                    }});
            }
        });

        signUpButton.setOnClickListener(view -> {
            //Kayıt olma sayfasına git ->
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });


    }
}