package com.sample.todo.dynamic.settings.domain.usecase

import com.sample.todo.dynamic.settings.domain.repository.SettingsPreferenceRepository
import javax.inject.Inject

class SetName @Inject constructor(
    private val preferenceRepository: SettingsPreferenceRepository
) {
    suspend operator fun invoke(name: String) {
        val realName = name.trim()
        if (realName.isEmpty()) throw IllegalArgumentException("Name can not empty")
        preferenceRepository.setName(realName)
    }
}
