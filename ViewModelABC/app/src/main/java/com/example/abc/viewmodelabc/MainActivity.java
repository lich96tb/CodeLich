package com.example.abc.viewmodelabc;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.abc.viewmodelabc.screentow.Screen2;
import com.example.abc.viewmodelabc.viewmodel.ScoreViewModel;
import com.example.abc.viewmodelabc.viewmodel.ViewmodelFragment;

public class MainActivity extends AppCompatActivity {
    private ScoreViewModel mViewModel;

    private TextView txtTeamA, txtTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);
        displayForTeamA(mViewModel.scoreTeamA);
        displayForTeamB(mViewModel.scoreTeamB);
    }

    private void initView() {
        txtTeamA = findViewById(R.id.txtTeamA);
        txtTeamB = findViewById(R.id.txtTeamB);
    }

    private void displayForTeamB(int scoreTeamB) {
        txtTeamB.setText(scoreTeamB + "");
    }

    private void displayForTeamA(int scoreTeamA) {
        txtTeamA.setText("" + scoreTeamA);

    }

    public void ScoreA(View view) {
        mViewModel.scoreTeamA = mViewModel.scoreTeamA + 1;
        displayForTeamA(mViewModel.scoreTeamA);
    }

    public void SorceB(View view) {
        mViewModel.scoreTeamB = mViewModel.scoreTeamB + 1;
        displayForTeamB(mViewModel.scoreTeamB);
    }

    public void nextScreen(View view) {

    }

    public void nextSceenFragmnet(View view) {
        startActivity(new Intent(this, Screen2.class));
    }
}
