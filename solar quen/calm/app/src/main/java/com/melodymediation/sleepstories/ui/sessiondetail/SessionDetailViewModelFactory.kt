package com.melodymediation.sleepstories.ui.sessiondetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.melodymediation.sleepstories.data.repository.CategoryRepository
import com.melodymediation.sleepstories.data.repository.SessionRepository

class SessionDetailViewModelFactory(
    private val categoryRepository: CategoryRepository,
    private val sessionRepository: SessionRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SessionDetailViewModel(categoryRepository, sessionRepository) as T
    }
}
