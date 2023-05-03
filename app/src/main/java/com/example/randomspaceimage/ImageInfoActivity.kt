package com.example.randomspaceimage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class ImageInfoActivity : AppCompatActivity() {


    var planetImageURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_info)
        getSpaceImageURL()
        Log.d("spaceImageURL", "space image URL set")

    }


    private fun getSpaceImageURL() {
        val client = AsyncHttpClient()

        client["https://api.nasa.gov/planetary/apod?api_key=XOwzd9OokurdcLHrvyZgurbUQKhoBNVjcPksQ6yQ&count=1", object :
            JsonHttpResponseHandler() {
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

}