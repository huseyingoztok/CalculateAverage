package com.example.husgo.ortalamahesaplama

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.splash_layout.*

class SplashActivity : AppCompatActivity() {
    var context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        var yukaricikanbuton = AnimationUtils.loadAnimation(context, R.anim.yukaricikanbuton)
        var asagiinenbalon = AnimationUtils.loadAnimation(context, R.anim.asagiinenbalon)
        var yukaricikanbalon = AnimationUtils.loadAnimation(context, R.anim.yukaricikanbalon)
        var asagiinenbuton = AnimationUtils.loadAnimation(context, R.anim.asagiinenbuton)
        btnAnim.animation = yukaricikanbuton
        imgAnim.animation = asagiinenbalon

        btnAnim.setOnClickListener {
            imgAnim.startAnimation(yukaricikanbalon)
            btnAnim.startAnimation(asagiinenbuton)


            object : CountDownTimer(1000, 100) {
                override fun onFinish() {
                    //var intent=Intent(this@SplashActivity,MainActivity::class.java)
                    /*
                    * javada ilk parametre activityadi.this kotlinde this@ActivityAdi
                    */
                    var intent=Intent(context,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onTick(p0: Long) {
                    //100 ms te 1 çalışacak kod bloğu
                }

            }.start()


        }

    }
}
