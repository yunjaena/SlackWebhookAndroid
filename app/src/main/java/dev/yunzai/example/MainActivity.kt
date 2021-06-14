package dev.yunzai.example

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dev.yunzai.slackwebhook.SlackWebHook

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        findViewById<Button>(R.id.send_button).setOnClickListener {
            SlackWebHook.builder()
                .pretext("hello")
                .title("this is webhook")
                .text("test")
                .color("#FF0000")
                .timeStampEnabled(true)
                .fields("name" to "jason", "github" to "https://github.com/yunjaena")
                .build()
                .send(this)
        }
    }
}
