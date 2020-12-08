package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResgisterActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    EditText mailText;
    EditText nameText;
    EditText lastnameText;
    EditText cityText;
    TextView resultado;
    String username;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);
        usernameText=findViewById(R.id.UsernameText);
        passwordText=findViewById(R.id.PasswordText);
        resultado=findViewById(R.id.result);
        mailText=findViewById(R.id.MailText);
        lastnameText=findViewById(R.id.LastnameText);
        cityText=findViewById(R.id.CityText);
        nameText=findViewById(R.id.NameText);







    }

    /*Metodo para el boton Volver*/
    public void Volver(View view){
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);

    }
    /*Metodo despues de registrarse ir al pergil si ha ido bien! ponerlo dentro de un if*/
    public void Registrado(View view){

      /*
        Bundle bundle = new Bundle();
        bundle.putString("user", usernameText.getText().toString());
        bundle.putString("password", usernameText.getText().toString());
        registrado.putExtras(bundle);*/



        Jugador jugador = new Jugador (usernameText.getText().toString(),passwordText.getText().toString(),mailText.getText().toString(),nameText.getText().toString(),lastnameText.getText().toString(),cityText.getText().toString());
        Call<Jugador> call = ApiClient.getUserService().createPostRegister(jugador);

        ////////////////////////////////////////////////////
        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {

                code = response.code(); /*No marca el 201*/

                Log.i("G4", "codigo"+ " "+ code);

                if (code == 201)
                {

                    AlertDialog alertDialog = new AlertDialog.Builder (ResgisterActivity.this).create();
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
                else if (code == 500) /*Si lo marca*/
                {
                    AlertDialog alertDialog = new AlertDialog.Builder (ResgisterActivity.this).create();
                    alertDialog.setTitle("Error: "+ code );
                    alertDialog.setMessage("500: Ya estas registrado");
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
/////////////////////////////////
        resultado.setText("Registrado Correctamente");



        /*Intent registrado = new Intent(this,PerfilActivity.class);*/

       /* Bundle bundle = new Bundle();
        bundle.putString("prueba1",username);*/
        /*startActivity(registrado);*/



    }
}