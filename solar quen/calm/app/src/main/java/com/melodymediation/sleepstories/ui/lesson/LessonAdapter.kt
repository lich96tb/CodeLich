package com.melodymediation.sleepstories.ui.lesson

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.melodymediation.sleepstories.R
import com.melodymediation.sleepstories.data.room.Session
import com.melodymediation.sleepstories.databinding.ItemLessonBinding
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.util.Log
import android.widget.Toast
import com.melodymediation.sleepstories.utilities.TYPE_MEDIA_BREATHE;
import com.melodymediation.sleepstories.interfeaces.IIntentLesson
import com.melodymediation.sleepstories.ui.MethodStatic


class LessonAdapter(val iIntentLesson: IIntentLesson) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var sessions: List<Session>? = null
    override fun getItemCount(): Int {
        return if (sessions == null) 0 else sessions!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val binding = DataBindingUtil.inflate<ItemLessonBinding>(LayoutInflater.from(parent.context), R.layout.item_lesson, parent, false)

        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(lessonViewHolder: LessonViewHolder, position: Int) {
        lessonViewHolder.binData(sessions!![position], position + 1)
    }

    fun setSessions(sessions: List<Session>) {
        this.sessions = sessions
        notifyDataSetChanged()
    }

    inner class LessonViewHolder(private val myBinding: ItemLessonBinding) : RecyclerView.ViewHolder(myBinding.root), View.OnClickListener {

        private var hasLock: Boolean = false

        init {
            itemView.setOnClickListener(this)
        }

        fun binData(session: Session, position: Int) {
            myBinding.session = session
            myBinding.tvLessonOrder.text = position.toString()
            if (!session.isFee && !session.isBuy) {
                myBinding.ivLockLesson.setImageResource(R.drawable.ic_15_white)
                hasLock = true
            }

//            myBinding.ivLockLesson.setOnClickListener {
//                if (!hasLock) {
//                    val bundle = Bundle()
//                    bundle.putString("com.bigqsys.music.relax.ui.media.SESSION_ID", session.sessionId)
//                    Navigation.findNavController(it).navigate(R.id.mediaFragment, bundle)
//                }
//            }
        }

        override fun onClick(view: View) {

            if (!hasLock) {
                val bundle = Bundle()
                bundle.putString(Companion.SESSION_ID, myBinding.session!!.sessionId)
                if (myBinding.session!!.typeMedia != null && myBinding.session!!.typeMedia == TYPE_MEDIA_BREATHE) {
                    //  Navigation.findNavController(view).navigate(R.id.meditationPlayFragment, bundle)
                    if (MethodStatic.bottomSheetBehavior != null) {
                        if (MethodStatic.bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED || MethodStatic.bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                            MethodStatic.bottomSheetBehavior.isHideable = true
                            MethodStatic.bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                        }
                    }
                    Log.e("AAAAAAAAAAA","eeeeeeeeeeee")
                    iIntentLesson.intenMetadition(bundle)
                } else {
                    iIntentLesson.intentLesson(bundle)
                    Log.e("AAAAAAAAAAA","ADDDDDDD")
                }
            }
        }
    }

    companion object {
        const val SESSION_ID = "com.bigqsys.music.relax.ui.media.SESSION_ID"
    }
}
