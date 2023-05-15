package edu.towson.cosc435vails.fitnessformula.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import edu.towson.cosc435vails.fitnessformula.MainActivity
import edu.towson.cosc435vails.fitnessformula.R


// Displays the notification that is supposed to trigger when a new workout was created and gives a reminder
class Notification(
    private val context: Context
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification() {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.dumbell)
            .setContentTitle("Your new workout awaits!")
            .setContentText("Nice job remember to give it a name and utilize the progress tracking!")
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification)

    }

    companion object {
        const val CHANNEL_ID = "FIT_FORMULA_CHANNEL"
    }
}