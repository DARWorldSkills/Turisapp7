package com.aprendiz.ragp.turisapp7.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aprendiz.ragp.turisapp7.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInicio extends Fragment {


    public FragmentInicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_inicio, container, false);
    }

}
