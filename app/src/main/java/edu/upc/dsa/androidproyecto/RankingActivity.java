package edu.upc.dsa.androidproyecto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    MyAdapterRanking myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
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
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                if (response.isSuccessful()) {
                    List<Jugador> jugadores = response.body();
                    ordenarNivelDesc(jugadores);
                    myAdapter = new MyAdapterRanking();  /*CORRECCION ARRIBA SE HA DECLARADO Y AQUI SE HA CREADO LA INSTANCIA*/
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Jugador> ordenarNivelDesc(List<Jugador> jugadoresAsc)
    {
        jugadoresAsc.sort(new Comparator<Jugador>() {
            @Override
            public int compare(Jugador o1, Jugador o2) {
                return o1.getLevel1()-o2.getLevel1();
            }
        });
        return jugadoresAsc;
    }
}


