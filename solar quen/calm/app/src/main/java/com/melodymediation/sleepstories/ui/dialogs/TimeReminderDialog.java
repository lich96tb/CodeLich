package com.melodymediation.sleepstories.ui.dialogs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.interfeaces.IOnClickBtnOkDialog;
import com.melodymediation.sleepstories.ui.view.MyAlertDialog;
import com.melodymediation.sleepstories.utilities.DialogAnim;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by AMBE on 10/5/2018 at 4:32 PM.
 */
public class TimeReminderDialog extends MyAlertDialog implements TimePicker.OnTimeChangedListener {
    private String time;
    public static final String TIME_REMINDER = "TIME_REMINDER";
    @BindView(R.id.time_picker_reminder)
    TimePicker timePickerReminder;
    @BindView(R.id.lnl_btn_dialog_time_reminder_cancel)
    LinearLayout lnlBtnDialogTimeReminderCancel;
    @BindView(R.id.lnl_btn_dialog_time_reminder_ok)
    LinearLayout lnlBtnDialogTimeReminderOk;
    @BindView(R.id.vgr_dialog_reminder)
    LinearLayout vgrDialogReminder;
    private IOnClickBtnOkDialog i;
    private String timeReminder;


    public void setTimeReminder(String timeStart) {
        this.timeReminder = timeStart;
    }

    public TimeReminderDialog(Context context, IOnClickBtnOkDialog i) {
        super(context);
        this.i = i;
    }

    @Override
    protected int provideLayout() {
        return R.layout.dialog_reminder;
    }

    @Override
    protected void setupViews() {

        String hour = timeReminder.substring(0, 2);
        String minute = timeReminder.substring(3, 5);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePickerReminder.setHour(Integer.parseInt(hour));
            timePickerReminder.setMinute(Integer.parseInt(minute));
        }
        timePickerReminder.setOnTimeChangedListener(this);
        applyStyLing(timePickerReminder);


    }

    @Override
    protected void setAnimation() {

        DialogAnim.animateDialog(vgrDialogReminder);

    }

    @OnClick({R.id.lnl_btn_dialog_time_reminder_cancel, R.id.lnl_btn_dialog_time_reminder_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lnl_btn_dialog_time_reminder_cancel:
                dismiss();
                break;
            case R.id.lnl_btn_dialog_time_reminder_ok:
                if ( time == null || time.length() == 0) {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat s = new SimpleDateFormat("hh:mm a");
                    time = s.format(c.getTime());
                }
                i.onClickBtnOk(TIME_REMINDER, time);
                dismiss();
                break;
        }
    }

    private void applyStyLing(TimePicker timePickerDialog) {
        Resources system = Resources.getSystem();
        int hourNumberPickerId = system.getIdentifier("hour", "id", "android");
        int minuteNumberPickerId = system.getIdentifier("minute", "id", "android");
        int ampmNumberPickerId = system.getIdentifier("amPm", "id", "android");

        NumberPicker hourNumberPicker = timePickerDialog.findViewById(hourNumberPickerId);
        NumberPicker minuteNumberPicker = timePickerDialog.findViewById(minuteNumberPickerId);
        NumberPicker ampmNumberPicker = timePickerDialog.findViewById(ampmNumberPickerId);

        setNumberPickerDividerColour(hourNumberPicker);
        setNumberPickerDividerColour(minuteNumberPicker);
        setNumberPickerDividerColour(ampmNumberPicker);
        setNumberpickerTextColour(hourNumberPicker);
        setNumberpickerTextColour(minuteNumberPicker);
        setNumberpickerTextColour(ampmNumberPicker);

    }

    private void setNumberpickerTextColour(NumberPicker number_picker) {
        final int count = number_picker.getChildCount();
        final int color = getContext().getResources().getColor(R.color.colorBlack);

        for (int i = 0; i < count; i++) {
            View child = number_picker.getChildAt(i);

            try {
                Field wheelpaint_field = number_picker.getClass().getDeclaredField("mSelectorWheelPaint");
                wheelpaint_field.setAccessible(true);

                ((Paint) wheelpaint_field.get(number_picker)).setColor(color);
                ((EditText) child).setTextColor(color);
                number_picker.invalidate();
            } catch (NoSuchFieldException e) {
                Log.w("AMBE1203", e);
            } catch (IllegalAccessException e) {
                Log.w("AMBE1203", e);
            } catch (IllegalArgumentException e) {
                Log.w("AMBE1203", e);
            }
        }
    }

    private void setNumberPickerDividerColour(NumberPicker number_picker) {
        final int count = number_picker.getChildCount();

        for (int i = 0; i < count; i++) {

            try {
                Field dividerField = number_picker.getClass().getDeclaredField("mSelectionDivider");
                dividerField.setAccessible(true);
                ColorDrawable colorDrawable = new ColorDrawable(getContext().getResources().getColor(R.color
                    .colorDialog));
                dividerField.set(number_picker, colorDrawable);

                number_picker.invalidate();
            } catch (NoSuchFieldException e) {
                Log.w("setNumberPickerTxtClr", e);
            } catch (IllegalAccessException e) {
                Log.w("setNumberPickerTxtClr", e);
            } catch (IllegalArgumentException e) {
                Log.w("setNumberPickerTxtClr", e);
            }
        }
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        int hourTam = hourOfDay;
        String hour;
        String minutes;
        if (hourTam > 12) {
            hourTam = hourTam - 12;
        }
        if (hourTam < 10) {
            hour = "0" + hourTam;
        } else {
            hour = "" + hourTam;
        }
        if (minute < 10) {
            minutes = "0" + minute;
        } else {
            minutes = "" + minute;
        }
        time = (hour + ":" + minutes + (hourOfDay > 12 ? " pm" : " am"));
    }
}
