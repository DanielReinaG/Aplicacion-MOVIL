package com.example.applicationrestaurant.DB;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.applicationrestaurant.Adapter.ComidaAdapter;
import com.example.applicationrestaurant.Entities.Comida;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBFirebase {
    private FirebaseFirestore db;

    public DBFirebase(){
        this.db = FirebaseFirestore.getInstance();
    }

    //Comida referencia a la entidad COMIDA
    public void insertData(Comida comida){
        Map<String, Object> prod = new HashMap<>();
        prod.put("id", comida.getId());
        prod.put("name", comida.getName());
        prod.put("description", comida.getDescription());
        prod.put("price", comida.getPrice());
        prod.put("image", comida.getImage());

        // Add a new document with a generated ID
        //"food" es la tabla de la BD
        db.collection("food").add(prod);
    }

    //CONSULTAR PRODUCTOS
    public void getData(ComidaAdapter adapter, ArrayList<Comida> list){
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Comida comida = new Comida(
                                        document.getData().get("id").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        Integer.parseInt(document.getData().get("price").toString()),
                                        document.getData().get("image").toString()
                                );
                                list.add(comida);
                            }
                            //ESTO ACTUALIZA LOS DATOS EN PANTALLA
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    //DELETE
    public void deleteData(String id){
        db.collection("food").whereEqualTo("id", id)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                documentSnapshot.getReference().delete();
                            }
                        }
                    }
                });
    }

    //ACTUALIZAR
    public void updateData(Comida comida){
        db.collection("food").whereEqualTo("id", comida.getId())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                documentSnapshot.getReference().update(
                                        "name", comida.getName(),
                                        "description", comida.getDescription(),
                                        "price", comida.getPrice(),
                                        "image", comida.getImage()
                                );
                            }
                        }
                    }
                });
    }
}
