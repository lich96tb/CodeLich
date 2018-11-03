package com.melodymediation.sleepstories.ui.category

import android.arch.lifecycle.ViewModel
import com.melodymediation.sleepstories.data.room.Category
import com.melodymediation.sleepstories.data.repository.CategoryRepository
import io.reactivex.disposables.CompositeDisposable
import android.arch.lifecycle.LiveData
import com.melodymediation.sleepstories.data.remote.toCategoryEntity
import com.melodymediation.sleepstories.data.remote.toSessionEntity

import com.melodymediation.sleepstories.data.room.Session
import com.melodymediation.sleepstories.data.repository.SessionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryListViewModel(private val categoryRepository: CategoryRepository,
                            private val sessionRepository: SessionRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getRemoteCategoriesOption() {
        categoryRepository.fetchCategoryCall().observeOn(AndroidSchedulers.mainThread())
            .map { categoryApiList -> categoryApiList.map{ it.toCategoryEntity()} }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                result -> categoryRepository.storeCategories(result)
            }, { error ->
                error.printStackTrace()
            })
    }

    fun getRemoteCategoriesByParentId(parentId: String) {
        categoryRepository.fetchCategoriesByParentIdCall(parentId).observeOn(AndroidSchedulers.mainThread())
            .map { categoryApiList -> categoryApiList.map{ it.toCategoryEntity()} }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                result -> categoryRepository.storeCategories(result)
            }, { error ->
                error.printStackTrace()
            })
    }

    fun getCategories(): LiveData<List<Category>> {
        return categoryRepository.getCategories()
    }

    fun getCategoriesByParentId(parentId: String): LiveData<List<Category>> {
        return categoryRepository.getCategoriesByParentId(parentId)
    }

    fun getCategoryById(categoryId: String): Category {
        return categoryRepository.getCategoryById(categoryId)
    }

    fun getCategoriesParent(): List<Category> {
        return categoryRepository.getCategoriesParent()
    }

    fun addCategory(category: Category) {
        categoryRepository.createCategory(category)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getSessionsByCategoryLimit(categoryId: String, pageSize: Int): LiveData<List<Session>> {
        return sessionRepository.getSessionByCategoryLimit(categoryId, pageSize)
    }

    fun getSessionsByCategoryOption(categoryId: String) {
        sessionRepository.fetchSessionsByCategoryCall(categoryId).observeOn(AndroidSchedulers.mainThread())
            .map { sessionApiList -> sessionApiList.map{ it.toSessionEntity()} }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                result -> sessionRepository.createSessions(result)
            }, { error ->
                error.printStackTrace()
            })
    }

    fun createSession(session: Session) {
        sessionRepository.createSession(session)
    }
}
