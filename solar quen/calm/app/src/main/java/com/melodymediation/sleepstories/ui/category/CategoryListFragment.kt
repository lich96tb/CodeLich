package com.melodymediation.sleepstories.ui.category

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.melodymediation.sleepstories.data.room.Category
import com.melodymediation.sleepstories.data.room.Session
import com.melodymediation.sleepstories.databinding.FragmentCategoryListBinding
import com.melodymediation.sleepstories.utilities.FragmentUtil
import com.melodymediation.sleepstories.utilities.InjectorUtils

import java.util.UUID

class CategoryListFragment : Fragment() {

    private lateinit var categoryListViewModel: CategoryListViewModel
    private lateinit var categoryId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        categoryId = arguments?.getString("com.bigqsys.music.relax.ui.category.CATEGORY_ID")!!

        val factory = InjectorUtils.provideCategoryListViewModelFactory(context!!)
        categoryListViewModel = ViewModelProviders.of(this, factory).get(CategoryListViewModel::class.java)

        val categoryAdapter = CategoryAdapter(this, categoryListViewModel, fragmentManager, context)
        binding.rvCategoryList.adapter = categoryAdapter

        val category = categoryListViewModel.getCategoryById(categoryId)
        if (category != null) {
            binding.tvCategoryName.text = category.name
        }


        FragmentUtil.printActivityFragmentList(fragmentManager)
//        initViewModel()
        subscribeUi(categoryAdapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            Log.d("AMBE1203", " aaaaaaaaaa")

    }

    private fun subscribeUi(categoryAdapter: CategoryAdapter) {
        categoryListViewModel.getCategoriesByParentId(categoryId).observe(this, Observer { categories ->
            if (categories != null) categoryAdapter.setCategories(categories)
        })
    }

    private fun initViewModel() {
        categoryListViewModel!!.getRemoteCategoriesByParentId(categoryId)
    }

    private fun createCategory() {
        val category = Category(UUID.randomUUID().toString(), "Kids", "", "", "", 1)
        categoryListViewModel!!.addCategory(category)
    }

    private fun createSession() {
        for (i in 1..1) {
            val session = Session(UUID.randomUUID().toString(), "Lesson $i", "", "5bb312efa3fcfa50f8d46469",
                "", "10:22 min", "", true, false, "", i, "", "",
                "", "", "")
            categoryListViewModel!!.createSession(session)
        }
    }


}
