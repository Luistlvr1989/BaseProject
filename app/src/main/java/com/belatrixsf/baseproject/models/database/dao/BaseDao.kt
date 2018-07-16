package com.belatrixsf.baseproject.models.database.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

interface BaseDao<T> {
    @Insert
    fun insert(element: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(elements: List<T>)

    @Update
    fun update(vararg element: T)

    @Delete
    fun delete(element: T)

    @Delete
    fun deleteAll(elements: List<T>)
}