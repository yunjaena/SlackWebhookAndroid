package dev.yunzai.slackwebhook

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

internal interface SlackApi {
    @POST
    fun sendWebHook(@Url url: String, @Body slackWebHook: SlackWebHook): Call<String>
}