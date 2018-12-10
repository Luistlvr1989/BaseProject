package com.belatrixsf.baseproject.managers.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.support.annotation.VisibleForTesting
import android.arch.persistence.db.SupportSQLiteDatabase
import com.belatrixsf.baseproject.MvpLabKotlinApplication
import com.belatrixsf.baseproject.BuildConfig
import com.belatrixsf.baseproject.models.database.Address
import com.belatrixsf.baseproject.models.database.dao.AddressDao

@Database(entities = [
    Address::class
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @VisibleForTesting
        private const val DATABASE_NAME = "superpet.db"

        private val DEBUG_DATABASE = BuildConfig.DEBUG && BuildConfig.ENABLE_MEMORY_DB

        /**
         * Holder for a singleton thread-safe, and avoid double check for initialization
         */
        private object AppDatabaseHolder {
            internal val INSTANCE: AppDatabase = getDatabaseType()
                    .fallbackToDestructiveMigration()
                    .build()
        }

        fun getDatabaseType(): Builder<AppDatabase> {
            return if (DEBUG_DATABASE) {
                Room.inMemoryDatabaseBuilder(MvpLabKotlinApplication.context, AppDatabase::class.java)
            } else {
                Room.databaseBuilder(MvpLabKotlinApplication.context, AppDatabase::class.java, DATABASE_NAME)
            }
        }

        fun getInstance(): AppDatabase {
            return AppDatabaseHolder.INSTANCE
        }

        fun debugInMemoryRoomDatabase() {
            if (DEBUG_DATABASE) {
                try {
                    val debugDB = Class.forName("com.amitshekhar.DebugDB")
                    val argTypes = arrayOf<Class<*>>(HashMap::class.java)
                    val inMemoryDatabases = HashMap<String, SupportSQLiteDatabase>()
                    inMemoryDatabases["InMemoryOne.db"] = AppDatabaseHolder.INSTANCE.openHelper.writableDatabase
                    val setRoomInMemoryDatabase = debugDB.getMethod("setInMemoryRoomDatabases", *argTypes)
                    setRoomInMemoryDatabase.invoke(null, inMemoryDatabases)
                } catch (ignore: Exception) {

                }
            }
        }
    }

    abstract fun addressDao(): AddressDao
}