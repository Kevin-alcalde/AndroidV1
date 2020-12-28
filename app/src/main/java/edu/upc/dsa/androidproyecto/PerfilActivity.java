package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    TextView id;
    TextView username;
    TextView mail;
    TextView name;
    TextView lastname;
    TextView city;
    TextView password;
    TextView life;
    TextView level;
    EditText editTextUser;
    EditText editTextPassword;
    Button btnLogin;
    Button btnRegistrar;
    Track track = new Track();
    Jugador jugador = null;
    String user = null;
    String ciudad =null;
    Bundle bundle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        editTextUser=findViewById(R.id.userNameLog);
        editTextPassword=findViewById(R.id.passwordLog);
        btnLogin=findViewById(R.id.buttonLogin);
        btnRegistrar=findViewById(R.id.buttonRegister);

        id= findViewById(R.id.idVal);
        username=findViewById(R.id.usernameVal);
        mail=findViewById(R.id.mailVal);
        name=findViewById(R.id.nameVal);
        lastname=findViewById(R.id.lastnameVal);
        city=findViewById(R.id.cityVal);
        life=findViewById(R.id.lifeVal);
        level=findViewById(R.id.levelVal);
        Bundle datosEnviados = getIntent().getExtras();
        Jugador jugador = (Jugador)datosEnviados.getSerializable("prueba1");

        Call<Jugador> call = ApiClient.getUserService().createPostLogin(jugador);

        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {


                id.setText(response.body().getId());
                username.setText(response.body().getUsername());
                mail.setText(response.body().getMail());
                name.setText(response.body().getName());
                lastname.setText(response.body().getLastname());
                city.setText(response.body().getCity());
                life.setText(response.body().getLife());
                level.setText(response.body().getLevel());
                bundle = new Bundle();
                bundle.putString("username",response.body().getUsername());
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {

            }
        });



    }

    public void editarActivity (View view) {
        Intent intent = new Intent(this,EditarperfilActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }






}