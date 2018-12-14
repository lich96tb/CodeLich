package com.example.abc.viewmodelabc.screentow;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.viewmodelabc.R;
import com.example.abc.viewmodelabc.viewmodel.ScoreViewModel;
import com.example.abc.viewmodelabc.viewmodel.ViewmodelFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEdit extends Fragment implements View.OnClickListener {
    private ViewmodelFragment viewmodelFragment;
    private TextView txtScoreC, txtScoreD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtScoreC = view.findViewById(R.id.txtCoreC);
        txtScoreD = view.findViewById(R.id.txtCoreD);
        view.findViewById(R.id.btnScoreC).setOnClickListener(this);
        view.findViewById(R.id.btnScoreD).setOnClickListener(this);
        view.findViewById(R.id.btnDisplay).setOnClickListener(this);
        viewmodelFragment = ViewModelProviders.of(getActivity()).get(ViewmodelFragment.class);
        disPlayDcoreC(viewmodelFragment.ScoreC);
        disPlayDcoreD(viewmodelFragment.ScoreD);


    }

    private void disPlayDcoreD(int scoreD) {
        txtScoreD.setText("" + scoreD);
    }

    private void disPlayDcoreC(int scoreC) {
        txtScoreC.setText("" + scoreC);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnScoreC:
                viewmodelFragment.ScoreC = viewmodelFragment.ScoreC + 1;
                disPlayDcoreC(viewmodelFragment.ScoreC);
                break;
            case R.id.btnScoreD:
                viewmodelFragment.ScoreD = viewmodelFragment.ScoreD + 1;
                disPlayDcoreD(viewmodelFragment.ScoreD);
                break;
            case R.id.btnDisplay:
                getFragmentManager().beginTransaction().replace(R.id.fragmMain,new fragmentDeplay()).commit();
                break;
        }
    }
}
