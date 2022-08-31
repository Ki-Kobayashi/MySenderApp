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
        // 🌟 定期実行で非同期処理をするなら「ScheduledExecutorService# scheduleAtFixedRate」
            // 指定された時間間隔で、Runnable# runメソッド（ラムダ式）で実行
        schedule = Executors.newSingleThreadScheduledExecutor()
        // 🌟🌟　3秒置きにブロードキャストで、現在時刻を送信する　🌟🌟
        schedule.scheduleAtFixedRate(
            {
                // アクション名を指定して、一致したレシーバーに受信させる
                val i = Intent("SimpleService_BC_ACTION").apply {
                    putExtra("dataName", Date().toString())
                }
                sendBroadcast(i)
            }, // ここで時間の掛かる処理をやる
            0, // 実行までの遅延時間
            3000, // 実行間隔（システムに負荷を掛けないように、なるべく間隔はあける）
            TimeUnit.MILLISECONDS // 時間単位
        )
        return START_STICKY // システムから強制終了されたときに、サービスを再起動する
    }

    /**
     * サービスをActivityとバインドするときに利用
     *  ※Activityが終了すると、サービスも終了
     */
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}