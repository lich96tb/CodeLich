package com.melodymediation.sleepstories.ui.category

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation

import com.melodymediation.sleepstories.R
import com.melodymediation.sleepstories.data.room.Category

import com.melodymediation.sleepstories.data.room.Session
import com.melodymediation.sleepstories.databinding.FooterViewBinding
import com.melodymediation.sleepstories.databinding.ListItemCategoryBinding
import com.melodymediation.sleepstories.databinding.ListItemLessonBinding

import com.melodymediation.sleepstories.ui.lesson.LessonFragment
import com.melodymediation.sleepstories.ui.media.MediaFragment
import com.melodymediation.sleepstories.utilities.PAGE_SIZE
import com.melodymediation.sleepstories.utilities.SESSION_TYPE_MUSIC
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.melodymediation.sleepstories.ui.meditation.MeditationPlayFragment
import com.melodymediation.sleepstories.ui.sessiondetail.SessionDetailFragment
import kotlinx.android.synthetic.main.fragment_media.view.*


class CategoryAdapter(internal var fragment: Fragment, private val categoryListViewModel: CategoryListViewModel, private val fragmentManager: FragmentManager?, internal var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var categories: List<Category>? = null
    private val TYPE_ITEM = 1
    private val TYPE_FOOTER = 2
    override fun getItemCount(): Int {
        return if (categories == null) 0 else categories!!.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER
        }
        return return TYPE_ITEM
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == categories!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_FOOTER) {
            val binding = DataBindingUtil.inflate<FooterViewBinding>(LayoutInflater.from(parent.context), R.layout.footer_view, parent, false)
            return FooterView(binding)
        }
        val binding = DataBindingUtil.inflate<ListItemCategoryBinding>(LayoutInflater.from(parent.context), R.layout.list_item_category, parent, false)

        return CategoryViewHolder(binding, fragment, categoryListViewModel)


    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is CategoryViewHolder) {
            val category = categories!![position]
            viewHolder.binData(category)

            viewHolder.tvSeeAll.setOnClickListener {
                //     val sessionDetailFragment = SessionDetailFragment()
                val bundle = Bundle()
                bundle.putString("com.bigqsys.music.relax.ui.sessiondetail.CATEGORY_ID", category.categoryId)
                //               sessionDetailFragment.arguments = bundle
//                val transaction = fragmentManager?.beginTransaction()
//                transaction?.replace(R.id.fragmentHome, sessionDetailFragment, "SessionDetailFragment")
//                transaction?.addToBackStack(null)
//                transaction?.commit()
//                FragmentUtil.printActivityFragmentList(fragmentManager)

            //    Navigation.findNavController(it).navigate(R.id.sessionDetailFragment, bundle)
                var fragment=SessionDetailFragment()
                fragment.arguments=bundle
                fragmentManager!!.beginTransaction().replace(R.id.layoutCategory,fragment).addToBackStack("fmCategory").commit()
            }
        } else if (viewHolder is FooterView) {

        }
    }

    fun setCategories(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    private inner class FooterView(private val binding: FooterViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }

    }

    inner class CategoryViewHolder(private val mBinding: ListItemCategoryBinding, private val fragment: Fragment, private val categoryListViewModel: CategoryListViewModel) : RecyclerView.ViewHolder(mBinding.root) {

        val tvSeeAll: TextView

        init {
            mBinding.rvLessonList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            tvSeeAll = mBinding.tvSeeAllCategory
        }

        fun binData(category: Category) {
            mBinding.category = category
//            categoryListViewModel.getSessionsByCategoryOption(category.categoryId)
            categoryListViewModel.getSessionsByCategoryLimit(category.categoryId, PAGE_SIZE).observe(fragment, Observer<List<Session>> {
                if (it != null) {
                    mBinding.rvLessonList.adapter = LessonAdapter(it, fragmentManager, context)
                    mBinding.executePendingBindings()
                }
            })
        }
    }

    private inner class LessonAdapter(private val sessions: List<Session>?, private val fragmentManager: FragmentManager?, private val context: Context) : RecyclerView.Adapter<LessonViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
            val listItemLessonBinding = DataBindingUtil.inflate<ListItemLessonBinding>(LayoutInflater.from(parent.context), R.layout.list_item_lesson, parent, false)
            return LessonViewHolder(listItemLessonBinding, fragmentManager, context)
        }

        override fun onBindViewHolder(lessonViewHolder: LessonViewHolder, position: Int) {
            lessonViewHolder.binData(sessions!![position])
        }

        override fun getItemCount(): Int {
            return sessions?.size ?: 0
        }
    }

    private class LessonViewHolder(private val listItemLessonBinding: ListItemLessonBinding, private val fragmentManager: FragmentManager?, internal var context: Context) : RecyclerView.ViewHolder(listItemLessonBinding.root), View.OnClickListener {

        private var hasLock: Boolean = false

        init {
            itemView.setOnClickListener(this)
        }

        fun binData(session: Session) {
            listItemLessonBinding.session = session

            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(25))
            Glide.with(context)
                .load(session.urlBackground)
                .apply(requestOptions)
                .into(listItemLessonBinding.ivThumbLesson)

            listItemLessonBinding.tvLessonName.text = session.name
            listItemLessonBinding.tvLessonQuantity.text = session.count
            listItemLessonBinding.tvLessonTime.text = session.duration
            listItemLessonBinding.rlLockSession.visibility = View.INVISIBLE
            if (!session.isFee && !session.isBuy) {
                listItemLessonBinding.rlLockSession.visibility = View.VISIBLE
                hasLock = true
            }
            if (session.type?.equals(SESSION_TYPE_MUSIC)) {
                listItemLessonBinding.tvLessonQuantity.visibility = View.GONE
            }
        }

        override fun onClick(view: View) {
            if (!hasLock) {
                LessonFragment.nameCategory=listItemLessonBinding.session!!.type
                if (listItemLessonBinding.session!!.type.equals("Music")) {
                   val bundle = Bundle()
                  bundle.putString("com.bigqsys.music.relax.ui.media.SESSION_ID", listItemLessonBinding.session!!.sessionId)
                    val mediaFragment = MediaFragment()
                    mediaFragment.arguments = bundle
                    fragmentManager!!.beginTransaction().replace(R.id.player_wrap, mediaFragment).commit()
                } else {
                    val bundle = Bundle()
                    bundle.putString("com.bigqsys.music.relax.ui.lesson.SESSION_ID", listItemLessonBinding.session!!.sessionId)
                    bundle.putString("TYPE_FR", "CATE")
                    var fragment=LessonFragment()
                    fragment.arguments=bundle
                    fragmentManager!!.beginTransaction().replace(R.id.layoutCategory,fragment).addToBackStack("fmCategory").commit()
                }
            }
        }
    }
}
