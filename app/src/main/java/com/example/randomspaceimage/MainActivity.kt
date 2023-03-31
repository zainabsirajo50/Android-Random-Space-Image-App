package com.example.randomspaceimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.randomspaceimage.R
import okhttp3.Headers


class MainActivity : AppCompatActivity() {

    var planetImageURL = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPlanetImageURL()
        Log.d("spaceImageURL", "space image URL set")
        val button = findViewById<Button>(R.id.spaceButton)
        val image = findViewById<ImageView>(R.id.spaceImage)
        getNextImage(button, image)
    }


    private fun getPlanetImageURL() {
        val client = AsyncHttpClient()

        client["https://api.nasa.gov/planetary/apod?api_key=XOwzd9OokurdcLHrvyZgurbUQKhoBNVjcPksQ6yQ&count=1", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Image", "response successful$json")
                var resultsJSON = json.jsonArray.getJSONObject(0)
                planetImageURL = resultsJSON.getString("url")
            }


            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Image Error", errorResponse)
            }
        }]
    }


    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            getPlanetImageURL()

            Glide.with(this)
                .load(planetImageURL)
                .fitCenter()
                .into(imageView)
        }

    }
}