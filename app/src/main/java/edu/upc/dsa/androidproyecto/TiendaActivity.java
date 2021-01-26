package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TiendaActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    MyAdapterTienda myAdapter;
    List<Item> items;
    public String nombreItem;
    public String cantidadItem;
    EditText buscador;
    TextView name;
    TextView cantidadbuscado;
    Button buttonBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        toolbar = findViewById(R.id.toolbar);
        buscador=findViewById(R.id.plainobjeto);
        name=findViewById(R.id.name);
        cantidadbuscado=findViewById(R.id.cantidadbuscado);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        buttonBuscar=findViewById(R.id.buscarObjeto);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        getAllItems();
        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Item> call = ApiClient.getUserService().dameItem(buscador.getText().toString());
                call.enqueue(new Callback<Item>() {
                    @Override
                    public void onResponse(Call<Item> call, Response<Item> response) {
                        name.setText(response.body().getName());
                        cantidadbuscado.setText(response.body().getTotal());
                    }

                    @Override
                    public void onFailure(Call<Item> call, Throwable t) {

                    }
                });

            }
        });

    }

    private void getAllItems() {
        Call<List<Item>> itemList = ApiClient.getUserService().getItems();
        itemList.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                myAdapter = new MyAdapterTienda();
                items = response.body();
                myAdapter.setData(items);
                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
            Log.e("G4",""+t);
            }
        });

    }

    public void getItem(View view)
    {
        Call<Item> call = ApiClient.getUserService().dameItem(buscador.getText().toString());
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                name.setText(response.body().getName());
                cantidadbuscado.setText(response.body().getTotal());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });

    }




}