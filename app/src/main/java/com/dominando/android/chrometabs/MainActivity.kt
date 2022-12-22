package com.dominando.android.chrometabs

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ActivityCompat
import com.dominando.android.chrometabs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTab.setOnClickListener { openTab() }
    }

    private fun openTab() {
        CustomTabsIntent.Builder().apply {
            setToolbarColor(
                ActivityCompat.getColor(
                    this@MainActivity,
                    R.color.purple_200
                )
            )

            val icon = BitmapFactory.decodeResource(resources, android.R.drawable.ic_menu_agenda)

            setActionButton(icon, "Ação", pendingIntent(), true)
            addMenuItem("Voltar para o app nativo", pendingIntent())

            setStartAnimations(
                this@MainActivity,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )

            setExitAnimations(
                this@MainActivity,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

            val url = "http://www.nglauber.com.br"
            build().launchUrl(this@MainActivity, Uri.parse(url))
        }
    }

    private fun pendingIntent() = PendingIntent.getActivity(
        this@MainActivity, 0,
        Intent(this@MainActivity, MainActivity::class.java), 0
    )
}