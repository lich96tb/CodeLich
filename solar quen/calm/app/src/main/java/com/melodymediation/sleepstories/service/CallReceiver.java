package com.melodymediation.sleepstories.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by AMBE on 10/18/2018 at 5:21 PM.
 */
public class CallReceiver extends BroadcastReceiver {
    TelephonyManager telManager;
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {


        this.context = context;

        telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

    }

    private final PhoneStateListener phoneListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            try {
                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING: {
                        //PAUSE
                        Intent background = new Intent(context, ServiceMedia.class);
                        context.stopService(background);
                        break;
                    }
                    case TelephonyManager.CALL_STATE_OFFHOOK: {

                        break;
                    }
                    case TelephonyManager.CALL_STATE_IDLE: {
                        //PLAY
                        Intent background = new Intent(context, ServiceMedia.class);
                        context.startService(background);
                        break;
                    }
                    default: {
                    }
                }
            } catch (Exception ex) {

            }
        }
    };
}
