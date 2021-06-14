package dev.yunzai.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dev.yunzai.slackwebhook.SlackWebHook
import dev.yunzai.slackwebhook.send

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        findViewById<Button>(R.id.send_button).setOnClickListener {
            SlackWebHook.builder()
                .pretext("hello")
                .title("this is webhook")
                .text("test")
                .build()
                .send(this)
        }
    }
}