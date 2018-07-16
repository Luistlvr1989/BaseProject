package com.belatrixsf.baseproject.managers

import com.belatrixsf.baseproject.managers.database.AppDatabase
import com.belatrixsf.superpet.managers.database.AppExecutors
import java.io.Serializable

object DataManager : Serializable {
    private val db = AppDatabase.getInstance()
    private val executors = AppExecutors()

    /*fun addAllCategories(categories: List<Category>) {
        executors.networkIO().execute { db.categoryDao().updateData(categories) }
    }*/
}