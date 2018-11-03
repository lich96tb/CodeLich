package com.melodymediation.sleepstories.data.remote


class MelodyRemoteDataSource(private val categoryService: CategoryService,
                             private val sessionService: SessionService,
                             private val versionService: VersionService) {

    fun fetchCategories() = categoryService.getCategories()
    fun fetchCategoriesCall() = categoryService.getCategoriesCall()
    fun fetchCategoriesByParentIdCall(parentId: String) = categoryService.getCategoriesByParentIdCall(parentId)
    fun fetchSessions() = sessionService.getSessions()
    fun getSessionsObservable() = sessionService.getSessionsObservable()
    fun fetchSessionsByCategory(categoryId: String) = sessionService.getSessionsByCategory(categoryId)
    fun fetchSessionsByType(type: String) = sessionService.getSessionsByType(type)
    fun fetchSessionsByParentId(parentId: String) = sessionService.getSessionsByParentId(parentId)

    // Remote serve get versions
    fun fetchVersionsObservable() = versionService.getVersionsObservable()
}
