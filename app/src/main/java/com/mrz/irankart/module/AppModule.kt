package com.mrz.irankart.module

import android.content.Context
import android.content.SharedPreferences

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

/**
 * The [AppModule] class provides objects to inject
 */
@InstallIn(SingletonComponent::class)
@Module
class AppModule {
//    /**
//     * Provide [DbRepo]
//     *
//     * @param boxStore To return DbRepo
//     * @return Singleton [DbRepo]
//     */
//    @Provides
//    @Singleton
//    fun provideDbRepo(boxStore: BoxStore?): DbRepo {
//        return DbRepo(boxStore!!)
//    }
//
//    /**
//     * Provide [BoxStore]
//     *
//     * @param context Application [Context]
//     * @return Singleton [BoxStore]
//     */
//    @Provides
//    @Singleton
//    fun provideBoxStore(@ApplicationContext context: Context): BoxStore {
//        val boxStore = MyObjectBox.builder()
//            .androidContext(context)
//            .name(context.getString(R.string.RFID_READER_DB))
//            .build()
//        if (BuildConfig.DEBUG) {
//            AndroidObjectBrowser(boxStore).start(context)
//        }
//        return boxStore
//    }
//
//    /**
//     * Provide [AppDataManager]
//     *
//     * @param context Application [Context]
//     * @return Singleton [AppDataManager]
//     */
//    @Provides
//    @Singleton
//    fun provideDataManager(@ApplicationContext context: Context): AppDataManager {
//        return AppDataManager(context)
//    }
//

}