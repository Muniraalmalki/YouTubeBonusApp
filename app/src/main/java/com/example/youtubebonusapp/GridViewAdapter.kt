package com.example.youtubebonusapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class GridViewAdapter(private val videoList:List<Video>,private  val player: YouTubePlayer,private val context: Context):BaseAdapter() {
    var layoutInflater: LayoutInflater? = null
    override fun getCount(): Int {
        return videoList.size
    }

    override fun getItem(position: Int): Any {
        return videoList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if(convertView == null){
            convertView = layoutInflater!!.inflate(R.layout.video_item,
            parent,
            false)
        }
        if (convertView != null) {
            val button = convertView!!.findViewById<Button>(R.id.videoButton)
            button.text = videoList[position].title
            button.setOnClickListener {
                player.loadVideo(videoList[position].id, 0f)
            }
        }
           return convertView
    }
}
