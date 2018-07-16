package com.belatrixsf.baseproject.models.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.belatrixsf.baseproject.models.database.Address

@Dao
abstract class AddressDao : BaseDao<Address> {
    @Transaction
    open fun updateData(addresses: List<Address>) {
        deleteAll()
        insertAll(addresses)
    }

    @Query("SELECT * FROM addresses")
    abstract fun getAddresses(): LiveData<List<Address>>

    @Query("DELETE FROM addresses")
    abstract fun deleteAll()
}