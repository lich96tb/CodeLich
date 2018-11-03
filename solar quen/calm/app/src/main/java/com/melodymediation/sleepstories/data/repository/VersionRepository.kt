package com.melodymediation.sleepstories.data.repository

import com.melodymediation.sleepstories.data.remote.MelodyRemoteDataSource
import com.melodymediation.sleepstories.data.remote.VersionResponse
import com.melodymediation.sleepstories.data.room.Version
import com.melodymediation.sleepstories.data.room.VersionDao
import com.melodymediation.sleepstories.utilities.runOnIoThread
import io.reactivex.Observable

class VersionRepository private constructor(
    private val versionDao: VersionDao,
    private val moviesRemoteDataSource: MelodyRemoteDataSource
) {

    fun createVersions(versions: List<Version>) {
        runOnIoThread {
            versionDao.insertAll(versions)
        }
    }

    fun getVersionsList() = versionDao.getVersionsList()

    fun fetchVersionsObservable() : Observable<List<VersionResponse>> {
        return moviesRemoteDataSource.fetchVersionsObservable()
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: VersionRepository? = null

        fun getInstance(versionDao: VersionDao, moviesRemoteDataSource: MelodyRemoteDataSource) =
            instance ?: synchronized(this) {
                instance
                    ?: VersionRepository(versionDao, moviesRemoteDataSource).also { instance = it }
            }
    }
}
