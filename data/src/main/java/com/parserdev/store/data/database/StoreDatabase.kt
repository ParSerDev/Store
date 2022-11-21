package com.parserdev.store.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.parserdev.store.data.database.dao.HomeDao
import com.parserdev.store.data.dto.home.FavouriteItemDto

@Database(
    entities = [FavouriteItemDto::class],
    version = 1,
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun homeDao(): HomeDao

    companion object {

        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getInstance(context: Context): StoreDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                StoreDatabase::class.java, "Store"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}