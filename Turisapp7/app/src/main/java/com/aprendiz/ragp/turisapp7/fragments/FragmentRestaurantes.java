package com.aprendiz.ragp.turisapp7.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aprendiz.ragp.turisapp7.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRestaurantes extends Fragment {
    RecyclerView recyclerView;
    int position;
    int valor;
    public FragmentRestaurantes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_restaurantes, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);


        return view;
    }

}
