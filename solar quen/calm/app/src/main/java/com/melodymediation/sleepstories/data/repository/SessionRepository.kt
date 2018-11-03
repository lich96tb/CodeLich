package com.melodymediation.sleepstories.data.repository

import android.content.Context
import com.melodymediation.sleepstories.data.remote.MelodyRemoteDataSource
import com.melodymediation.sleepstories.data.remote.SessionResponse
import com.melodymediation.sleepstories.data.room.Session
import com.melodymediation.sleepstories.data.room.SessionDao
import com.melodymediation.sleepstories.utilities.runOnIoThread
import io.reactivex.Observable
import com.melodymediation.sleepstories.service.downloadlistimage.DownLoadListImage
import com.melodymediation.sleepstories.service.downloadlistmp3.DownloadListMp3

class SessionRepository
private constructor(
    private val sessionDao: SessionDao,
    private val moviesRemoteDataSource: MelodyRemoteDataSource,
    private val context: Context
) {

    fun createSession(session: Session) {
        runOnIoThread {
            val sessions = ArrayList<Session>()
            sessions.add(session)
            sessionDao.insertAll(sessions)
        }
    }

    fun createSessions(sessions: List<Session>) {
        if (sessions.isEmpty()) {
            return
        }
        runOnIoThread {
            for (session in sessions) {
                val sessionLocal: Session = sessionDao.getSessionById(session.sessionId)
                if (sessionLocal != null) {
                    if (sessionLocal.url?.equals(session.url)) {
                        session.urlDist = sessionLocal.urlDist
                    }
                    if (sessionLocal.urlBackground?.equals(session.urlBackground)) {
                        session.urlBackgroundDist = sessionLocal.urlBackgroundDist
                    }
                }
            }
            sessionDao.insertAll(sessions)
            var doawLoadListImage = DownLoadListImage(context);
            doawLoadListImage.execute()
//            var downloadListMp3 = DownloadListMp3(context)
//            downloadListMp3.execute()
        }

    }

    fun getAllSession() = sessionDao.getSessions()

    fun getSessionByCategory(categoryId: String) = sessionDao.getSessionsByCategory(categoryId)

    fun getSessionByType(type: String) = sessionDao.getSessionsByType(type)

    fun getSessionByCategoryLimit(categoryId: String, pageSize: Int) = sessionDao.getSessionsByCategoryLimit(categoryId, pageSize)

    fun getSessionByParentId(parentId: String) = sessionDao.getSessionsByParentId(parentId)

    fun getSessionById(sessionId: String) = sessionDao.getSessionById(sessionId)
    fun updateUrlDist(sessionId: String, urlDistUpdate: String) = sessionDao.updateSessionUrlDist(sessionId, urlDistUpdate)
    fun updateSessionUrlBackground(sessionId: String, urlbackgroundDistUpdate: String) = sessionDao.updateSessionUrlBackground(sessionId, urlbackgroundDistUpdate)


    fun fetchSessionsObservable(): Observable<List<SessionResponse>> {
        return moviesRemoteDataSource.getSessionsObservable()
    }

    fun fetchSessionsByParentIdCall(parentId: String): Observable<List<SessionResponse>> {
        return moviesRemoteDataSource.fetchSessionsByParentId(parentId)
    }

    fun fetchSessionsByCategoryCall(categoryId: String): Observable<List<SessionResponse>> {
        return moviesRemoteDataSource.fetchSessionsByCategory(categoryId)
    }

    fun fetchSessionsByTypeCall(type: String): Observable<List<SessionResponse>> {
        return moviesRemoteDataSource.fetchSessionsByType(type)
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: SessionRepository? = null

        fun getInstance(sessionDao: SessionDao, moviesRemoteDataSource: MelodyRemoteDataSource, context: Context) =
            instance ?: synchronized(this) {
                instance
                    ?: SessionRepository(sessionDao, moviesRemoteDataSource, context).also { instance = it }
            }


    }
}
