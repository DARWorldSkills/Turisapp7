package com.aprendiz.ragp.turisapp7.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.turisapp7.R;
import com.aprendiz.ragp.turisapp7.controllers.Detalle;
import com.aprendiz.ragp.turisapp7.controllers.MenuT;
import com.aprendiz.ragp.turisapp7.models.AdapterT;
import com.aprendiz.ragp.turisapp7.models.GestorDB;
import com.aprendiz.ragp.turisapp7.models.Lugares;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHoteles extends Fragment {
    RecyclerView recyclerView;
    Button btnCambio;
    int position;
    int valor;
    Lugares lugar;
    public FragmentHoteles() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_hoteles, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        inputAdapter(view);


        if (position== Surface.ROTATION_0 || position==Surface.ROTATION_180) {
            btnCambio = view.findViewById(R.id.btnCambio);
            btnCambio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (valor) {
                        case 0:
                            valor = 1;
                            inputAdapter(v);
                            break;
                        case 1:
                            valor = 0;
                            inputAdapter(v);
                            break;
                    }
                }
            });

        }
        return view;
    }


    private void inputAdapter(final View view) {
        position= getActivity().getWindowManager().getDefaultDisplay().getRotation();
        GestorDB gestorDB = new GestorDB(getContext());
        final List<Lugares> lugaresList = gestorDB.hotelesList();
        
        if (position== Surface.ROTATION_0 || position==Surface.ROTATION_180){
            if (valor==0){
                AdapterT adapterT = new AdapterT(lugaresList,R.layout.item_list,getContext());
                recyclerView.setAdapter(adapterT);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setHasFixedSize(true);
                adapterT.setMlistener(new AdapterT.OnItemClickListener() {
                    @Override
                    public void itemClick(int position) {
                        lugar = lugaresList.get(position);
                        MenuT.lugar =lugar;
                        Intent intent = new Intent(getActivity(), Detalle.class);
                        startActivity(intent);
                    }
                });
            }else {
                AdapterT adapterT = new AdapterT(lugaresList,R.layout.item_grid,getContext());
                recyclerView.setAdapter(adapterT);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
                recyclerView.setHasFixedSize(true);
                adapterT.setMlistener(new AdapterT.OnItemClickListener() {
                    @Override
                    public void itemClick(int position) {
                        lugar = lugaresList.get(position);
                        MenuT.lugar =lugar;
                        Intent intent = new Intent(getActivity(), Detalle.class);
                        startActivity(intent);
                    }
                });
            }
        }else {

            AdapterT adapterT = new AdapterT(lugaresList,R.layout.item_land,getContext());
            recyclerView.setAdapter(adapterT);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);
            adapterT.setMlistener(new AdapterT.OnItemClickListener() {
                @Override
                public void itemClick(int position) {
                    lugar = lugaresList.get(position);
                    MenuT.lugar =lugar;
                    inputLand(view);


                }
            });

        }
    }



    private void inputLand(View view) {
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcionLand);
        txtDescripcion.setText(lugar.getDescripcion());
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize=3;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),lugar.getImagen(),op);
        ImageView imagen = view.findViewById(R.id.imgLand);
        imagen.setImageBitmap(bitmap);
    }

}
