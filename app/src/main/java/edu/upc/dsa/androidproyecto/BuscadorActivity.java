package edu.upc.dsa.androidproyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscadorActivity extends AppCompatActivity {
    TextView nombreEtiqueta;
    TextView apellidoEtiqueta;
    TextView correoEtiqueta;
    TextView ciudadEtiqueta;
    TextView nombre;
    TextView apellido;
    TextView correo;
    TextView ciudad;
    EditText buscador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);
        buscador=findViewById(R.id.UsernameText);
        nombreEtiqueta=findViewById(R.id.nombreText);
        apellidoEtiqueta=findViewById(R.id.lastnameText);
        correoEtiqueta=findViewById(R.id.correoText);
        ciudadEtiqueta=findViewById(R.id.ciudadText);
        nombre=findViewById(R.id.nombreVal);
        apellido=findViewById(R.id.apellidoVal);
        correo=findViewById(R.id.correoVal);
        ciudad=findViewById(R.id.ciudadVal);









    }

    public void buscar(View view) {
        Call<Jugador> call = ApiClient.getUserService().getJugadorByUserName(buscador.getText().toString());
        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                if(response.code()==201){
                    nombreEtiqueta.setText("Nombre");
                    apellidoEtiqueta.setText("Apellido");
                    correoEtiqueta.setText("Correo");
                    ciudadEtiqueta.setText("Ciudad");
                    nombre.setText(response.body().getName());
                    apellido.setText(response.body().getLastname());
                    correo.setText(response.body().getMail());
                    ciudad.setText(response.body().getCity());


                }
                else if (response.code()==404){
                    AlertDialog alertDialog = new AlertDialog.Builder (BuscadorActivity.this).create();
                    alertDialog.setTitle("Error: "+ response.code() );
                    alertDialog.setMessage("El usuario no existe");
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