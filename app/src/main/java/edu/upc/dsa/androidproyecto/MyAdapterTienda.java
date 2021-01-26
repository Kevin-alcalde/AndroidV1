package edu.upc.dsa.androidproyecto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdapterTienda extends RecyclerView.Adapter<MyAdapterTienda.ViewHolder> implements View.OnClickListener {

    private List<Item> items;
    private Context context;
    public String nombreItem;
    public String cantidadItem;
    public int total;
    private View.OnClickListener listener;

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
        holder.comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cuanto = holder.cantidadAcomprar.getText().toString();

                if (Integer.parseInt(cantidad) >= Integer.parseInt(cuanto)){


                    total = Integer.parseInt(cantidad)-Integer.parseInt(cuanto);
                    Item itemNuevo = new Item(nombre,String.valueOf(total));
                    Call<Void> call = ApiClient.getUserService().comprarItem(itemNuevo);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 201){
                                {
                                    Log.i("G4","THE CODE IS"+""+response.code());
                                    AlertDialog alertDialog = new AlertDialog.Builder (context).create();
                                    alertDialog.setTitle("Todo Correcto");
                                    alertDialog.setMessage("OK");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    dialog.dismiss();
                                                    holder.cantidadAcomprar.setText("");
                                                    String total1 = String.valueOf(total);
                                                    holder.cantidad.setText(total1);






                                                }
                                            });
                                    alertDialog.show();

                                }
                            }





                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
                else if (Integer.parseInt(cantidad) < Integer.parseInt(cuanto)){

                    AlertDialog alertDialog = new AlertDialog.Builder (context).create();
                    alertDialog.setTitle("No puedes comprar tanto");
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
        });

        total = 0;

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView cantidad;
        Button comprar;
        EditText cantidadAcomprar;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            name = itemView.findViewById(R.id.nameItem);
            cantidad = itemView.findViewById(R.id.cantidad);
            comprar = itemView.findViewById(R.id.comprar);
            cantidadAcomprar = itemView.findViewById(R.id.cuantoComprar);

        }
    }
}
