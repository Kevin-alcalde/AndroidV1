package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {
    JugadorAdapter jugadorAdapter;
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
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {

            }
        });



      //  username.setText(jugador.getUsername());










       /* String idRecibido = "Kevin";
        String singer = "Kevin";
        Log.i("G4",idRecibido +" "+ singer);*/

       /* Track track1 = null;*/
      /*  Bundle miBundle = this.getIntent().getExtras();


        username.setText(registrado);

        String nombre = miBundle.getString("user");
        String nombre2 = miBundle.getString("password");
        Log.i("G4",nombre +" "+ nombre2);


        Jugador jugador = new Jugador (nombre,nombre2);
        Call<Jugador> call = ApiClient.getUserService().createPostLogin(jugador);

        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {

                    int code = response.code();
                    Log.i("G4", "codigo"+ " "+ code);

                if(code == 201) {
                    Jugador jugadorRecibido = response.body();
                    username.setText(jugadorRecibido.getUsername());
                    mail.setText(jugadorRecibido.getMail());
                    lastname.setText(jugadorRecibido.getLastname());
                    name.setText(jugadorRecibido.getName());
                    city.setText(jugadorRecibido.getCity());
                }
                else if (code == 404)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder (PerfilActivity.this).create();
                    alertDialog.setTitle("Error: "+ code );
                    alertDialog.setMessage("404: Aun no te has registrado. Registrate!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else if (code == 500)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder (PerfilActivity.this).create();
                    alertDialog.setTitle("Error: "+ code );
                    alertDialog.setMessage("Password erróneo. Inténtalo otra vez!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }






            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {

            }
        });





         /*



        Track track = new Track (nombre,nombre2,"cancion");
        Call<Track> call = ApiClient.getUserService().agregarTrack(track);
        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {

                Track trackRecibido = response.body();
                id.setText(trackRecibido.getId());
                username.setText(trackRecibido.getSinger());
                name.setText(trackRecibido.getTitle());

            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {

            }
        });




*/


    }





}