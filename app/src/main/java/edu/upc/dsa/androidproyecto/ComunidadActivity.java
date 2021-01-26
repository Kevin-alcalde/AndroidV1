package edu.upc.dsa.androidproyecto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComunidadActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    EditText username;
    TextView name;
    TextView mail;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunidad);
        toolbar = findViewById(R.id.toolbar);
        name=findViewById(R.id.nombre1);
        mail=findViewById(R.id.mail);
        recyclerView = findViewById(R.id.recyclerview);
        username=findViewById(R.id.username);
        buscar = findViewById(R.id.buscar1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        getAllusers();
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Jugador> call = ApiClient.getUserService().getJugadorByUserName(username.getText().toString());
                call.enqueue(new Callback<Jugador>() {
                    @Override
                    public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                        name.setText(response.body().getName());
                        mail.setText(response.body().getMail());
                    }

                    @Override
                    public void onFailure(Call<Jugador> call, Throwable t) {
                        Log.i("G4","THE CODE IS"+t);
                        AlertDialog alertDialog = new AlertDialog.Builder (ComunidadActivity.this).create();
                        alertDialog.setTitle("Error conexión");
                        alertDialog.setMessage("OK");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();



                                    }
                                });
                        alertDialog.show();

                    }
                });

            }
        });
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
                Log.i("G4","THE CODE IS"+t);
                AlertDialog alertDialog = new AlertDialog.Builder (ComunidadActivity.this).create();
                alertDialog.setTitle("Error conexión");
                alertDialog.setMessage("OK");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();



                            }
                        });
                alertDialog.show();

            }
        });


    }
    /*ordenar ascedentemente por nivel*/

    public void ranking (View view)
    {
        Intent intent = new Intent(this, RankingActivity.class);

        startActivity(intent);

    }
}
