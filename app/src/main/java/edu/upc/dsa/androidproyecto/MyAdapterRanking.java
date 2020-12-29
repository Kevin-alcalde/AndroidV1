package edu.upc.dsa.androidproyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterRanking extends RecyclerView.Adapter<MyAdapterRanking.ViewHolder> {

    private List<Jugador> jugadores;
    private Context context;

    public void setData(List<Jugador> jugadores){
        this.jugadores = jugadores;
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public MyAdapterRanking.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyAdapterRanking.ViewHolder(LayoutInflater.from(context).inflate(R.layout.rowranking_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterRanking.ViewHolder holder, int position) {
        Jugador jugador = jugadores.get(position);
        String nombre = jugador.getName();
        int nivel = jugador.getLevel1();

        holder.name.setText(nombre);
        holder.level.setText(nivel);


    }

    @Override
    public int getItemCount() {
       return jugadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView level;

        public View layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            name = itemView.findViewById(R.id.nameRow);
            level = itemView.findViewById(R.id.levelRow);



        }
    }
}
