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
   //     getSpaceNameURL()
    //  getSpaceDateURL()

        rvSpace = findViewById(R.id.spaceList)
        spaceList = mutableListOf()


  /*      val button = findViewById<Button>(R.id.spaceButton)
        val image = findViewById<ImageView>(R.id.spaceImage)
        val text1 = findViewById<TextView>(R.id.spaceText1)
        val text2 = findViewById<TextView>(R.id.spaceText2)*/





        val adapter = SpaceAdapter(spaceList)
        rvSpace.adapter = adapter
        rvSpace.layoutManager = LinearLayoutManager(this@MainActivity)


        Log.d("spaceImageURL", "space image URL set")


       // getNextImage(button, image, text1, text2)


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

   /*private fun getSpaceNameURL() {
        val client = AsyncHttpClient()

        client["https://api.nasa.gov/planetary/apod?api_key=XOwzd9OokurdcLHrvyZgurbUQKhoBNVjcPksQ6yQ&count=20", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {


                Log.d("Image Name", "response successful$spaceList")

                var spaceTitleArray = json.jsonArray.getJSONObject(0)



                for (i in 0 until spaceTitleArray.length()) {
                    spaceTitleArray = json.jsonArray.getJSONObject(i)
                    spaceNameURL = spaceTitleArray.getString("title")

                    spaceList.add(spaceNameURL)


                }

                var spaceDateArray = json.jsonArray.getJSONObject(0)



                for (i in 0 until spaceDateArray.length()) {
                    spaceDateArray = json.jsonArray.getJSONObject(i)
                    spaceDateURL = spaceDateArray.getString("date")

                    spaceList.add(spaceDateURL)
                }
                Log.d("Image Date", "response successful$spaceList")

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
                Log.d("Image Name Error", errorResponse)
            }
        }]



    }


   private fun getSpaceDateURL() {
        val client = AsyncHttpClient()

        client["https://api.nasa.gov/planetary/apod?api_key=XOwzd9OokurdcLHrvyZgurbUQKhoBNVjcPksQ6yQ&count=1", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Image Description", "response successful$spaceList")


                var spaceDateArray = json.jsonArray.getJSONObject(0)



                for (i in 0 until spaceDateArray.length()) {
                    spaceDateArray = json.jsonArray.getJSONObject(i)
                    spaceDateURL = spaceDateArray.getString("date")

                    spaceList.add(spaceDateURL)
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
                Log.d("Image Date Error", errorResponse)
            }
        }]
    }
*/

  /*  private fun getNextImage(button: Button, imageView: ImageView, spaceName: TextView, spaceDescription: TextView) {
        button.setOnClickListener {
            getPlanetImageURL()
            getSpaceNameURL()
            getSpaceDateURL()

            spaceName.setText(spaceNameURL)
            spaceDescription.setText(spaceDateURL)

            Glide.with(this)
                .load(planetImageURL)
                .fitCenter()
                .into(imageView)
        }

    }*/
}