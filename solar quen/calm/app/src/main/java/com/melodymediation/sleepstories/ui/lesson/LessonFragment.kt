package com.melodymediation.sleepstories.ui.lesson

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.melodymediation.sleepstories.databinding.FragmentLessonBinding
import com.melodymediation.sleepstories.utilities.InjectorUtils
import com.bumptech.glide.Glide
import android.os.Build
import android.graphics.drawable.Drawable
import android.util.Log
import com.melodymediation.sleepstories.interfeaces.IIntentLesson
import com.melodymediation.sleepstories.ui.media.MediaFragment
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.melodymediation.sleepstories.R
import com.melodymediation.sleepstories.ui.MethodStatic
import com.melodymediation.sleepstories.service.evenbust.MessageEvent
import com.melodymediation.sleepstories.ui.meditation.MeditationPlayFragment
import kotlinx.android.synthetic.main.fragment_lesson.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LessonFragment : Fragment(), IIntentLesson {
    override fun intenMetadition(bundle: Bundle?) {
        val meta = MeditationPlayFragment.newInstance()
        meta.arguments = bundle
        fragmentManager!!.beginTransaction().add(R.id.fragmentHome, meta).commit()
    }

    override fun intentLesson(bundle: Bundle?) {
        val mediaFragment = MediaFragment()
        mediaFragment.arguments = bundle
        Log.e("idsssss",""+bundle!!.get("com.bigqsys.music.relax.ui.media.SESSION_ID"))
        fragmentManager!!.beginTransaction().replace(R.id.player_wrap, mediaFragment).commit()
    }

    companion object {
        var nameCategory: String? = null
    }

    private lateinit var lessonViewModel: LessonViewModel
    var name: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLessonBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

//        if (tabLayout.visibility == View.VISIBLE) {
//            tabLayout.visibility = View.GONE // Hide tab layout
//        }

        val factory = InjectorUtils.provideLessonViewModelFactory(context)
        lessonViewModel = ViewModelProviders.of(this, factory).get(LessonViewModel::class.java)

        val lessonAdapter = LessonAdapter(this)
        binding.rvLessonList.adapter = lessonAdapter

        val parentId: String = arguments?.getString("com.bigqsys.music.relax.ui.lesson.SESSION_ID")!!
        val type: String = arguments?.getString("TYPE_FR")!!
        Log.e("ACD", "" + parentId)
        val session = lessonViewModel.getLessonById(parentId)
        binding.tvLessonTitle.text = session.name
        nameCategory = session.name
        // Toast.makeText(context,session.name,Toast.LENGTH_SHORT).show()

        // Set background fragment
        Glide.with(this).load(session.urlBackground).into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    binding.llFragmentLesson.background = resource

                }
            }
        })

//        initViewModel(parentId)
        subscribeUi(lessonAdapter, parentId)

        binding.root.iv_back.setOnClickListener {
            //            fragmentManager?.popBackStack()
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.commit()
//            FragmentUtil.printActivityFragmentList(fragmentManager)

//            val bundle = Bundle()
//            val x = lessonViewModel.getCaategoryById(session.categoryId)
//            bundle.putString("com.bigqsys.music.relax.ui.category.CATEGORY_ID", x.parentId)
//            Navigation.findNavController(it).navigate(R.id.categoryListFragment, bundle)
            fragmentManager!!.popBackStack();
            // Navigation.findNavController(it).navigateUp()

            //          (activity as MainActivity).onBackPressed()


        }
        return binding.root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this);

    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this);

    }

    private fun subscribeUi(lessonAdapter: LessonAdapter, parentId: String) {
        lessonViewModel.getLessonsByParentId(parentId).observe(viewLifecycleOwner, Observer { sessions ->
            if (sessions != null) lessonAdapter.setSessions(sessions)
        })
    }

    private fun initViewModel(parentId: String) {
        lessonViewModel!!.getLessonsByParentIdOption(parentId)

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent) {
        if (event.url.equals("COLLAPSED") || event.url.equals("EXPANDED")) {
            MethodStatic.tabLayout.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        MethodStatic.tabLayout.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        MethodStatic.tabLayout.visibility = View.VISIBLE
    }
}
