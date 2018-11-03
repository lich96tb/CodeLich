package com.melodymediation.sleepstories.ui.media


import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Toast
import com.melodymediation.sleepstories.data.repository.SessionRepository
import com.melodymediation.sleepstories.data.room.Session
import com.melodymediation.sleepstories.service.downloadimage.ISenUrlImage
import com.melodymediation.sleepstories.service.ServiceMedia
import com.melodymediation.sleepstories.service.downloadimage.DownloadImage
import com.melodymediation.sleepstories.ui.lesson.LessonFragment
import com.melodymediation.sleepstories.ui.lesson.LessonViewModel
import com.melodymediation.sleepstories.service.doawloadmp3.DownloadMusicFile
import com.melodymediation.sleepstories.service.doawloadmp3.IDoawnload
import kotlinx.android.synthetic.main.dialog_time_bg.*
import kotlinx.android.synthetic.main.fragment_media.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.io.File
import org.greenrobot.eventbus.ThreadMode
import com.melodymediation.sleepstories.service.evenbust.MessageEvent
import com.melodymediation.sleepstories.service.evenbust.MessegaeEventService
import com.melodymediation.sleepstories.ui.sessiondetail.SessionDetailViewModel
import com.melodymediation.sleepstories.utilities.InjectorUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.melodymediation.sleepstories.R
import com.melodymediation.sleepstories.service.BroadcastStopService
import com.melodymediation.sleepstories.service.BroadcastStopServiceSongBackground
import com.melodymediation.sleepstories.ui.MethodStatic
import com.melodymediation.sleepstories.ui.MethodStatic.bottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_media.view.*
import java.util.*


class MediaFragment : Fragment(), IDoawnload, ISenUrlImage {
    override fun urlSenImage(urlImage: String?) {
        if (urlImage != null) {
            sessionDetailViewModel.updateSessionUrlBackground(sessionId!!, urlImage)
            session = sessionDetailViewModel.getSessionById(sessionId!!)
            //   CheckDownloadMP3(view)
        }
    }

    override fun Idoawload(urlDist: String?) {
        if (urlDist != null) {
            dialog!!.dismiss()
            sessionDetailViewModel.updateSessionUrlDist(sessionId!!, urlDist)
            session = sessionDetailViewModel.getSessionById(sessionId!!)
            CheckDownloadMP3(view)
            var bitmap1 = BitmapFactory.decodeFile(session!!.urlBackgroundDist);
            view!!.imgAvataMediaplay.setImageBitmap(bitmap1)
        }
    }

    private var requestOptions: RequestOptions? = null
    private val sessionRepository: SessionRepository? = null
    var audio: AudioManager? = null
    var mediaPlayer: MediaPlayer? = null
    var duration = 0
    var ss = ""
    var mm = ""
    var position = 0
    var timeStop = -1
    var listSession: List<Session>? = null
    var session: Session? = null
    var file: File? = null
    var checkPlayMedia: Boolean? = false
    private lateinit var sessionDetailViewModel: SessionDetailViewModel
    var sessionId: String? = null
    var dialog: ProgressDialog? = null
    var check = false
    var intent: Intent? = null
    var timecPlay = 0;
    private lateinit var lessonViewModel: LessonViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_media, container, false)
        requestOptions = RequestOptions()
        requestOptions = requestOptions!!.transforms(CenterCrop(), RoundedCorners(30))
        intent = Intent(context, ServiceMedia::class.java)
        view.txtTitlePL.setText(LessonFragment.nameCategory)
        view.txtNameCategory.text = LessonFragment.nameCategory
        sessionId = arguments?.getString("com.bigqsys.music.relax.ui.media.SESSION_ID")!!
        //khoi tao viewmodel
        val factory = InjectorUtils.provideSessionDetailViewModelFactory(context!!)
        sessionDetailViewModel = ViewModelProviders.of(this, factory).get(SessionDetailViewModel::class.java)
        session = sessionDetailViewModel.getSessionById(sessionId!!)

