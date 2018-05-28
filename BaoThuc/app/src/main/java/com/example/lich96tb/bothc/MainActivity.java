package com.example.lich96tb.bothc;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private TextView txtHienThi;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        calendar = Calendar.getInstance();

        intent = new Intent(this, AlarmRecever.class);
    }

    private void anhxa() {
        timePicker = findViewById(R.id.timePicker);
        txtHienThi = findViewById(R.id.txtHienThi);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    // sự kiện khi click vào button
    public void clickButton(View view) {
        switch (view.getId()) {
            case R.id.btnDungLai:
                txtHienThi.setText("stop");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra","off");
                sendBroadcast(intent);
                break;
            case R.id.btnHenGio:
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                int gio = timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();
                String string_phut;
                if (phut < 10) {
                    string_phut = "0" + phut;
                } else {
                    string_phut = phut + "";
                }
                String tring_gio = gio + "";
                intent.putExtra("extra", "on");
               // sendBroadcast(intent);
                pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Toast.makeText(this, "thoi gian toast ra: "+calendar.getTimeInMillis(), Toast.LENGTH_SHORT).show();
                txtHienThi.setText(tring_gio + ":" + string_phut);
                break;
        }
    }
}
