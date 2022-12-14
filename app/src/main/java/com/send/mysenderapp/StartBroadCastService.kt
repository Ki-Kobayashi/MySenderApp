package com.send.mysenderapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class StartBroadCastService : Service() {
    companion object {
        private const val TAG = "StartReceiverService"
    }

    private lateinit var schedule: ScheduledExecutorService

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "---------onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // ð å®æå®è¡ã§éåæå¦çããããªããScheduledExecutorService# scheduleAtFixedRateã
            // æå®ãããæéééã§ãRunnable# runã¡ã½ããï¼ã©ã ãå¼ï¼ã§å®è¡
        schedule = Executors.newSingleThreadScheduledExecutor()
        // ððã3ç§ç½®ãã«ãã­ã¼ãã­ã£ã¹ãã§ãç¾å¨æå»ãéä¿¡ãããðð
        schedule.scheduleAtFixedRate(
            {
                // ã¢ã¯ã·ã§ã³åãæå®ãã¦ãä¸è´ããã¬ã·ã¼ãã¼ã«åä¿¡ããã
                val i = Intent("SimpleService_BC_ACTION").apply {
                    putExtra("dataName", Date().toString())
                }
                sendBroadcast(i)
            }, // ããã§æéã®æããå¦çããã
            0, // å®è¡ã¾ã§ã®éå»¶æé
            3000, // å®è¡ééï¼ã·ã¹ãã ã«è² è·ãæããªãããã«ããªãã¹ãééã¯ãããï¼
            TimeUnit.MILLISECONDS // æéåä½
        )
        return START_STICKY // ã·ã¹ãã ããå¼·å¶çµäºãããã¨ãã«ããµã¼ãã¹ãåèµ·åãã
    }

    /**
     * ãµã¼ãã¹ãActivityã¨ãã¤ã³ãããã¨ãã«å©ç¨
     *  â»Activityãçµäºããã¨ããµã¼ãã¹ãçµäº
     */
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}