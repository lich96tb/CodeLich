package com.melodymediation.sleepstories.ui.dialogs;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.interfeaces.IOnClickBtnOkDialog;
import com.melodymediation.sleepstories.service.BroadcastStopService;
import com.melodymediation.sleepstories.service.ServiceMedia;
import com.melodymediation.sleepstories.ui.MethodStatic;
import com.melodymediation.sleepstories.ui.view.MyAlertDialog;
import com.melodymediation.sleepstories.utilities.DialogAnim;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.ALARM_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;

/**
 * Created by AMBE on 10/5/2018 at 3:55 PM.
 */
public class TimeBgDialog extends MyAlertDialog {
    public static final String TIME_BG_DIALOG = "TIMEBG";
    @BindView(R.id.number_picker)
    NumberPicker numberPicker;
    @BindView(R.id.lnl_btn_dialog_weight_cancel)
    LinearLayout lnlBtnDialogWeightCancel;
    @BindView(R.id.lnl_btn_dialog_weight_ok)
    LinearLayout lnlBtnDialogWeightOk;
    @BindView(R.id.vgr_dialog_weight)
    LinearLayout vgrDialogWeight;

    private String number;
    private String unit;
    private IOnClickBtnOkDialog i;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Intent intentBroadcastStopService;

    public TimeBgDialog(Context context, IOnClickBtnOkDialog i) {
        super(context);
        this.i = i;
    }

    @Override
    protected int provideLayout() {
        return R.layout.dialog_time_bg;
    }

    @Override
    protected void setupViews() {
        numberPicker.setValue(Integer.parseInt(number));
    }

    public void setNumber(String mess) {
        String str = mess.split(" ")[0];
        String str1 = mess.split(" ")[1];
        number = str;
        unit = str1;

    }

    @Override
    protected void setAnimation() {
        DialogAnim.animateDialog(vgrDialogWeight);

    }

    @OnClick({R.id.lnl_btn_dialog_weight_cancel, R.id.lnl_btn_dialog_weight_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lnl_btn_dialog_weight_cancel:
                dismiss();
                break;
            case R.id.lnl_btn_dialog_weight_ok:
                //cai dat hen gio
                if (ServiceMedia.session.getType().equals("Background")) {
                    MethodStatic.AlarmSoungBackground(getContext(), (numberPicker.getValue() * 60000));
//                    intentBroadcastStopService = new Intent(getContext(), BroadcastStopService.class);
//                    alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
//                    pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intentBroadcastStopService, PendingIntent.FLAG_UPDATE_CURRENT);
//                    Calendar calendar=Calendar.getInstance();
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, (calendar.getTimeInMillis() + MethodStatic.timeBackground(getContext())), pendingIntent);
//                    Log.e("ACDddD",""+ (calendar.getTimeInMillis()+MethodStatic.timeBackground(getContext())));
                }
                MethodStatic.timeBackground(getContext(), numberPicker.getValue()* 60000);
                i.onClickBtnOk(TIME_BG_DIALOG, numberPicker.getValue() + " " + "minutes");
                dismiss();
                break;
        }
    }
}
