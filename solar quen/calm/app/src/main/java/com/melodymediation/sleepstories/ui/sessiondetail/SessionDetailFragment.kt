package com.melodymediation.sleepstories.ui.sessiondetail

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melodymediation.sleepstories.databinding.FragmentSessionDetailBinding
import com.melodymediation.sleepstories.utilities.InjectorUtils
import android.support.v7.widget.LinearLayoutManager
import androidx.navigation.Navigation
import com.melodymediation.sleepstories.interfeaces.IIntentLesson
import com.melodymediation.sleepstories.ui.MainActivity
import com.melodymediation.sleepstories.ui.MainFragment
import com.melodymediation.sleepstories.ui.MethodStatic
import kotlinx.android.synthetic.main.fragment_session_detail.view.*

class SessionDetailFragment : Fragment() {

    private lateinit var sessionDetailViewModel: SessionDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSessionDetailBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        MethodStatic.tabLayout.visibility = View.GONE

        val factory = InjectorUtils.provideSessionDetailViewModelFactory(context)
        sessionDetailViewModel = ViewModelProviders.of(this, factory).get(SessionDetailViewModel::class.java)

        val sessionDetailAdapter = SessionDetailAdapter(this, fragmentManager)
        binding.rvSessionList.adapter = sessionDetailAdapter
        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        binding.rvSessionList.layoutManager = gridLayoutManager

        val categoryId: String = arguments?.getString("com.bigqsys.music.relax.ui.sessiondetail.CATEGORY_ID")!!

        val category = sessionDetailViewModel.getCategoryById(categoryId)
        if (category != null) {
            val categoryParent = sessionDetailViewModel.getCategoryById(category.parentId)
            binding.tvSessionTitle.text = category.name
            if (categoryParent != null) {
                binding.tvSessionTitle.text = categoryParent.name + " / " + category.name
            }
        }
        subscribeUi(sessionDetailAdapter, categoryId)

        binding.root.iv_back.setOnClickListener {
           fragmentManager!!.popBackStack();
            MethodStatic.tabLayout.visibility = View.VISIBLE
        }
        return binding.root
    }

    private fun subscribeUi(sessionDetailAdapter: SessionDetailAdapter, categoryId: String) {
        sessionDetailViewModel.getLessonsByCategoryId(categoryId).observe(viewLifecycleOwner, Observer { sessions ->
            if (sessions != null) sessionDetailAdapter.setSessions(sessions)
        })
    }

    private fun initViewModel(categoryId: String) {
        sessionDetailViewModel!!.getSessionsByCategoryOption(categoryId)
    }
}
