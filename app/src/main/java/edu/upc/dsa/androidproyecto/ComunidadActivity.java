package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComunidadActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunidad);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        getAllusers();
    }

    public void getAllusers() {
        Call<List<Jugador>> jugadorList = ApiClient.getUserService().getAllusers();
        jugadorList.enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                if (response.isSuccessful()) {
                    List<Jugador> jugadores = response.body();
                    myAdapter = new MyAdapter();  /*CORRECCION ARRIBA SE HA DECLARADO Y AQUI SE HA CREADO LA INSTANCIA*/
                    Log.i("G4", ""+jugadores);
                    myAdapter.setData(jugadores);
                    recyclerView.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Jugador>> call, Throwable t) {

            }
        });







    }
}