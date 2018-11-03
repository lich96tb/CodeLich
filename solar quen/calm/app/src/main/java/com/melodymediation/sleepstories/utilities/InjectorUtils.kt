package com.melodymediation.sleepstories.utilities
import android.content.Context
import com.melodymediation.sleepstories.data.remote.*
import com.melodymediation.sleepstories.ui.category.CategoryListViewModelFactory
import com.melodymediation.sleepstories.ui.lesson.LessonViewModelFactory
import com.melodymediation.sleepstories.data.room.AppDatabase
import com.melodymediation.sleepstories.data.repository.CategoryRepository
import com.melodymediation.sleepstories.data.repository.SessionRepository
import com.melodymediation.sleepstories.data.repository.VersionRepository
import com.melodymediation.sleepstories.ui.sessiondetail.SessionDetailViewModelFactory

object InjectorUtils {

    fun getCategoryRepository(context: Context): CategoryRepository {
        return CategoryRepository.getInstance(AppDatabase.getInstance(context).categoryDao(), MelodyRemoteDataSource(getCategoryService(), getSessionService(), getVersionService()))
    }

    private fun getCategoryRepositorySimple(context: Context): CategoryRepository {
        return CategoryRepository.getInstance(AppDatabase.getInstance(context).categoryDao(), MelodyRemoteDataSource(getCategorySimple(), getSessionService(), getVersionService()) )
    }

    fun getSessionRepository(context: Context): SessionRepository {
        return SessionRepository.getInstance(AppDatabase.getInstance(context).sessionDao(), MelodyRemoteDataSource(getCategoryService(), getSessionService(), getVersionService()),context)
    }

    fun getVersionRepository(context: Context): VersionRepository {
        return VersionRepository.getInstance(AppDatabase.getInstance(context).versionDao(), MelodyRemoteDataSource(getCategoryService(), getSessionService(), getVersionService()))
    }

    fun provideLessonViewModelFactory(context: Context): LessonViewModelFactory {
        val sessionRepository = getSessionRepository(context)
        val categoryRepository = getCategoryRepository(context)
        return LessonViewModelFactory(sessionRepository, categoryRepository)
    }

    fun provideCategoryListViewModelFactory(context: Context): CategoryListViewModelFactory {
        val categoryRepository = getCategoryRepository(context)
        val sessionRepository = getSessionRepository(context)
        return CategoryListViewModelFactory(categoryRepository, sessionRepository)
    }

    fun provideSessionDetailViewModelFactory(context: Context): SessionDetailViewModelFactory {
        val categoryRepository = getCategoryRepository(context)
        val sessionRepository = getSessionRepository(context)
        return SessionDetailViewModelFactory(categoryRepository, sessionRepository)
    }
}
