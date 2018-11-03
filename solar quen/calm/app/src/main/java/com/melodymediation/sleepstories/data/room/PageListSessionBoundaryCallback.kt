package com.melodymediation.sleepstories.data.room

import android.arch.paging.PagedList
import android.util.Log
import com.melodymediation.sleepstories.data.remote.MelodyRemoteDataSource
import com.melodymediation.sleepstories.data.remote.toSessionEntity
import io.reactivex.schedulers.Schedulers

class PageListSessionBoundaryCallback(private val melodyRemoteDataSource: MelodyRemoteDataSource,
                                      private val sessionDao: SessionDao) : PagedList.BoundaryCallback<Session>() {

    private var isRequestRunning = false
    private var requestedPage = 1

    override fun onZeroItemsLoaded() {
        Log.i(TAG, "onZeroItemsLoaded")
        fetchAndStoreSession()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Session) {
        Log.i(TAG, "onItemAtEndLoaded")
        fetchAndStoreSession()
    }

    fun storeSessions(sessions: List<Session>) = sessionDao.insertAll(sessions)

    private fun fetchAndStoreSession() {
        Log.i(TAG, "Request......")
        if (isRequestRunning) return

        isRequestRunning = true

        melodyRemoteDataSource.fetchSessions()
            .map { sessionApiList -> sessionApiList.map{ it.toSessionEntity()} }
            .doOnSuccess { sessions ->
                Log.i(TAG, "Inserted: ${sessions.size}")
                if (sessions.isNotEmpty()) {
                    this.storeSessions(sessions)

                    Log.i(TAG, "Inserted: ${sessions.size}")
                } else {
                    Log.i(TAG, "No Inserted")
                }
                requestedPage++
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .toCompletable()
            .doFinally { isRequestRunning = false }
            .subscribe({ Log.i(TAG, "Sessions Completed") }, { it.printStackTrace() })
    }

    companion object {
        private const val TAG: String = "PLSessionBoundary "
    }
}
