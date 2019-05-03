package com.sample.todo.main.tasks.ui

import androidx.paging.PagedList
import com.airbnb.mvrx.MvRxState
import com.sample.todo.base.widget.FabData
import com.sample.todo.base.widget.ToolbarData
import com.sample.todo.main.tasks.library.domain.entity.TaskFilterType
import com.sample.todo.main.tasks.library.domain.entity.TaskMini

data class TasksState(
    val toolbarData: ToolbarData?,
    val fabData: FabData?,
    val isInEditState: Boolean,
    val selectAll: Boolean,
    val selectedIds: HashSet<String>?,
    val filterType: TaskFilterType?,
    val tasksMini: PagedList<TaskMini>?,
    val isNoTasks: Boolean?,
    val noTaskIcon: Int?,
    val noTaskLabel: Int?,
    val isToolbarSelected: Boolean,
    val toolbarSubTitle: Int?
) : MvRxState
