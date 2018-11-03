package com.melodymediation.sleepstories.data.repository

import com.melodymediation.sleepstories.data.remote.MelodyRemoteDataSource
import com.melodymediation.sleepstories.data.room.Category
import com.melodymediation.sleepstories.data.room.CategoryDao
import com.melodymediation.sleepstories.utilities.runOnIoThread
import io.reactivex.Observable
import com.melodymediation.sleepstories.data.remote.CategoryResponse

class CategoryRepository private constructor(
    private val categoryDao: CategoryDao,
    private val moviesRemoteDataSource: MelodyRemoteDataSource
) {
//    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()
    fun getCategories() = categoryDao.getCategories()

    fun getCategoriesByParentId(parentId: String) = categoryDao.getCategoriesByParentId(parentId)

    fun getCategoryById(categoryId: String) = categoryDao.getCategoryById(categoryId)

    fun getCategoriesParent() = categoryDao. getCategoriesParent()

    fun storeCategories(categories: List<Category>) {
        runOnIoThread {
            categoryDao.insertAll(categories)
        }
    }

    fun createCategory(category: Category) {
        runOnIoThread {
            val categories = ArrayList<Category>()
            categories.add(category)
            categoryDao.insertAll(categories)
        }
    }

    fun fetchCategoriesByParentIdCall(parentId: String) : Observable<List<CategoryResponse>>{
        return moviesRemoteDataSource.fetchCategoriesByParentIdCall(parentId)
    }

//    fun fetchOrGetCategory() : Observable<PagedList<Category>> = RxPagedListBuilder(getCategoryBy1(), 20)
//        .setBoundaryCallback(PageListCategoryBoundaryCallback(moviesRemoteDataSource, categoryDao))
//        .buildObservable()
//    fun getRemoteCategorys(): LiveData<PaginationPage<CategoryResponse>> {
//
//        val mutableLiveData = MutableLiveData<PaginationPage<CategoryResponse>>()
//        val disposable = remoteCategoryService.getCategories()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ currencyResponse ->
//                mutableLiveData.value = currencyResponse
//
//            }, { t: Throwable? -> t?.printStackTrace() })
//        allCompositeDisposable.add(disposable)
//        return mutableLiveData
//    }
    fun fetchCategoryCall() : Observable<List<CategoryResponse>>{
//        moviesRemoteDataSource.fetchCategoriesCall().enqueue(object : Callback<List<CategoryResponse>>() {
//            override fun onResponse(call: Call<List<CategoryResponse>>, response: Response<List<CategoryResponse>>) {
//                Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show()
//                executor.execute({
//                    val user = response.body()
//                    user.setLastRefresh(Date())
//                    userDao.save(user)
//                })
//                Log.d("AAAA",response.body().toString());
//            }
//
//             fun onFailure(call: List<CategoryResponse>, t: Throwable) {}
//        });
        return moviesRemoteDataSource.fetchCategoriesCall()
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CategoryRepository? = null

        fun getInstance(categoryDao: CategoryDao, moviesRemoteDataSource: MelodyRemoteDataSource) =
                instance ?: synchronized(this) {
                    instance
                        ?: CategoryRepository(categoryDao, moviesRemoteDataSource).also { instance = it }
                }
    }
}
