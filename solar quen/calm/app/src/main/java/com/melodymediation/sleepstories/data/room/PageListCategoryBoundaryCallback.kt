package com.melodymediation.sleepstories.data.room

import android.arch.paging.PagedList
import android.util.Log
import com.melodymediation.sleepstories.data.remote.MelodyRemoteDataSource
import com.melodymediation.sleepstories.data.remote.toCategoryEntity

import io.reactivex.schedulers.Schedulers

class PageListCategoryBoundaryCallback(private val melodyRemoteDataSource: MelodyRemoteDataSource,
                                    private val categoryDao: CategoryDao) : PagedList.BoundaryCallback<Category>() {

    private var isRequestRunning = false
    private var requestedPage = 1

    override fun onZeroItemsLoaded() {
        Log.i(TAG, "onZeroItemsLoaded")
        fetchAndStoreCategory()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Category) {
        Log.i(TAG, "onItemAtEndLoaded")
        fetchAndStoreCategory()
    }

    private fun storeCategories(categories: List<Category>) = categoryDao.insertAll(categories)

    private fun fetchAndStoreCategory() {
        Log.i(TAG, "Request......")
        if (isRequestRunning) return

        isRequestRunning = true

        melodyRemoteDataSource.fetchCategories()
            .map { categoryApiList -> categoryApiList.map{ it.toCategoryEntity()} }
            .doOnSuccess { categories ->
                Log.i(TAG, "Inserted: ${categories.size}")
                if (categories.isNotEmpty()) {
                    this.storeCategories(categories)

                    Log.i(TAG, "Inserted: ${categories.size}")
                } else {
                    Log.i(TAG, "No Inserted")
                }
                requestedPage++
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .toCompletable()
            .doFinally { isRequestRunning = false }
            .subscribe({ Log.i(TAG, "Movies Completed") }, { it.printStackTrace() })

    }

    companion object {
        private const val TAG: String = "PageListCateBoundary "
    }
}
