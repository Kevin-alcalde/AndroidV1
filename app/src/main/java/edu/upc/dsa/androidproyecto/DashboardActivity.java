package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

        TextView nombre;
        String name;
        String username;
        String password;
        String ciudad;
        Jugador jugador;
        Bundle bundle = new Bundle();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_dashboard);
            nombre = findViewById(R.id.Usuario);
            Bundle Recibo = getIntent().getExtras();
            name = Recibo.getString("nombreLogin");
            nombre.setText(name);


            jugador = new Jugador(Recibo.getString("user"), Recibo.getString("password"));
            bundle.putSerializable("prueba1",jugador);


        }

        public void Loger (View view) {
            Intent intent = new Intent(this, PerfilActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }

    public void BuscarAmigo (View view) {
        Intent intent = new Intent(this,BuscadorActivity.class);
        startActivity(intent);

    }

    public void Comunidad (View view) {
            Intent intent = new Intent(this,ComunidadActivity.class);
            startActivity(intent);

    }

    public void Ranking (View view) {
            Intent intent = new Intent (this, RankingActivity.class);
            startActivity(intent);
    }
    public void Tienda (View view) {
            Intent intent= new Intent (this, TiendaActivity.class);
            startActivity(intent);

    }


}