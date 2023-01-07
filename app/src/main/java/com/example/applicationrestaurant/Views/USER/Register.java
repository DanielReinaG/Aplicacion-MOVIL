package com.example.applicationrestaurant.Views.USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.ADMIN.Products.ListComida;
import com.example.applicationrestaurant.Views.USER.Login;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private TextView btnRegister;
    private EditText editEmailRegister,editPasslRegister,editPassConfirmRegister,editNameRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        editEmailRegister = findViewById(R.id.editEmailRegister);
        editPasslRegister = findViewById(R.id.editPasslRegister);
        editPassConfirmRegister = findViewById(R.id.editPassConfirmRegister);
        editNameRegister = findViewById(R.id.editNameRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validar si los campos de contraseña son iguales
                String name = editNameRegister.getText().toString();
                String email = editEmailRegister.getText().toString();
                String pass = editPasslRegister.getText().toString();
                String confirm = editPassConfirmRegister.getText().toString();
                //si al comparar lo agregado en el campo pass con confirm es igual a 0
                //si es valido crea el usuario y lo guarda en el FIREBASE
                if(name.equals("") || email.equals("") || pass.equals("") || confirm.equals("")){
                    Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    //VALIDACION CORREO
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        editEmailRegister.setError("Correo Invalido");
                        editEmailRegister.setFocusable(true);
                    }
                    else if(pass.length()<7){
                        editPasslRegister.setError("Contraseña debe ser mayor o igual a 8");
                        editPasslRegister.setFocusable(true);
                    }
                    else if(pass.compareTo(confirm)==0) {
                        FirebaseAuth mAuth;
                        mAuth = FirebaseAuth.getInstance();
                        mAuth.createUserWithEmailAndPassword(email, pass);
                        Intent intent = new Intent(getApplicationContext(), ListComida.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(),"Las constraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}