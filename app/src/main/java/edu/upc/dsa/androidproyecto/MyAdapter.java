package edu.upc.dsa.androidproyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Jugador> jugadores;
    private Context context;



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
        String apellido = jugador.getPassword();

        holder.name.setText(nombre);
        holder.lastname.setText(apellido);


    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }



    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView name;
        TextView lastname;
        public View layout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            layout = itemView;
            name = itemView.findViewById(R.id.nameRow);
            lastname = itemView.findViewById(R.id.lastnameRow);
        }
    }
}
