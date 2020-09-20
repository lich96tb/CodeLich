package com.example.youtubeplay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_custom_youtube.*

class CustomYoutube : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_youtube)
        lifecycle.addObserver(youTubePlayerView);
        val customPlayerUi=youTubePlayerView.inflateCustomPlayerUi(R.layout.custom_player_ui)


        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val customPlayerUiController = CustomPlayerUiController(
                    this@CustomYoutube,
                    customPlayerUi,
                    youTubePlayer,
                    youTubePlayerView
                )
                youTubePlayer.addListener(customPlayerUiController)
                youTubePlayerView.addFullScreenListener(customPlayerUiController)

//                YouTubePlayerUtils.loadOrCueVideo(
//                    youTubePlayer, lifecycle,
//                    VideoIdsProvider.getNextVideoId(), 0f
//                )
            }
        })
    }
}