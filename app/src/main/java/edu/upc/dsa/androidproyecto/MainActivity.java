package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Jugador jugador;
    Jugador jugador2;
    EditText editTextUser;
    EditText editTextPassword;
    Button btnLogin;
    Button btnRegistrar;
    UserService userService;
    TextView id;
    TextView username;
    TextView mail;
    TextView name;
    TextView lastname;
    TextView city;
    TextView password;
    TextView life;
    TextView level;
    JugadorAdapter jugadorAdapter;
    Track track;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUser=findViewById(R.id.userNameLog);
        editTextPassword=findViewById(R.id.passwordLog);
        btnLogin=findViewById(R.id.buttonLogin);
        btnRegistrar=findViewById(R.id.buttonRegister);
        id=findViewById(R.id.idVal);
        username=findViewById(R.id.usernameVal);
        mail=findViewById(R.id.mailVal);
        name=findViewById(R.id.nameVal);
        lastname=findViewById(R.id.lastnameVal);
        city=findViewById(R.id.cityVal);
        life=findViewById(R.id.lifeVal);
        level=findViewById(R.id.levelVal);
        jugadorAdapter = new JugadorAdapter();
        jugador = new Jugador();
        jugador2 = new Jugador();
        track = new Track();




    }
     /*Metodo para el boton Registrar*/
    public void Registrar(View view){
        Intent registrar = new Intent(this,ResgisterActivity.class);/*Le enseñamos de donde partimos y hacia donde vamos*/
        startActivity(registrar);
    }
    /*Metodo para el boton Logearse ponerlo en otro if*/
    public void Login(View view){


        /*Track track = new Track(editTextUser.toString(),editTextPassword.toString(),"prueba1");
        Call<Track> call = ApiClient.getUserService().agregarTrack(track); */

       /* call.enqueue(new Callback<Track>() {
            Track track = null;

            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {


                track = response.body();
              /*  Bundle bundle = new Bundle();
                bundle.putSerializable("abs",response.body());


                id.setText(track.getId());
                username.setText(track.getSinger());
                mail.setText(track.getTitle());*/

           /* }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {


            }*/
       // });  / /




       /* Jugador jugador = new Jugador(editTextUser.toString(),editTextUser.toString());

        Call<Jugador> call = ApiClient.getUserService().createPostLogin(jugador);
        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
               Jugador jugador2 = response.body();
               jugadorAdapter.setData(jugador2);*/





              /*
               String id = response.body().getId();
               String username = response.body().getUsername();
               String mail = response.body().getMail();
               String name = response.body().getName();
               String lastname = response.body().getLastname();
               String city = response.body().getCity();
               String password = response.body().getPassword();
               String life = response.body().getLife();
               String level = response.body().getLevel();
                id.setText(jugador2.getId());
                username.setText(jugador2.getUsername());
                mail.setText(jugador2.getMail());
                name.setText(jugador2.getName());
                lastname.setText(jugador2.getLastname());
                city.setText(jugador2.getCity());
                password.setText(jugador2.getPassword());
                life.setText(jugador2.getLife());
                level.setText(jugador2.getLevel());


            }*/

        /*   @Override
            public void onFailure(Call<Jugador> call, Throwable t) {


            }
        });

*/


    Bundle bundle  = new Bundle();
    bundle.putString("user",editTextUser.getText().toString());
    bundle.putString("password",editTextPassword.getText().toString());


    Intent login = new Intent(this, PerfilActivity.class);
    login.putExtras(bundle);




    startActivity(login);
    }


}