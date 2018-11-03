package com.melodymediation.sleepstories.ui.sessiondetail

import android.content.Context
import android.databinding.DataBindingUtil
import android.media.MediaPlayer
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.melodymediation.sleepstories.R
import com.melodymediation.sleepstories.data.room.Session
import com.melodymediation.sleepstories.databinding.ItemSessionBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.melodymediation.sleepstories.interfeaces.IIntentLesson
import com.melodymediation.sleepstories.ui.MethodStatic
import com.melodymediation.sleepstories.ui.lesson.LessonAdapter
import com.melodymediation.sleepstories.ui.lesson.LessonFragment
import com.melodymediation.sleepstories.ui.media.MediaFragment
import com.melodymediation.sleepstories.ui.meditation.MeditationPlayFragment
import com.melodymediation.sleepstories.utilities.TYPE_MEDIA_BREATHE

class SessionDetailAdapter(internal var fragment: Fragment, private val fragmentManager: FragmentManager?) : RecyclerView.Adapter<SessionDetailAdapter.SessionViewHolder>() {

    private var sessions: List<Session>? = null

    override fun getItemCount(): Int {
        return if (sessions == null) 0 else sessions!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val binding = DataBindingUtil.inflate<ItemSessionBinding>(LayoutInflater.from(parent.context), R.layout.item_session, parent, false)

        return SessionViewHolder(binding, fragmentManager, parent.context)
    }

    override fun onBindViewHolder(lessonViewHolder: SessionViewHolder, position: Int) {
        lessonViewHolder.binData(sessions!![position])
    }

    fun setSessions(sessions: List<Session>) {
        this.sessions = sessions
        notifyDataSetChanged()
    }

    inner class SessionViewHolder(private val myBinding: ItemSessionBinding, private val fragmentManager: FragmentManager?, private val context: Context) : RecyclerView.ViewHolder(myBinding.root), View.OnClickListener {

        private var hasLock: Boolean = false

        init {
            itemView.setOnClickListener(this)
        }

        fun binData(session: Session) {
            myBinding.session = session
            myBinding.tvSessionName.text = session.name
            myBinding.tvSessionTime.text = session.duration
            myBinding.rlLockSession.visibility = View.INVISIBLE
            if (!session.isFee && !session.isBuy) {
                myBinding.rlLockSession.visibility = View.VISIBLE
                hasLock = true
            }

            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(25))
            Glide.with(context)
                .load(session.urlBackground)
                .apply(requestOptions)
                .into(myBinding.ivThumbSession)
        }

        override fun onClick(view: View) {
            if (!hasLock) {
                Log.e("Typer", myBinding.session!!.type)
                val bundle = Bundle()
                bundle.putString(LessonAdapter.SESSION_ID, myBinding.session!!.sessionId)
                LessonFragment.nameCategory=myBinding.session!!.type
                if (myBinding.session!!.type.equals("Lesson")) {
                    val bundle = Bundle()
                    bundle.putString("com.bigqsys.music.relax.ui.lesson.SESSION_ID", myBinding.session!!.sessionId)
                    bundle.putString("TYPE_FR", "CATE")
                    var fragment= LessonFragment()
                    fragment.arguments=bundle
                    fragmentManager!!.beginTransaction().replace(R.id.layoutSessionFragment,fragment).commit()


                } else if (myBinding.session!!.type.equals("Music")) {
                    val bundle = Bundle()
                    bundle.putString("com.bigqsys.music.relax.ui.media.SESSION_ID", myBinding.session!!.sessionId)
                    var fragment= MediaFragment()
                    fragment.arguments=bundle
                    fragmentManager!!.beginTransaction().replace(R.id.player_wrap,fragment).commit()

                } else if (myBinding.session!!.type.equals(TYPE_MEDIA_BREATHE)) {

                    val bundle = Bundle()
                    bundle.putString("com.bigqsys.music.relax.ui.media.SESSION_ID", myBinding.session!!.sessionId)
                    var fragment=MeditationPlayFragment()
                    fragment.arguments=bundle
                    fragmentManager!!.beginTransaction().replace(R.id.layoutSessionFragment,fragment).commit()

//                    if (MethodStatic.bottomSheetBehavior != null) {
//                        if (MethodStatic.bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED || MethodStatic.bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
//                            MethodStatic.bottomSheetBehavior.isHideable = true
//                            MethodStatic.bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
//                        }
//                    }

                }
                //               val lessonFragment = LessonFragment()
//                val bundle = Bundle()
//                bundle.putString("com.bigqsys.music.relax.ui.lesson.SESSION_ID", myBinding.session!!.sessionId)
//                bundle.putString("TYPE_FR", "DETAIL")
//                var detailFragment=SessionDetailFragment()
//                detailFragment.arguments=bundle
//
//          //      Navigation.findNavController(view).navigate(R.id.lessonFragment, bundle)
//                fragmentManager!!.beginTransaction().replace(R.id.layoutSessionFragment,detailFragment).addToBackStack(null).commit()
//                lessonFragment.arguments = bundle
//                val transaction = fragmentManager?.beginTransaction()
//                transaction?.replace(R.id.fragmentHome, lessonFragment, "LessonFragment")
//                transaction?.addToBackStack(null)
//                transaction?.commit()
//                FragmentUtil.printActivityFragmentList(fragmentManager)
            }
        }
    }
}
