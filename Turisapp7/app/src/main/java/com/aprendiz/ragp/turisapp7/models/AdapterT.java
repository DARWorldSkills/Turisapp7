package com.aprendiz.ragp.turisapp7.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.aprendiz.ragp.turisapp7.R;

import java.util.List;

public class AdapterT extends RecyclerView.Adapter<AdapterT.Holder>{
    List<Lugares> lugaresList;
    int item;
    Context context;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener{
        void itemClick(int position);
    }

    public AdapterT(List<Lugares> lugaresList, int item, Context context) {
        this.lugaresList = lugaresList;
        this.item = item;
        this.context = context;
    }

    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item,parent,false);
        Holder holder = new Holder(view,mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(lugaresList.get(position));
    }

    @Override
    public int getItemCount() {
        return lugaresList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        TextView txtUbicacion;
        TextView txtDescripcion;
        ImageView imagen = itemView.findViewById(R.id.imgItem);
        public Holder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position =getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.itemClick(position);
                        }
                    }
                }
            });
        }

        public void connectData(Lugares lugares){
            if (item==R.layout.item_list){
                txtNombre= itemView.findViewById(R.id.txtNombreItem);
                txtUbicacion= itemView.findViewById(R.id.txtUbicacionItem);
                txtDescripcion= itemView.findViewById(R.id.txtDescripcionItem);

                txtNombre.setText(lugares.getNombre());
                txtUbicacion.setText(lugares.getUbicacion());
                txtDescripcion.setText(lugares.getDescripcionc());

            }


            if (item==R.layout.item_grid){
                txtNombre= itemView.findViewById(R.id.txtNombreItem);
                txtUbicacion= itemView.findViewById(R.id.txtUbicacionItem);

                txtNombre.setText(lugares.getNombre());
                txtUbicacion.setText(lugares.getUbicacion());

            }

            if (item==R.layout.item_land){
                txtNombre= itemView.findViewById(R.id.txtNombreItem);

                txtNombre.setText(lugares.getNombre());

            }

            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inSampleSize=6;
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),lugares.getImagen(),op);
            imagen.setImageBitmap(bitmap);

        }

    }
}
