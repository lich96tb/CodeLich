package com.melodymediation.sleepstories.ui.lesson

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.melodymediation.sleepstories.data.repository.CategoryRepository
import com.melodymediation.sleepstories.data.repository.SessionRepository

class LessonViewModelFactory(
    private val sessionRepository: SessionRepository,
    private val categoryRepository: CategoryRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LessonViewModel(sessionRepository, categoryRepository) as T
    }
}
