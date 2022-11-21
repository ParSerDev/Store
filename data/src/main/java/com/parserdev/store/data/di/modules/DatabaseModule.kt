package com.parserdev.store.data.di.modules

import android.content.Context
import com.parserdev.store.data.database.StoreDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDb(context: Context): StoreDatabase {
        return StoreDatabase.getInstance(context)
    }
}