package com.sample.todo.di

import com.sample.todo.work.WorkerFactoryBindingModule
import com.sample.todo.work.downloadmodule.DownloadModuleWorkerModule
import dagger.Module

@Module(
    includes = [
        WorkerFactoryBindingModule::class,
        DownloadModuleWorkerModule::class
    ]
)
interface WorkModule
