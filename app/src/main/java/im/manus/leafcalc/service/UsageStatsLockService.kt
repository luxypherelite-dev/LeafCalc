package im.manus.leafcalc.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import im.manus.leafcalc.LockScreenActivity
import kotlinx.coroutines.*

class UsageStatsLockService : Service() {
    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())
    private lateinit var usageStatsManager: UsageStatsManager

    override fun onCreate() {
        super.onCreate()
        usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        startForeground(1, createNotification())
        startMonitoring()
    }

    private fun startMonitoring() {
        serviceScope.launch {
            while (isActive) {
                val foregroundApp = getForegroundApp()
                if (isAppProtected(foregroundApp)) {
                    launchLockScreen(foregroundApp)
                }
                delay(300) // The "Debounce" Rule from spec
            }
        }
    }

    private fun getForegroundApp(): String {
        val time = System.currentTimeMillis()
        val stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 10, time)
        return stats?.maxByOrNull { it.lastTimeUsed }?.packageName ?: ""
    }

    private fun isAppProtected(packageName: String): Boolean {
        // In a real app, check the DataStore/Database
        // For MVP, we'll assume a hardcoded list or a simplified check
        return false 
    }

    private fun launchLockScreen(packageName: String) {
        val intent = Intent(this, LockScreenActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra("package_name", packageName)
        }
        startActivity(intent)
    }

    private fun createNotification(): Notification {
        val channelId = "system_ui_engine"
        val channel = NotificationChannel(channelId, "System UI Engine", NotificationManager.IMPORTANCE_LOW)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
        
        return Notification.Builder(this, channelId)
            .setContentTitle("System UI Engine")
            .setContentText("Optimizing system performance")
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}
