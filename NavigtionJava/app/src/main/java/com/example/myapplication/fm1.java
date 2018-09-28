package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class fm1 extends Fragment {
    private Button btnNext;
    private EditText edt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fm1, container, false);
        btnNext = view.findViewById(R.id.btnfm1);
        edt = view.findViewById(R.id.edtfm1);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nameArg", edt.getText().toString());
                Navigation.findNavController(view).navigate(R.id.fmNext,bundle);
            }
        });


    }
}
