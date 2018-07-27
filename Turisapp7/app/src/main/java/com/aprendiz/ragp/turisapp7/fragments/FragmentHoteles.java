package com.aprendiz.ragp.turisapp7.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;

import com.aprendiz.ragp.turisapp7.R;
import com.aprendiz.ragp.turisapp7.models.GestorDB;
import com.aprendiz.ragp.turisapp7.models.Lugares;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHoteles extends Fragment {
    RecyclerView recyclerView;
    int position;
    int valor;

    public FragmentHoteles() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_hoteles, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        inputAdapter();

        return view;
    }

    private void inputAdapter() {
        position= getActivity().getWindowManager().getDefaultDisplay().getRotation();
        GestorDB gestorDB = new GestorDB(getContext());
        List<Lugares> lugaresList = gestorDB.hotelesList();
        
        if (position== Surface.ROTATION_0 || position==Surface.ROTATION_180){

        }else {

        }
    }

}
