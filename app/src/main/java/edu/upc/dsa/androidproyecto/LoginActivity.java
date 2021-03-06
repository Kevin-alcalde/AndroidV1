package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.unity3d.player.UnityPlayerActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Jugador jugador;
    EditText editTextUser;
    EditText editTextPassword;
    Button btnLogin;
    Button btnRegistrar;
    int code;
    String user = null ;
    String pass = null ;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUser=findViewById(R.id.userNameLog);
        editTextPassword=findViewById(R.id.passwordLog);
        btnLogin=findViewById(R.id.buttonLogin);
        btnRegistrar=findViewById(R.id.buttonRegister);
        progressBar=findViewById(R.id.progressBar);
        cargarSharedPreference();

    }

    private void cargarSharedPreference() {
        SharedPreferences preferences = getSharedPreferences("credenciales",Context.MODE_PRIVATE);
        user = preferences.getString("user","No hay nada");
        pass = preferences.getString("password","no hay nada");
        progressBar.setVisibility(View.VISIBLE);
        Bundle bundle  = new Bundle();
        Intent login = new Intent(this, DashboardActivity.class);/*    Cambiar a que vaya al Dashboard   */
        progressBar.setVisibility(View.VISIBLE);
        if (user != null &&(pass != null)) {
            jugador = new Jugador(user, pass, true);
            Call<Jugador> call = ApiClient.getUserService().createPostLogin(jugador);
            call.enqueue(new Callback<Jugador>() {
                @Override
                public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                    code = response.code();
                    if (code == 201) {
                        /*    Cambiar a que vaya al Dashboard   */
                        bundle.putString("nombreLogin",response.body().getName());
                        bundle.putString("user",response.body().getUsername());
                        bundle.putString("password",response.body().getPassword());
                        bundle.putSerializable("usuario", response.body());

                        Log.i("G8",""+response.body().getName());
                        Log.i("G8",""+response.body().getUsername());
                        Log.i("G8",""+response.body().getPassword());
                        Log.i("G8",""+response.body().getName());
                        Log.i("G8",""+response.body().getImage());
                        Log.i("G8",""+response.body().getCity());
                        Log.i("G8",""+response.body().getMail());
                        Log.i("G8",""+response.body().getLevel());

                        login.putExtras(bundle);
                        startActivity(login);
                        progressBar.setVisibility(View.INVISIBLE);


                    }
                }
                @Override
                public void onFailure(Call<Jugador> call, Throwable t) {
                    AlertDialog alertDialog = new AlertDialog.Builder (LoginActivity.this).create();
                    alertDialog.setTitle("Error: "+ t );
                    alertDialog.setMessage("No conxión");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });
                    alertDialog.show();
                }
            });
        }
    }
    /*Metodo para el boton Registrar*/
    public void Registrar(View view){
        Intent registrar = new Intent(this,ResgisterActivity.class);/*Le enseñamos de donde partimos y hacia donde vamos*/
        startActivity(registrar);
    }
    /*Metodo para el boton Logearse ponerlo en otro if*/
    public void Login(View view){
    Bundle bundle  = new Bundle();
    this.user= editTextUser.getText().toString();
    this.pass = editTextPassword.getText().toString();
    bundle.putString("user",editTextUser.getText().toString());
    bundle.putString("password",editTextPassword.getText().toString());

    jugador = new Jugador(editTextUser.getText().toString(),editTextPassword.getText().toString(),true);
    Call<Jugador> call = ApiClient.getUserService().createPostLogin(jugador);
        progressBar.setVisibility(View.VISIBLE);
    Intent login = new Intent(this, DashboardActivity.class);
    call.enqueue(new Callback<Jugador>() {
        @Override
        public void onResponse(Call<Jugador> call, Response<Jugador> response) {
            code = response.code();
            Log.i("G4", "codigo"+ " "+ code);
            if (code == 201) {
                if (editTextUser.getText().toString() != "" &&(editTextPassword.getText().toString()!="")) {
                    SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("user", editTextUser.getText().toString());
                    editor.putString("password", editTextPassword.getText().toString());
                    editor.commit();
                }


                bundle.putString("nombreLogin",response.body().getName());

                /*bundle.putString("passwordLogin",response.body().getPassword()); */

                login.putExtras(bundle);
                startActivity(login);
                progressBar.setVisibility(View.INVISIBLE);

            }
            else if (code == 404) {
                AlertDialog alertDialog = new AlertDialog.Builder (LoginActivity.this).create();
                alertDialog.setTitle("Error: "+ code );
                alertDialog.setMessage("El usuario no esta registrado");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            else if (code == 500) {
                AlertDialog alertDialog = new AlertDialog.Builder (LoginActivity.this).create();
                alertDialog.setTitle("Error: "+ code );
                alertDialog.setMessage("Contraseña incorrecta");
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

            AlertDialog alertDialog = new AlertDialog.Builder (LoginActivity.this).create();
            alertDialog.setTitle("Error: "+ t );
            alertDialog.setMessage("No conxión");
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
}