//        if(txtTitlePL.getText().equals("")){
//           // txtTitlePL.visibility=View.GONE
//        }

        if (!session!!.urlDist.equals("")) {
            var mp = MediaPlayer.create(context, Uri.parse(session!!.urlDist));
            var duration = mp.getDuration();
            view.seekBarPL.max = duration
            view.seekPlayMedia.max = duration
            fomatTime(duration)
            view!!.txtTimePL.setText(mm + ":" + ss)

//        if(txtTitlePL.getText().equals("")){
//           // txtTitlePL.visibility=View.GONE
//        }
        }
        if (!session!!.urlBackgroundDist.equals("")) {
            Glide.with(getContext())
                .load(session!!.urlBackgroundDist)
                .apply(this!!.requestOptions!!)
                .into(view!!.imgBackgroundPlay);
            var bitmap1 = BitmapFactory.decodeFile(session!!.urlBackgroundDist);
            view.imgAvataMediaplay.setImageBitmap(bitmap1)
        }


        check = MethodStatic.isMyServiceRunning(ServiceMedia::class.java, context)
        if (check) {
            if (!sessionId.equals(ServiceMedia.id)) {
                activity!!.stopService(intent)
                CheckDownloadMP3(view)
            }
        } else {
            CheckDownloadMP3(view)
        }

        sessionDetailViewModel.getLessonsByParentId(session!!.parentId).observe(viewLifecycleOwner, Observer { sessions ->
            if (sessions != null) {
                listSession = sessions


            }
        })
        return view
    }


    private fun CheckDownloadMP3(view: View?) {

        if (session!!.urlDist.equals("")) {
            dialog = ProgressDialog(context)
            var bundle = Bundle()
            bundle!!.putString("name", session!!.name)
            bundle!!.putString("url", session!!.url)
            bundle!!.putString("urlimage", session!!.urlBackground)
            var downloadImage = DownloadImage(this)
            downloadImage.execute(bundle)
            var downloadMusicFile = DownloadMusicFile(this)
            downloadMusicFile.execute(bundle)
            dialog!!.setCancelable(false)
            dialog!!.setMessage(context!!.resources.getString(R.string.download_song))
            dialog!!.show()
        } else {
            if (session!!.urlBackgroundDist != null) {
                Glide.with(getContext())
                    .load(session!!.urlBackgroundDist)
                    .apply(this!!.requestOptions!!)
                    .into(view!!.imgBackgroundPlay);
            }

            var mp = MediaPlayer.create(context, Uri.parse(session!!.urlDist));
            var duration = mp.getDuration();
            view!!.seekBarPL.max = duration
            view!!.seekPlayMedia.max = duration
            view!!.seekPlayMedia.max = duration

            fomatTime(duration)
            view!!.txtTimePL.setText(mm + ":" + ss)
            view!!.txtTimePlayHome.text = mm + ":" + ss
            intent!!.putExtra("session", session)
            activity!!.startService(intent)


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MethodStatic.tabLayout.visibility = View.GONE


        CheckSound();
        onClick()
        audio = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        val max = audio!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        view.seekBarPL.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    var intent = Intent()
                    intent.setAction("My Broadcast");
                    intent.putExtra("progress", progress);
                    context?.let { LocalBroadcastManager.getInstance(it).sendBroadcast(intent) };
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        val volumeLevel = audio!!.getStreamVolume(AudioManager.STREAM_MUSIC)

        var maxValue = max * 10
        seekbarVolumeMP.setMax(maxValue)
        seekbarVolumeMP.setProgress(volumeLevel * 10)
        seekbarVolumeMP.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (progress == 0) {
                        imglock.setImageResource(R.drawable.ic_volume_mute)
                    } else {
                        imglock.setImageResource(R.drawable.ic_sound)
                    }
                    audio!!.setStreamVolume(AudioManager.STREAM_MUSIC, progress / 10, 0)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        btnOkTimeStop.setOnClickListener {
            //fdgf
            // stop service
            stopMyService(timeStop)
            layoutTimer.visibility = View.GONE

        }

        val width = activity!!.windowManager.getDefaultDisplay().getWidth();
        MethodStatic.bottomSheetBehavior = BottomSheetBehavior.from(view!!.fragmentMedia)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.peekHeight = width / 2

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, i: Int) {
                if (i == BottomSheetBehavior.STATE_EXPANDED) {
                    //  animateChange(1.0f)
                    MethodStatic.tabLayout.visibility = View.GONE
                    //    EventBus.getDefault().postSticky(MessageEvent("EXPANDED"))
                } else if (i == BottomSheetBehavior.STATE_COLLAPSED) {
                    //   animateChange(0.0f)
                    //    EventBus.getDefault().postSticky(MessageEvent("COLLAPSED"))
                    MethodStatic.tabLayout.visibility = View.VISIBLE

                }
            }

            override fun onSlide(view: View, v: Float) {
                animateChange(v)
            }
        })



        layoutPlayListSmall.alpha = 0f
        imgMiniature.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            EventBus.getDefault().postSticky(MessageEvent("COLLAPSED"))

        }

        layoutPlayListSmall.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            EventBus.getDefault().postSticky(MessageEvent("EXPANDED"))

        }

        val filter = IntentFilter()
        filter.addAction("broadcastSen")

        imgPlayMedia.setOnClickListener {
            EventBus.getDefault().postSticky(MessegaeEventService(true, 0));
        }
    }

    private fun animateChange(slideOffset: Float) {
        val mini = 1.0f - Math.min(1.0f, slideOffset * 2.0f)
        val maxi = Math.min(1.0f, slideOffset * 2.0f - 1.0f)
        layoutPlayListSmall.alpha = mini
        frg_container_media.alpha = maxi
    }


    private fun stopMyService(time: Int) {
        val inten = Intent(context, BroadcastStopService::class.java);
        var alarmManager: AlarmManager
        var pendingIntent: PendingIntent
        alarmManager = context!!.getSystemService(ALARM_SERVICE) as AlarmManager
        pendingIntent = PendingIntent.getBroadcast(context, 0, inten, PendingIntent.FLAG_UPDATE_CURRENT);
        if (time != -1) {
            var calendar: Calendar
            calendar = Calendar.getInstance();
            ServiceMedia.timeAlar = calendar.timeInMillis + time
            ServiceMedia.minutes = time / 60000;
            alarmManager.set(AlarmManager.RTC_WAKEUP, ServiceMedia.timeAlar, pendingIntent)
            val tabIconColor = ContextCompat.getColor(context!!, R.color.colorTabSelect)
            imgAlarm!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
        } else {
            ServiceMedia.timeAlar = -1
            ServiceMedia.minutes = -1
            alarmManager.cancel(pendingIntent)
            val tabIconColor = ContextCompat.getColor(context!!, R.color.colorWhite)
            imgAlarm!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
        }
    }

    private fun onClick() {
        layoutPlayPL.setOnClickListener {
                EventBus.getDefault().postSticky(MessegaeEventService(true, 0))
        }

        switchStop.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // switchStop.isChecked = false
                Log.e("ADDD", "" + isChecked)
            } else {
                rdb15p.isChecked = false
                rdb30p.isChecked = false
                rdb45p.isChecked = false
                rdb60p.isChecked = false
                edtInputTime.setText("Input minutes")
                timeStop = -1;
            }
        }




        layoutNext.setOnClickListener {
            var intent = Intent()
            intent.setAction("My Broadcast");
            intent.putExtra("progress", timecPlay + 15000);
            context?.let { LocalBroadcastManager.getInstance(it).sendBroadcast(intent) };
        }
        layoutPreview.setOnClickListener {
            var intent = Intent()
            intent.setAction("My Broadcast");
            intent.putExtra("progress", timecPlay - 15000);
            context?.let { LocalBroadcastManager.getInstance(it).sendBroadcast(intent) };
        }
        layoutManager.setOnClickListener {
            layoutTimer.visibility = View.VISIBLE
            showSetingAlarmPlay();
        }
        canDialog.setOnClickListener { layoutTimer.visibility = View.GONE }
        rdb15p.setOnClickListener {
            switchStop.isChecked = true
            rdb15p.isChecked = true
            rdb30p.isChecked = false
            rdb45p.isChecked = false
            rdb60p.isChecked = false
            timeStop = 60000 * 15
        }
        rdb30p.setOnClickListener {
            switchStop.isChecked = true
            rdb15p.isChecked = false
            rdb30p.isChecked = true
            rdb45p.isChecked = false
            rdb60p.isChecked = false
            timeStop = 60000 * 30

        }
        rdb45p.setOnClickListener {
            switchStop.isChecked = true
            rdb15p.isChecked = false
            rdb30p.isChecked = false
            rdb45p.isChecked = true
            rdb60p.isChecked = false
            timeStop = 60000 * 45
        }
        rdb60p.setOnClickListener {
            switchStop.isChecked = true
            rdb15p.isChecked = false
            rdb30p.isChecked = false
            rdb45p.isChecked = false
            rdb60p.isChecked = true
            timeStop = 60000 * 60

        }
        edtInputTime.setOnClickListener {
            val dialog = Dialog(context, R.style.CustomDialog)
            dialog.setContentView(R.layout.dialog_time_bg)
            dialog.findViewById<View>(R.id.lnl_btn_dialog_weight_ok).setOnClickListener {
                switchStop.isChecked = true
                timeStop = dialog.number_picker.value * 60000
                edtInputTime.setText("" + timeStop / 60000 + " minutes")
                rdb15p.isChecked = false
                rdb30p.isChecked = false
                rdb45p.isChecked = false
                rdb60p.isChecked = false
                dialog.dismiss()
            }
            dialog.findViewById<View>(R.id.lnl_btn_dialog_weight_cancel).setOnClickListener { dialog.dismiss() }
            dialog.show()


        }

        layoutSoundPL.setOnClickListener {
            if (layoutSound.getVisibility() == View.VISIBLE) {
                layoutSound.visibility = View.GONE
            } else {
                layoutSound.visibility = View.VISIBLE
            }
            CheckSound();
        }
        imgMiniature.setOnClickListener {
            fragmentManager!!.popBackStack();
        }
    }

    private fun CheckSound() {
        if (layoutSound.getVisibility() == View.VISIBLE) {
            val tabIconColor = ContextCompat.getColor(context!!, R.color.colorTabSelect)
            imglock!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
        } else {
            val tabIconColor = ContextCompat.getColor(context!!, R.color.colorText)
            imglock!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
        }
    }

    private fun fomatTime(time: Int) {
        if (time / 1000 > 9) {
            val k = time / 1000 / 60
            if (time / 1000 - k * 60 > 9)
                ss = (time / 1000 - k * 60).toString()
            else {
                ss = "0" + (time / 1000 - k * 60)
            }
        } else {
            ss = "0" + time / 1000
        }
        if (time / 1000 / 60 > 9) {
            mm = (time / 1000 / 60).toString() + ""
        } else {
            mm = "0" + time / 1000 / 60 + ""
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent) {
        fomatTime(event.getTimeCurrent())
        txtRealTimePL.setText(mm + ":" + ss)
        txtNameSongMP.setText(event.getNameSong())
        txtNameSongMedia.text = event.nameSong
        timecPlay = event.getTimeCurrent()
        seekBarPL.progress = event.getTimeCurrent()
        seekPlayMedia.progress = event.getTimeCurrent()

        checkPlayMedia = event.checkPlay
        if (event.checkPlay) {
            imgplayPL.setImageResource(R.drawable.ic_pause)
            imgPlayMedia.setImageResource(R.drawable.ic_pause)

        } else {
            imgplayPL.setImageResource(R.drawable.ic_play)
            imgPlayMedia.setImageResource(R.drawable.ic_play)
        }
        if (event.url.equals("EXPANDED_BACK")) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }


    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this);
        showSetingAlarmPlay();
    }

    private fun showSetingAlarmPlay() {
        if (ServiceMedia.timeAlar > -1) {
            val tabIconColor = ContextCompat.getColor(context!!, R.color.colorTabSelect)
            imgAlarm!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            switchStop.isChecked = true
            Log.e("ACddDD", "" + ServiceMedia.minutes)
            when (ServiceMedia.minutes) {
                15 -> rdb15p.isChecked = true
                30 -> rdb30p.isChecked = true
                45 -> rdb45p.isChecked = true
                60 -> rdb60p.isChecked = true
                else -> edtInputTime.setText("" + ServiceMedia.minutes + " minutes")
            }
            var k = ServiceMedia.timeAlar - Calendar.getInstance().timeInMillis
            txtTimeremaining.text = "The app will shut down after " + (k / 60000) + " minutes!"
        } else {
            txtTimeremaining.text = "You should chose 30 minutes"
        }
    }


}
