package com.sample.todo.work

import androidx.work.WorkManager
import com.sample.todo.base.di.AppScope
import dagger.Module
import dagger.Provides

@Module
abstract class WorkManagerModule {
    @Module
    companion object {
        @Provides
        @AppScope
        @JvmStatic
        fun provideWorkManager(): WorkManager {
            return WorkManager.getInstance()
        }
    }
}