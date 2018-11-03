package com.melodymediation.sleepstories.service;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.ui.MainActivity;

import java.util.Date;

/**
 * Created by AMBE on 10/15/2018 at 6:05 PM.
 */
public class AlertDialogClass extends Activity {

    private AlertDialog.Builder mAlertDlgBuilder;
    private AlertDialog mAlertDialog;
    private View mDialogView = null;
    private TextView txtTime;
    private Button mOKBtn;
    private SharedPreferences preferences;
    private final static String PRE_NAME = "SETTINGS";
    private final static String TIME_REMINDER = "1203";
    private String timeReminder;
    private Date date;


    private String convertAMPMto24(String txt) {
        String suffix = txt.substring(txt.length() - 2);
        txt = txt.replace(suffix, "");
        String[] hms = txt.split(":");
        if (suffix.equals("PM") || suffix.equals("pm")) {
            hms[0] = String.valueOf(Integer.parseInt(hms[0]) + 12);
            hms[0] = hms[0].equals("24") ? "12" : hms[0];
        } else if (suffix.equals("AM") || suffix.equals("am")) {
            hms[0] = hms[0].equals("12") ? "00" : hms[0];
        }

        return (hms[0] + ":" + hms[1]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(PRE_NAME, MODE_PRIVATE);
        timeReminder = preferences.getString(TIME_REMINDER, "");
        LayoutInflater inflater = getLayoutInflater();

        // Build the dialog
        mAlertDlgBuilder = new AlertDialog.Builder(this);
        mDialogView = inflater.inflate(R.layout.dialog_go_to_bed, null);
        mOKBtn = mDialogView.findViewById(R.id.btn_ok_go_to_bed);
        txtTime = mDialogView.findViewById(R.id.txt_time_go_to_bed);
        mOKBtn.setOnClickListener(mDialogbuttonClickListener);
        mAlertDlgBuilder.setCancelable(false);
        mAlertDlgBuilder.setInverseBackgroundForced(true);
        mAlertDlgBuilder.setView(mDialogView);
        mAlertDialog = mAlertDlgBuilder.create();
        if (timeReminder != null && !timeReminder.equals("")) {
            txtTime.setText(convertAMPMto24(timeReminder));
        } else {
            txtTime.setText("22:30");
        }
        if (getWindow() != null)
            mAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        try {
            mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        } catch (Exception ignored) {

        }
        mAlertDialog.show();

    }

    View.OnClickListener mDialogbuttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_ok_go_to_bed) {
                Intent intent = new Intent(AlertDialogClass.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
