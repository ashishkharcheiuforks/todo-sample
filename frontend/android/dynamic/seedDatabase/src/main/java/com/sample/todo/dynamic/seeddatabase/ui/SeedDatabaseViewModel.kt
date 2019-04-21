package com.sample.todo.dynamic.seeddatabase.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sample.todo.dynamic.seeddatabase.worker.Parameter
import com.sample.todo.dynamic.seeddatabase.worker.SeedDatabaseWorker
import javax.inject.Inject
import javax.inject.Provider

class SeedDatabaseViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {
    val totalTask = MutableLiveData<String>().apply { value = 10.toString() }

    fun onSeedButtonClick() {
        val param = Parameter()
        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
            .setInputData(param.toData())
            .build()
        workManager.enqueueUniqueWork("aka", ExistingWorkPolicy.REPLACE, request)
    }

    class Factory @Inject constructor(
        private val provider: Provider<SeedDatabaseViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return provider.get() as T
        }
    }
}
