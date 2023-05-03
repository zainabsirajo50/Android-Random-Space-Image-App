package com.example.randomspaceimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers


class MainActivity : AppCompatActivity() {
    private lateinit var spaceList: MutableList<Triple<String, String, String>>
    private lateinit var rvSpace: RecyclerView
    var planetImageURL = ""
    var spaceNameURL = ""
    var spaceDateURL = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPlanetImageURL()


        rvSpace = findViewById(R.id.spaceList)
        spaceList = mutableListOf()




        val adapter = SpaceAdapter(spaceList)
        rvSpace.adapter = adapter
        rvSpace.layoutManager = LinearLayoutManager(this@MainActivity)


        Log.d("spaceImageURL", "space image URL set")





    }



    private fun getPlanetImageURL() {
        val client = AsyncHttpClient()

        client["https://api.nasa.gov/planetary/apod?api_key=XOwzd9OokurdcLHrvyZgurbUQKhoBNVjcPksQ6yQ&count=20", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Image Photo", "response successful$spaceList")


                var spaceImageArray = json.jsonArray.getJSONObject(0)



                for (i in 0 until 20) {
                    spaceImageArray = json.jsonArray.getJSONObject(i)


                    planetImageURL = spaceImageArray.getString("url")

                    spaceNameURL = spaceImageArray.getString("title")

                    spaceDateURL = spaceImageArray.getString("date")

                    spaceList.add(Triple(planetImageURL, spaceNameURL, spaceDateURL))
                }




                val adapter = SpaceAdapter(spaceList)
                rvSpace.adapter = adapter
                rvSpace.layoutManager = LinearLayoutManager(this@MainActivity)



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