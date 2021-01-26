package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilRecyclerViewActivity extends AppCompatActivity {
    TextView id;
    TextView username;
    TextView mail;
    TextView name;
    TextView lastname;
    TextView city;
    TextView password;
    TextView life;
    TextView level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_recycler_view);
        username=findViewById(R.id.usernameVal);
        mail=findViewById(R.id.mailVal);
        name=findViewById(R.id.nameVal);
        lastname=findViewById(R.id.lastnameVal);
        city=findViewById(R.id.cityVal);
        Bundle recibido =  getIntent().getExtras();
        String user = recibido.getString("username");
        Call<Jugador> call = ApiClient.getUserService().getJugadorByUserName(user);
        Jugador jugador;
        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                final Jugador jugador;
                jugador = response.body();
                username.setText(jugador.getUsername());

                name.setText(jugador.getName());
                lastname.setText(jugador.getLastname());
                city.setText(jugador.getCity());


            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Log.e("G4",""+t);

            }
        });
    }
}