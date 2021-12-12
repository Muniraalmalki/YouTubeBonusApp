package com.example.youtubebonusapp

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var videoList: List<Video>
    private lateinit var gridView:GridView
    private lateinit var player: YouTubePlayer
    private lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkConnectivity()

        videoList = listOf(
            Video("Do You Want to Build a Snowman? (From \"Frozen\"/Sing-Along)", "TeQ_TTyLGMs"),
            Video("FROZEN | Let It Go Sing-along | Official Disney UK", "L0MK7qz13bU"),
            Video("dina Menzel, AURORA - Into the Unknown (From \"Frozen 2\")", "gIOyB9ZXn8s"),
            Video(
                "Kristen Bell, Idina Menzel - For the First Time in Forever (From \"Frozen\"/Sing-Along)",
                "ZrX1XKtShSI"
            ),
            Video("FROZEN | \"In Summer\" Song - Olaf | Official Disney UK", "UFatVn1hP3o")
        )

        youTubePlayerView = findViewById(R.id.youtube_player_view)
        gridView = findViewById(R.id.gridView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo(videoList[0].id, 0f)

                // initialize recyclerView to Video
                gridView.adapter = GridViewAdapter(videoList, player,this@MainActivity)


            }
        })


    }

    //  track device rotation
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
            youTubePlayerView.enterFullScreen()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
            youTubePlayerView.exitFullScreen()
        }
    }
    private fun checkConnectivity(){
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if (activeNetwork?.isConnectedOrConnecting == false){
            Toast.makeText(this,"Check Network Connection", Toast.LENGTH_SHORT).show()
        }
    }
}