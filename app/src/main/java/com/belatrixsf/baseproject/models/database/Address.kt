package com.belatrixsf.baseproject.models.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(
        @ColumnInfo(name = "id") @PrimaryKey var id: Long,
        @ColumnInfo(name = "alias") var alias: String,
        @ColumnInfo(name = "address1") var address1: String,
        @ColumnInfo(name = "address2") var address2: String,
        @ColumnInfo(name = "country_id") var countryId: Long,
        @ColumnInfo(name = "state_id") var stateId: Long,
        @ColumnInfo(name = "city") var city: String,
        @ColumnInfo(name = "phone") var phone: String,
        @ColumnInfo(name = "firstname") var firstname: String,
        @ColumnInfo(name = "lastname") var lastname: String
)