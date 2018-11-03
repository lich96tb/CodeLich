package com.melodymediation.sleepstories.service

import com.melodymediation.sleepstories.data.remote.toCategoryEntity
import com.melodymediation.sleepstories.data.remote.toSessionEntity
import com.melodymediation.sleepstories.data.remote.toVersionEntity
import com.melodymediation.sleepstories.data.repository.CategoryRepository
import com.melodymediation.sleepstories.data.repository.SessionRepository
import com.melodymediation.sleepstories.data.repository.VersionRepository
import com.melodymediation.sleepstories.utilities.SESSION_TYPE_BACKGROUND
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SyncDataService(private val categoryRepository: CategoryRepository, private val sessionRepository: SessionRepository, private val versionRepository: VersionRepository) {

    private fun syncBackgrounds() {
        sessionRepository.fetchSessionsByTypeCall(SESSION_TYPE_BACKGROUND).observeOn(AndroidSchedulers.mainThread())
            .map { sessionApiList -> sessionApiList.map { it.toSessionEntity() } }
            .subscribeOn(Schedulers.io())
            .subscribe({ result -> sessionRepository.createSessions(result)
            }, { error ->
                error.printStackTrace()
            })
    }

    private fun syncCategories() {
        categoryRepository.fetchCategoryCall().observeOn(AndroidSchedulers.mainThread())
            .map { categoryApiList -> categoryApiList.map{ it.toCategoryEntity()} }
            .subscribeOn(Schedulers.io())
            .subscribe ({
                result -> categoryRepository.storeCategories(result)
            }, { error ->
                error.printStackTrace()
            })
    }

    private fun syncSessions() {
        sessionRepository.fetchSessionsObservable().observeOn(AndroidSchedulers.mainThread())
            .map { sessionApiList -> sessionApiList.map { it.toSessionEntity() } }
            .subscribeOn(Schedulers.io())
            .subscribe({ result -> sessionRepository.createSessions(result)
            }, { error ->
                error.printStackTrace()
            })
    }

    fun syncAllData() {
        val versionsLocal = versionRepository.getVersionsList()
        versionRepository.fetchVersionsObservable().observeOn(AndroidSchedulers.mainThread())
            .map { versionApi -> versionApi.map { it.toVersionEntity() } }
            .subscribe({
                if (versionsLocal.isEmpty()) {
                    syncBackgrounds()
                    syncCategories()
                    syncSessions()
                    versionRepository.createVersions(it)
                } else {
                    for (version in it) {
                        if (version?.name != versionsLocal?.get(0).name) {
                            syncBackgrounds()
                            syncCategories()
                            syncSessions()
                            versionRepository.createVersions(it)
                            break
                        }
                    }
                }
            }, {
                error -> error.printStackTrace()
            })
    }
}
