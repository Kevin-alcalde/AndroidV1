package edu.upc.dsa.androidproyecto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Jugador> jugadores;
    private Context context ;





    public void setData (List<Jugador> jugadores){
        this.jugadores = jugadores;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Jugador jugador = jugadores.get(position);
        String nombre = jugador.getUsername();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),PerfilRecyclerViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",jugador.getUsername());
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        Call<Jugador> call = ApiClient.getUserService().getJugadorByUserName(nombre);
        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                String URL_IMG ="http://localhost:8080"+response.body().getImage();
                Log.i("G30",""+URL_IMG);
                Glide.with(context).load(URL_IMG).into(holder.imageView);
                holder.lastname.setText(response.body().getLastname());
                holder.name.setText(response.body().getUsername());
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Log.e("G4", ""+t);

            }
        });

       /* String apellido = jugador.getPassword();
        Log.i("G11","JUGADORES" + jugador.getImage());
        String URL_IMG="http://localhost:8080/"+jugador.getImage();
        Glide.with(context).load(URL_IMG).into(holder.imageView);
        holder.name.setText(nombre);
        holder.lastname.setText(apellido);*/



    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }



    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView name;
        TextView lastname;
        ImageView imageView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            layout = itemView;
            name = itemView.findViewById(R.id.nameRow);
            lastname = itemView.findViewById(R.id.lastnameRow);
            imageView = itemView.findViewById(R.id.icon);
        }
    }
}
