package edu.upc.dsa.androidproyecto;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JugadorAdapter  {


    public Jugador jugador;
    private Context context;


    public void setData(Jugador jugador) {
        this.jugador = jugador;

    }



    public class UserAdapterVH {
        TextView id;
        TextView username;
        TextView mail;
        TextView name;
        TextView lastname;
        TextView city;
        TextView password;
        TextView life;
        TextView level;



        }


    }
