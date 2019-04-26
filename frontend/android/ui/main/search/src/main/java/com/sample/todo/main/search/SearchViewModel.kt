package com.sample.todo.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.ViewModelContext
import com.sample.todo.base.entity.Event
import com.sample.todo.base.extension.getFragment
import com.sample.todo.domain.usecase.GetSearchResultStatisticsObservable
import com.sample.todo.domain.usecase.SearchTask
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

// TODO how to dispose observable/emitter
class SearchViewModel @AssistedInject constructor(
    @Assisted private val initialState: SearchState,
    private val searchTask: SearchTask,
    private val getSearchResultStatisticsObservable: GetSearchResultStatisticsObservable
) : com.sample.todo.base.MvRxViewModel<SearchState>(initialState) {

    private var query = BehaviorSubject.create<String>()

    init {
        query.debounce(500L, TimeUnit.MILLISECONDS)
            .distinctUntilChanged().apply {
                switchMap { searchTask(it) }.execute { copy(searchResult = it()) }
                switchMap { getSearchResultStatisticsObservable(it) }.execute { copy(searchResultStatistics = it()) }
            }
    }

    private val _navigateToTaskDetailEvent = MutableLiveData<Event<NavDirections>>()
    val navigateToTaskDetailEvent: LiveData<Event<NavDirections>>
        get() = _navigateToTaskDetailEvent

    fun onResultItemClick(taskId: String) {
        Timber.d("onResultItemClick(taskId=$taskId)")
        _navigateToTaskDetailEvent.value = Event(SearchFragmentDirections.toTaskDetailFragment(taskId))
    }

    fun onSearchTextChange(text: String?): Boolean {
        Timber.d("onSearchTextChange(text=$text)")
        query.onNext(text ?: "")
        return true
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: SearchState): SearchViewModel
    }

    companion object : MvRxViewModelFactory<SearchViewModel, SearchState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: SearchState
        ): SearchViewModel? {
            return viewModelContext.getFragment<SearchFragment>().viewModelFactory.create(state)
        }

        override fun initialState(viewModelContext: ViewModelContext): SearchState? {
            return SearchState(
                searchResult = null,
                searchResultStatistics = null
            )
        }
    }
}
