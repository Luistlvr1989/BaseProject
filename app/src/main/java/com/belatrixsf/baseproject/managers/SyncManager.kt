package com.belatrixsf.baseproject.managers

import java.io.Serializable

object SyncManager : Serializable {
    /*fun syncCategories(): Observable<List<Category>> {
        return ApiClient.execute(CategoriesRequest())
                .flatMap { result ->
                    val categories = mutableListOf<Category>()
                    buildCategoryList(result, categories)
                    DataManager.addAllCategories(categories)
                    Observable.just(categories.toList())
                }
                .observeOn(AndroidSchedulers.mainThread())
    }*/
}
