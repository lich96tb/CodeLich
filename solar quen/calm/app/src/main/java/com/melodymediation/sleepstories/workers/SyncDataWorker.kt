package com.melodymediation.sleepstories.workers

import androidx.work.Worker
import com.melodymediation.sleepstories.data.repository.CategoryRepository
import com.melodymediation.sleepstories.data.repository.SessionRepository
import com.melodymediation.sleepstories.data.repository.VersionRepository
import com.melodymediation.sleepstories.service.SyncDataService
import com.melodymediation.sleepstories.utilities.InjectorUtils

class SyncDataWorker : Worker() {

    override fun doWork(): Result = try {
        Thread.sleep(3000)

        val versionRepository: VersionRepository = InjectorUtils.getVersionRepository(applicationContext)
        val sessionRepository: SessionRepository = InjectorUtils.getSessionRepository(applicationContext)
        val categoryRepository: CategoryRepository = InjectorUtils.getCategoryRepository(applicationContext)
        val syncDataService = SyncDataService(categoryRepository, sessionRepository, versionRepository)

        // Remote server sync data to local
        syncDataService.syncAllData()

        Result.SUCCESS
    } catch (e: Throwable) {
        Result.FAILURE
    }
}
