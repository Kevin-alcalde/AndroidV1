package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
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


    }


    public void editar(View view){

       Jugador jugador = new Jugador (id.getText().toString(),username.getText().toString(),mail.getText().toString(),name.getText().toString(),lastanme.getText().toString(),city.getText().toString(),password.getText().toString(),life.getText().toString(),level.getText().toString());
        Call<Jugador> call = ApiClient.getUserService().actualizarJugador(jugador);
        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                if (response.code()==201 ){
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
            public void onFailure(Call<Jugador> call, Throwable t) {

            }
        });




    }
}