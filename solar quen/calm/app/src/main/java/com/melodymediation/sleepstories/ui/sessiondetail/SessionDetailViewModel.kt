package com.melodymediation.sleepstories.ui.sessiondetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.melodymediation.sleepstories.data.remote.toSessionEntity
import com.melodymediation.sleepstories.data.repository.CategoryRepository
import com.melodymediation.sleepstories.data.repository.SessionRepository
import com.melodymediation.sleepstories.data.room.Category
import com.melodymediation.sleepstories.data.room.Session
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SessionDetailViewModel(
    private val categoryRepository: CategoryRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    fun getLessonsByCategoryId(categoryId: String): LiveData<List<Session>> {
        return sessionRepository.getSessionByCategory(categoryId)
    }


    fun getCategoryById(categoryId: String): Category {
        return categoryRepository.getCategoryById(categoryId)
    }

    fun getLessonsByParentId(parentId: String): LiveData<List<Session>> {
        return sessionRepository.getSessionByParentId(parentId)
    }

    fun getSessionsByCategoryOption(categoryId: String) {
        sessionRepository.fetchSessionsByCategoryCall(categoryId).observeOn(AndroidSchedulers.mainThread())
            .map { sessionApiList -> sessionApiList.map { it.toSessionEntity() } }
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                sessionRepository.createSessions(result)
            }, { error ->
                error.printStackTrace()
            })
    }

    fun getLessonsByType(type: String): List<Session> {
        return sessionRepository.getSessionByType(type)
    }

    fun getSessionsByTypeOption(type: String) {
        sessionRepository.fetchSessionsByTypeCall(type).observeOn(AndroidSchedulers.mainThread())
            .map { sessionApiList -> sessionApiList.map { it.toSessionEntity() } }
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                sessionRepository.createSessions(result)
            }, { error ->
                error.printStackTrace()
            })
    }

    fun getSessionById(sessionId: String): Session {
        return sessionRepository.getSessionById(sessionId)
    }

    fun updateSessionUrlDist(sessionId: String,updateUrlDist:String) {
        return sessionRepository.updateUrlDist(sessionId,updateUrlDist)
    }
    fun updateSessionUrlBackground(sessionId: String,updateUrlDistBackground:String) {
        return sessionRepository.updateSessionUrlBackground(sessionId,updateUrlDistBackground)
    }

}
