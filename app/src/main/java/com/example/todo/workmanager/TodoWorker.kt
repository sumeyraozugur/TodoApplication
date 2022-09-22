package com.example.todo.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.todo.R

class TodoWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        createNotification()
        return Result.success()
    }

    private fun createNotification() {

        val builder: NotificationCompat.Builder

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "channelId"
        val channelName = "channelName"
        val channelIntroduction = "channelIntroduction"
        val channelPriority = NotificationManager.IMPORTANCE_HIGH

        if (Build.VERSION.SDK_INT >= 26) {

            var channel: NotificationChannel? = notificationManager
                .getNotificationChannel(channelId)

            if (channel == null) {
                channel = NotificationChannel(channelId, channelName, channelPriority)
                channel.description = channelIntroduction
                notificationManager.createNotificationChannel(channel)
            }

            builder = NotificationCompat.Builder(applicationContext, channelId)
            builder
                .setContentTitle("Hello!")
                .setContentText("You have some targets in todolist. ")
                .setSmallIcon(R.drawable.exam)
                .setAutoCancel(true)

            notificationManager.notify(1, builder.build())
        }
    }
}
