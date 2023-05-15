package edu.towson.cosc435.labsapp.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import androidx.work.WorkerParameters
import edu.towson.cosc435vails.fitnessformula.MainActivity
import kotlinx.coroutines.delay
import java.io.File
import java.io.InputStream


class ImageWorker(
    private val ctx: Context,
    params: WorkerParameters
) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        val image_uri = inputData.getString(INPUT_KEY) ?: return Result.failure()
        val song_name = inputData.getString(INPUT_NAME_KEY) ?: return Result.failure()

        val notification = createNotification()
        setForeground(ForegroundInfo(image_uri.hashCode(), notification))
        delay(5 * 1000)
        createDoneNotification()
        return Result.success()
    }

    private fun createNotification(): Notification {
        createNotificationChannel()
        val builder = NotificationCompat.Builder(this.ctx, CHANNEL_ID)
            .setAutoCancel(true)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("Image Download")
            .setContentText("Downloading image...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return builder.build()
    }

    private fun createDoneNotification(): Notification {
        createNotificationChannel()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.type = "image/*"
        val pendingIntent = PendingIntent.getActivity(this.ctx, 1, intent, 0)
        val builder = NotificationCompat.Builder(this.ctx, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("Image Download")
            .setContentText("Download finished")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigPictureStyle())
            .setContentText("Done")
        return builder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Labs App Notification"
            val descriptionText = "Notification channel for LabsApp"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun saveFile(inputStream: InputStream, name: String): Uri? {
        val resolver = this.ctx.contentResolver
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cv = ContentValues()
        cv.put(MediaStore.Images.Media.DISPLAY_NAME, name)
        cv.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
//        cv.put(MediaStore.Images.Media.RELATIVE_PATH)
        val imageURI = resolver.insert(uri, cv)
        if (imageURI != null) {
            val outputStream = resolver.openOutputStream(imageURI)
            if (outputStream != null) {
                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
            }
        }
        return null
    }

    companion object {
        val CHANNEL_ID = "edu.towson.cosc435.labsapp.channel"
        val INPUT_KEY = "IMAGE_URI"
        val INPUT_NAME_KEY = "IMAGE_NAME"
    }
}