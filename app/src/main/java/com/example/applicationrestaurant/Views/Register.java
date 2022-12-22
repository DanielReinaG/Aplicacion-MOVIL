package com.example.applicationrestaurant.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationrestaurant.R;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private TextView btnRegister;
    private EditText editEmailRegister,editPasslRegister,editPassConfirmRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = (TextView) findViewById(R.id.btnRegister);
        editEmailRegister = (EditText) findViewById(R.id.editEmailRegister);
        editPasslRegister = (EditText) findViewById(R.id.editPasslRegister);
        editPassConfirmRegister = (EditText) findViewById(R.id.editPassConfirmRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validar si los campos de contraseña son iguales
                String email = editEmailRegister.getText().toString();
                String pass = editPasslRegister.getText().toString();
                String confirm = editPassConfirmRegister.getText().toString();
                //si al comparar lo agregado en el campo pass con confirm es igual a 0
                //si es valido crea el usuario y lo guarda en el FIREBASE
                if(pass.compareTo(confirm)==0){
                    FirebaseAuth mAuth;
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email,pass);
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"Contraseña no coincide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}