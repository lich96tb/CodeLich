package com.melodymediation.sleepstories.ui.setting


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.melodymediation.sleepstories.R
import com.melodymediation.sleepstories.ui.dialogs.TimeBgDialog
import com.melodymediation.sleepstories.ui.dialogs.TimeReminderDialog
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import com.melodymediation.sleepstories.databinding.FragmentSettingBinding
import com.melodymediation.sleepstories.interfeaces.IOnClickBtnOkDialog
import kotlinx.android.synthetic.main.fragment_setting.*
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.util.Log
import com.melodymediation.sleepstories.service.AlertDialogReceiver
import com.melodymediation.sleepstories.ui.MethodStatic
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SettingFragment : Fragment(), IOnClickBtnOkDialog {


    override fun onClickBtnOk(idDialog: String?, mess: String?) {
        if (idDialog.equals(TimeBgDialog.TIME_BG_DIALOG)) {
            txt_time_bg.text = mess
            timeBg = txt_time_bg.text.toString()
            savingPreferences()
        } else if (idDialog.equals(TimeReminderDialog.TIME_REMINDER)) {
            txt_time_reminder.text = mess
            timeReminder = txt_time_reminder.text.toString()
            savingPreferences()
            var txt: String = txt_time_reminder.text.toString()
            val suffix = txt.substring(txt.length - 2)
            txt = txt.replace(suffix, "")
            val hms = txt.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (suffix == "PM" || suffix == "pm") {
                hms[0] = (Integer.parseInt(hms[0]) + 12).toString()
                hms[0] = if (hms[0] == "24") "12" else hms[0]
            } else if (suffix == "AM" || suffix == "am") {
                hms[0] = if (hms[0] == "12") "00" else hms[0]
            }

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hms[0].trim()))
            calendar.set(Calendar.MINUTE, Integer.parseInt(hms[1].trim()))
            calendar.set(Calendar.MILLISECOND, 0)
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }
            val intent = Intent(context, AlertDialogReceiver::class.java)
            intent.putExtra("STOP", "Test")
            val mAlarmSender = PendingIntent.getBroadcast(context,
                167, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val amgr = context!!.getSystemService(ALARM_SERVICE) as AlarmManager

            amgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, mAlarmSender)


        }
    }

    companion object {
        val TIME_REMINDER = "1203"
        val TIME_BG = "0204"
        val PRE_NAME = "SETTINGS"
    }

    var timeReminder: String = ""
    var timeBg: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSettingBinding>(inflater, R.layout.fragment_setting, container, false)

        binding.imgBackSettings.setOnClickListener {
           // Navigation.findNavController(view).navigateUp()
            fragmentManager!!.popBackStack();

            //      savingPreferences()

        }

        binding.lnlBtnlTimeBg.setOnClickListener {
            showDialogTimeBg()
        }

        binding.lnlBtnlTimeReminder.setOnClickListener {
            showDialogTimeReminder()
        }

        val pre = context!!.getSharedPreferences(PRE_NAME, MODE_PRIVATE)

        val timeReminder = pre.getString(TIME_REMINDER, "")
        val timeBg = pre.getString(TIME_BG, "")
        if (timeReminder != null && !timeReminder.equals("")) {
            binding.txtTimeReminder.text = timeReminder
        }
            binding.txtTimeBg.text = ""+(MethodStatic.timeBackground(context)/60000)+" minutes"
        return binding.root
    }


    fun savingPreferences() {
        val pre = context!!.getSharedPreferences(PRE_NAME, MODE_PRIVATE)
        val editor = pre.edit()
        timeReminder = txt_time_reminder.text.toString()
        timeBg = txt_time_bg.text.toString()
        editor.putString(TIME_REMINDER, timeReminder)
        editor.putString(TIME_BG, timeBg)
        editor.commit()
    }


    private fun showDialogTimeReminder() {
        val timeReminderDialog = TimeReminderDialog(context, this)
        timeReminderDialog.setTimeReminder(txt_time_reminder.text as String?)
        timeReminderDialog.show()
    }

    private fun showDialogTimeBg() {

        val timeBgDialog = TimeBgDialog(context, this)
        timeBgDialog.setNumber(txt_time_bg.text as String?)
        timeBgDialog.show()

    }

    override fun onDestroy() {
        super.onDestroy()
    MethodStatic.tabLayout.visibility=View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        MethodStatic.tabLayout.visibility=View.GONE
    }
}
