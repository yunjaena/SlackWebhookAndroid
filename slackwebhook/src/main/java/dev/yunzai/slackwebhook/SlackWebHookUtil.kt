package dev.yunzai.slackwebhook

import android.content.Context
import androidx.work.*
import com.google.gson.Gson
import dev.yunzai.slackwebhook.SlackWebHookWorker.Companion.BODY


fun SlackWebHook.send(context: Context) {
    val requestBodyString = Gson().toJson(this)

    val webHookWork = OneTimeWorkRequestBuilder<SlackWebHookWorker>()
        .setInputData(workDataOf(BODY to requestBodyString))
        .setConstraints(getWorkerConstraints())
        .build()

    WorkManager.getInstance(context.applicationContext).enqueue(webHookWork)
}

private fun getWorkerConstraints(): Constraints {
    return Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
}