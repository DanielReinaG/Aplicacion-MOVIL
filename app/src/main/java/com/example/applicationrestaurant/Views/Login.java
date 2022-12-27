package com.example.applicationrestaurant.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.Products.ListComida;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private TextView btnLogin, btnLoginRegister;
    private EditText editEmailLogin, editPassLogin;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (TextView) findViewById(R.id.btnLogin);
        btnLoginRegister = (TextView) findViewById(R.id.btnLoginRegister);
        editEmailLogin = (EditText) findViewById(R.id.editEmailLogin);
        editPassLogin = (EditText) findViewById(R.id.editPassLogin);

        //EVENTO BOTONES
        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmailLogin.getText().toString();
                String pass = editPassLogin.getText().toString();
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(getApplicationContext(), InicioMenu.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Intente nuevamente", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
            }
        });

    }
}