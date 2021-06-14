package dev.yunzai.slackwebhook

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

internal class SlackWebHookWorker(
    appContext: Context,
    workerParameters: WorkerParameters
) : Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        val requestBody = inputData.getString(BODY) ?: return Result.failure()
        return when (sendWebHook(requestBody)) {
            RESULT_OK -> Result.success()
            else -> Result.failure()
        }
    }

    private fun sendWebHook(requestBody: String): String? {
        return try {
            val body = Gson().fromJson(requestBody, SlackWebHook::class.java)
            val slackWebHookResult =
                getRetrofit().create(SlackApi::class.java).sendWebHook(PATH, body)
            slackWebHookResult.execute().body()
        } catch (e: Exception) {
            ""
        }
    }

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(WEB_HOOK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    companion object {
        const val WEB_HOOK_BASE_URL = "https://hooks.slack.com/"
        val PATH = BuildConfig.SLACK_WEBHOOK_PATH
        const val BODY = "BODY"
        const val RESULT_OK = "ok"
    }
}