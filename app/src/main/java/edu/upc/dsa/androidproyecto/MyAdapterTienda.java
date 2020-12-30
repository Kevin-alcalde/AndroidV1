package edu.upc.dsa.androidproyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterTienda extends RecyclerView.Adapter<MyAdapterTienda.ViewHolder> {

    private List<Item> items;
    private Context context;

    public void setData(List<Item> items){
        this.items = items;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyAdapterTienda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyAdapterTienda.ViewHolder(LayoutInflater.from(context).inflate(R.layout.rowtienda_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterTienda.ViewHolder holder, int position) {
        Item item = items.get(position);
        String nombre = item.getName();
        String cantidad = item.getTotal();

        holder.name.setText(nombre);
        holder.cantidad.setText(cantidad);



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView cantidad;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            name = itemView.findViewById(R.id.nameItem);
            cantidad = itemView.findViewById(R.id.cantidad);

        }
    }
}
