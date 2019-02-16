package com.sample.todo.domain.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.sample.todo.domain.model.TaskFilterType
import com.sample.todo.domain.repository.PreferenceRepository
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTaskFilterTypeTest {

    @Test
    fun `data flow as usual`() {
        val expected = TaskFilterType.values().toList()
        val filterTypeOrdinals = Observable.fromIterable(expected.map { it.ordinal })
        val preferenceRepository: PreferenceRepository = mock {
            on { getTaskFilterTypeOrdinalFlowable() } doReturn filterTypeOrdinals
        }
        val result = GetTaskFilterType(preferenceRepository).invoke()

        val actual = result.test().values()
        assertEquals(expected, actual)
    }

    @Test
    fun `data flow correct when ordinal is invalid`() {
        val preferenceRepository: PreferenceRepository = mock {
            on { getTaskFilterTypeOrdinalFlowable() } doReturn Observable.just(-2)
        }
        val result = GetTaskFilterType(preferenceRepository).invoke()

        result.test()
            .assertValue(TaskFilterType.ALL)
    }

    @Test
    fun `data flow dictint until changed`() {
        val ordinals = listOf(0, 1, 1, 2)
        val preferenceRepository: PreferenceRepository = mock {
            on { getTaskFilterTypeOrdinalFlowable() } doReturn Observable.fromIterable(ordinals)
        }
        val result = GetTaskFilterType(preferenceRepository).invoke()

        val expected = listOf(
            TaskFilterType.ALL,
            TaskFilterType.COMPLETED,
            TaskFilterType.ACTIVE

        )
        val actual = result.test().values()
        assertEquals(expected, actual)
    }
}