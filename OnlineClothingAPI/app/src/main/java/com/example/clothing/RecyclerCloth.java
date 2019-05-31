package com.example.clothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;


import clothapi.ClothesAPI;
import model.Clothes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.Url;

public class RecyclerCloth extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Clothes> clothesList;
    private Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_clothes);
        recyclerView=findViewById(R.id.recyclerView);
        btnadd=findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerCloth.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        clothesList= new ArrayList<>();
        ClothesAPI clothesAPI = Url.getInstance().create(ClothesAPI.class);
        Call<List<Clothes>> call = clothesAPI.getAllCloth(Url.token);
        call.enqueue(new Callback<List<Clothes>>() {
            @Override
            public void onResponse(Call<List<Clothes>> call, Response<List<Clothes>> response) {

                clothesList = response.body();
                ClothesAdapter clothesAdapter = new ClothesAdapter(clothesList, RecyclerCloth.this);
                recyclerView.setAdapter(clothesAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerCloth.this));
            }

            @Override
            public void onFailure(Call<List<Clothes>> call, Throwable t) {

            }
        });






    }
}
