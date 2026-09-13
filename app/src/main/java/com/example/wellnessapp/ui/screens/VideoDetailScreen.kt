package com.example.wellnessapp.ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.model.WellnessVideo
import com.example.wellnessapp.ui.components.ExoPlayerView

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun VideoDetailScreen(
    video: WellnessVideo,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBackClick: () -> Unit
) {
    with(sharedTransitionScope) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            // Full screen video player sharing the exact same key as the floating card
            ExoPlayerView(
                videoUrl = video.videoUrl,
                modifier = Modifier
                    .fillMaxSize()
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "video_${video.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                isMuted = false // Play sound in full screen!
            )

            // Back Button Overlay
            Box(
                modifier = Modifier
                    .padding(top = 48.dp, start = 16.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "X",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
