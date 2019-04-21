package com.sample.todo.base.notification

import androidx.annotation.StringRes
import androidx.core.app.NotificationManagerCompat
import com.sample.todo.base.R

enum class NotificationChannelInformation(
    @StringRes val title: Int,
    @StringRes val description: Int,
    val importance: Int
) {
    SeedDatabase(
        title = R.string.notification_channel_seed_database_name,
        description = R.string.notification_channel_seed_database_description,
        importance = NotificationManagerCompat.IMPORTANCE_DEFAULT
    ),
    DownloadModule(
        title = R.string.notification_channel_seed_database_name,
        description = R.string.notification_channel_seed_database_description,
        importance = NotificationManagerCompat.IMPORTANCE_DEFAULT
    )
}
