package com.example.wellnessapp.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.model.WellnessVideo

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FloatingVideoCard(
    video: WellnessVideo,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Check if we are rendering inside an Android Studio Preview
    val isInspectionMode = LocalInspectionMode.current

    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .width(140.dp)
                .height(220.dp)
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "video_${video.id}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(24.dp),
                    spotColor = Color.Black.copy(alpha = 0.5f),
                    ambientColor = Color.Black.copy(alpha = 0.3f)
                )
                .clip(RoundedCornerShape(24.dp)) // Heavy iOS-like rounded corners
                .background(Color.DarkGray)
                .clickable { onClick() }
        ) {
            if (isInspectionMode) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Video\nThumbnail",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                ExoPlayerView(
                    videoUrl = video.videoUrl,
                    modifier = Modifier.fillMaxSize(),
                    isMuted = true
                )
            }
        }
    }
}
