package com.example.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.wellnessapp.model.WellnessVideo
import com.example.wellnessapp.ui.screens.HomeScreen
import com.example.wellnessapp.ui.screens.VideoDetailScreen
import com.example.wellnessapp.ui.theme.WellnessAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WellnessAppTheme {
                WellnessApp()
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun WellnessApp() {
    var selectedVideo by remember { mutableStateOf<WellnessVideo?>(null) }

    SharedTransitionLayout {
        AnimatedContent(
            targetState = selectedVideo,
            label = "main_transition"
        ) { targetVideo ->
            if (targetVideo == null) {
                HomeScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent,
                    onVideoClick = { video -> selectedVideo = video }
                )
            } else {
                VideoDetailScreen(
                    video = targetVideo,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent,
                    onBackClick = { selectedVideo = null }
                )
            }
        }
    }
}
