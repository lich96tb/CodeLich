package com.melodymediation.sleepstories.ui.lesson

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.melodymediation.sleepstories.data.remote.toSessionEntity
import com.melodymediation.sleepstories.data.repository.CategoryRepository
import com.melodymediation.sleepstories.data.room.Session
import com.melodymediation.sleepstories.data.repository.SessionRepository
import com.melodymediation.sleepstories.data.room.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LessonViewModel internal constructor(
    private val sessionRepository: SessionRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    fun getLessonsByParentId(parentId: String): LiveData<List<Session>> {
        return sessionRepository.getSessionByParentId(parentId)
    }

    fun getLessonById(lessonId: String): Session {
        return sessionRepository.getSessionById(lessonId)
    }

    fun getCategoryById(categoryId: String): Category {
        return categoryRepository.getCategoryById(categoryId)
    }

    fun getLessonsByParentIdOption(parentId: String) {
        sessionRepository.fetchSessionsByParentIdCall(parentId).observeOn(AndroidSchedulers.mainThread())
            .map { sessionApiList -> sessionApiList.map{ it.toSessionEntity()} }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                result -> sessionRepository.createSessions(result)
            }, { error ->
                error.printStackTrace()
            })
    }
}
