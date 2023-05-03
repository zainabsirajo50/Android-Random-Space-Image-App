package com.example.randomspaceimage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class SpaceAdapter(private val spaceList: MutableList<Triple<String, String, String>>): RecyclerView.Adapter<SpaceAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val spaceImage: ImageView
        val spaceTitle: TextView
        val spaceDate: TextView


        init {
            // Find our RecyclerView item's ImageView for future use
            spaceImage = view.findViewById(R.id.space_image)
            spaceTitle = view.findViewById(R.id.spaceText1)
            spaceDate = view.findViewById(R.id.spaceText2)



        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.space_item, parent, false)

        return ViewHolder(view)


    }




    override fun getItemCount() = spaceList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.spaceTitle.text = spaceList[position].second
        holder.spaceDate.text = spaceList[position].third


        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Image $position clicked", Toast.LENGTH_SHORT).show()

        }


        Glide.with(holder.itemView)
            .load(spaceList[position].first)
            .centerCrop()
            .into(holder.spaceImage)


    }










}



