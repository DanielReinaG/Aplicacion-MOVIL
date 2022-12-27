package com.example.applicationrestaurant.DB;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.applicationrestaurant.Adapter.CervezaAdapter;
import com.example.applicationrestaurant.Adapter.CoctelesAdapter;
import com.example.applicationrestaurant.Adapter.ComidaAdapter;
import com.example.applicationrestaurant.Entities.Cervezas;
import com.example.applicationrestaurant.Entities.Cocteles;
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
    //TABLAS DE LA ENTIDAD COMIDA

    //Comida referencia a la entidad COMIDA
    public void insertDataComida(Comida comida){
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
    public void getDataComida(ComidaAdapter adapter, ArrayList<Comida> list){
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
    public void deleteDataComida(String id){
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
    public void updateDataComida(Comida comida){
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


    //TABLAS DE LA ENTIDAD COCTELES

    //Cocteles referencia a la entidad COCTELES
    public void insertDataCocteles(Cocteles cocteles){
        Map<String, Object> prod = new HashMap<>();
        prod.put("id", cocteles.getId());
        prod.put("name", cocteles.getName());
        prod.put("description", cocteles.getDescription());
        prod.put("price", cocteles.getPrice());
        prod.put("image", cocteles.getImage());

        // Add a new document with a generated ID
        //"food" es la tabla de la BD
        db.collection("coctel").add(prod);
    }

    //CONSULTAR PRODUCTOS
    public void getDataCocteles(CoctelesAdapter adapter, ArrayList<Cocteles> list){
        db.collection("coctel")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Cocteles cocteles = new Cocteles(
                                        document.getData().get("id").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        Integer.parseInt(document.getData().get("price").toString()),
                                        document.getData().get("image").toString()
                                );
                                list.add(cocteles);
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
    public void deleteDataCocteles(String id){
        db.collection("coctel").whereEqualTo("id", id)
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
    public void updateDataCocteles(Cocteles cocteles){
        db.collection("coctel").whereEqualTo("id", cocteles.getId())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                documentSnapshot.getReference().update(
                                        "name", cocteles.getName(),
                                        "description", cocteles.getDescription(),
                                        "price", cocteles.getPrice(),
                                        "image", cocteles.getImage()
                                );
                            }
                        }
                    }
                });
    }


    //TABLAS DE LA ENTIDAD CERVEZAS

    //Cocteles referencia a la entidad CERVEZAS
    public void insertDataCervezas(Cervezas cervezas){
        Map<String, Object> prod = new HashMap<>();
        prod.put("id", cervezas.getId());
        prod.put("name", cervezas.getName());
        prod.put("contentAlcohol", cervezas.getContentAlcohol());
        prod.put("price", cervezas.getPrice());

        // Add a new document with a generated ID
        //"food" es la tabla de la BD
        db.collection("cerveza").add(prod);
    }

    //CONSULTAR PRODUCTOS
    public void getDataCerveza(CervezaAdapter adapter, ArrayList<Cervezas> list){
        db.collection("cerveza")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Cervezas cervezas = new Cervezas(
                                        document.getData().get("id").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("contentAlcohol").toString(),
                                        Integer.parseInt(document.getData().get("price").toString())

                                );
                                list.add(cervezas);
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
    public void deleteDataCerveza(String id){
        db.collection("cerveza").whereEqualTo("id", id)
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
    public void updateDataCerveza(Cervezas cervezas){
        db.collection("cerveza").whereEqualTo("id", cervezas.getId())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                documentSnapshot.getReference().update(
                                        "name", cervezas.getName(),
                                        "contentAlcohol", cervezas.getContentAlcohol(),
                                        "price", cervezas.getPrice()
                                );
                            }
                        }
                    }
                });
    }
}
