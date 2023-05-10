package edu.towson.cosc435vails.fitnessformula.network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request

interface IExerciseFetcher {
    suspend fun fetchExercises(): List<Exercise>

    suspend fun fetchImage(url: String): Bitmap?

    suspend fun fetchLibraryExercises(): List<LibraryExercise>

}

class ExerciseFetcher(private val ctx: Context) : IExerciseFetcher {

    private val URL = "https://my-json-server.typicode.com/arosscoe/fitness-formula_api/exercises"
    private val client = OkHttpClient()

    override suspend fun fetchExercises(): List<Exercise> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url(URL)
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body
            if (responseBody != null) {
                val jsonString = responseBody.string()
                val gson = Gson()
                val exercisesArray = gson.fromJson(jsonString, Array<Exercise>::class.java)
                exercisesArray.toList()
            } else {
                listOf()
            }
        }
    }

    override suspend fun fetchLibraryExercises(): List<LibraryExercise> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url(URL)
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body
            if (responseBody != null) {
                val jsonString = responseBody.string()
                val gson = Gson()
                val exercisesArray = gson.fromJson(jsonString, Array<LibraryExercise>::class.java)
                exercisesArray.toList()
            } else {
                listOf()
            }
        }
    }

    override suspend fun fetchImage(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .get()
                .url(url)
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body
            if(responseBody != null) {
                val bytes = responseBody.byteStream()
                BitmapFactory.decodeStream(bytes)
            } else {
                null
            }
        }
    }

}