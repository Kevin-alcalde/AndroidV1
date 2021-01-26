package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarperfilActivity extends AppCompatActivity {

    EditText id;
    EditText username;
    EditText mail;
    EditText name;
    EditText lastanme;
    EditText city;
    EditText password;
    EditText life;
    EditText level;
    String usernameEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarperfil);
        id=findViewById(R.id.editId);
        username=findViewById(R.id.editUsername);
        mail=findViewById(R.id.editMail);
        name=findViewById(R.id.editName);
        lastanme=findViewById(R.id.editLastname);
        city=findViewById(R.id.editCity);
        password=findViewById(R.id.editTextTextPersonName6);
        life=findViewById(R.id.editLife);
        level=findViewById(R.id.editLevel);
        Bundle datosEnviados = getIntent().getExtras();
        usernameEliminar = datosEnviados.getString("username");
        Log.i("G4", "nombre del jugador a eliminar :"+""+usernameEliminar);


    }


    public void editar(View view){

       Jugador jugador = new Jugador (username.getText().toString(), mail.getText().toString(), name.getText().toString(), lastanme.getText().toString(), city.getText().toString(), password.getText().toString());
        Call<Void> call = ApiClient.getUserService().actualizarJugador(jugador);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code()==201 ){
                    Log.i("G4","THE CODE IS"+""+response.code());
                    AlertDialog alertDialog = new AlertDialog.Builder (EditarperfilActivity.this).create();
                    alertDialog.setTitle("Todo Correcto");
                    alertDialog.setMessage("OK");
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
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    public void eliminarUsuario(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        Call<Void> call = ApiClient.getUserService().eliminarJugador(usernameEliminar);/* Falta el bunle*/
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code()==201)
                {

                    Log.i("G4","THE CODE IS eliminar"+" "+ response.code());
                    AlertDialog alertDialog = new AlertDialog.Builder (EditarperfilActivity.this).create();
                    alertDialog.setTitle("codigo de operacion:"+""+response.code());
                    alertDialog.setMessage("Se te ha dado de baja correctamente");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    startActivity(intent); }
                            });
                    alertDialog.show();


                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("G4","ELIMINAR JUGADOR",t);

            }
        });


    }



}