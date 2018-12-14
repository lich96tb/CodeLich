package com.example.abc.viewmodelabc.screentow;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc.viewmodelabc.R;
import com.example.abc.viewmodelabc.viewmodel.ScoreViewModel;
import com.example.abc.viewmodelabc.viewmodel.ViewmodelFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentDeplay extends Fragment {
    private ViewmodelFragment viewmodelFragment;
    private TextView txtScoreC,getTxtScoreD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_deplay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewmodelFragment= ViewModelProviders.of(getActivity()).get(ViewmodelFragment.class);
        txtScoreC=view.findViewById(R.id.txtScoresC);
        getTxtScoreD=view.findViewById(R.id.txtScoresD);
        txtScoreC.setText(viewmodelFragment.ScoreC+"");
        getTxtScoreD.setText(viewmodelFragment.ScoreD+"");

    }
}
