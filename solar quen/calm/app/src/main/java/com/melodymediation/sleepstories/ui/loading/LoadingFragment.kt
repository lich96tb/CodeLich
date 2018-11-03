package com.melodymediation.sleepstories.ui.loading

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.melodymediation.sleepstories.databinding.FragmentLoadingBinding
import com.melodymediation.sleepstories.workers.SyncDataWorker
import android.net.NetworkInfo
import android.net.ConnectivityManager
import androidx.navigation.Navigation
import com.melodymediation.sleepstories.R
import com.melodymediation.sleepstories.ui.dialogs.NoInternetDialog
import com.melodymediation.sleepstories.utilities.PrefManager
import android.content.BroadcastReceiver
import android.widget.Toast
import com.melodymediation.sleepstories.service.evenbust.MessageEventDoawloadListImage
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoadingFragment : Fragment() {
    private var dialog: ProgressDialog? = null
    private var binding: FragmentLoadingBinding? = null
    private var networkChangeReceiver: BroadcastReceiver? = null
    private var noInternetDialog: NoInternetDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        noInternetDialog = NoInternetDialog(context)
        Toast.makeText(context, "load", Toast.LENGTH_LONG).show()
        networkChangeReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == "android.net.conn.CONNECTIVITY_CHANGE") {
                    val prefManager = PrefManager(inflater.context)

                    if (prefManager!!.isFirstTimeSyncData()) {
                        if (isNetworkAvailable(inflater.context)) {
                            // Sync data
                            val syncData = OneTimeWorkRequest.Builder(SyncDataWorker::class.java)
                                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                                .addTag("SYNC_DATA_TAG")
                                .build()
                            val workManager = WorkManager.getInstance()
                            workManager.beginWith(syncData).enqueue()
                            prefManager.setFirstTimeSyncData(false)
                            noInternetDialog?.dismiss()
                            dialog = ProgressDialog(context)
                            dialog!!.setMessage("DoadloadImage")
                            dialog!!.setCancelable(false)
                            dialog!!.show()
                            //  Navigation.findNavController(binding!!.root).navigate(R.id.introMainFragment)
                        } else {
                            noInternetDialog?.show()
                        }
                    } else {
                        //     Navigation.findNavController(binding!!.root).navigate(R.id.introMainFragment)
                    }
                }
            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefManager = PrefManager(view.context)
        if (!prefManager!!.isFirstTimeSyncData()) {
            Navigation.findNavController(binding!!.root).navigate(R.id.introMainFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.registerReceiver(networkChangeReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        EventBus.getDefault().register(this);
    }

    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(networkChangeReceiver)
        EventBus.getDefault().unregister(this);
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetwork: NetworkInfo? = connectivityManager!!.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTaskCreateSelectEvent(event: MessageEventDoawloadListImage) {
        dialog!!.dismiss()
        Navigation.findNavController(binding!!.root).navigate(R.id.introMainFragment)
//Toast.makeText(context,"okdvao roi",Toast.LENGTH_LONG).show()
    }


}
