package com.melodymediation.sleepstories.ui
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melodymediation.sleepstories.databinding.FragmentAppBinding

import com.melodymediation.sleepstories.utilities.InjectorUtils
import com.melodymediation.sleepstories.ui.category.CategoryListViewModel

class AppFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAppBinding.inflate(inflater, container, false)
        binding.recyclerCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerCategory.itemAnimator = DefaultItemAnimator()
        val factory = InjectorUtils.provideCategoryListViewModelFactory(requireContext())
        val viewModel = ViewModelProviders.of(this, factory)
            .get(CategoryListViewModel::class.java)
        binding.buttonTest.setOnClickListener {
            viewModel.getCategories()
        }

        return binding.root
    }

}
