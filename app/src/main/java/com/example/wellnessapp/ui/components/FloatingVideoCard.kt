package com.example.wellnessapp.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FloatingVideoCard(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    // Check if we are rendering inside an Android Studio Preview
    val isInspectionMode = LocalInspectionMode.current

    Box(
        modifier = modifier
            .width(140.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(24.dp)) // Heavy iOS-like rounded corners
            .background(Color.DarkGray)
    ) {
        if (isInspectionMode) {
            // Fallback for Android Studio Preview (ExoPlayer will crash/fail in Preview)
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
            // Actual Video Player
            ExoPlayerView(
                videoUrl = videoUrl,
                modifier = Modifier.fillMaxSize(),
                isMuted = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FloatingVideoCardPreview() {
    MaterialTheme {
        FloatingVideoCard(
            videoUrl = "dummy_url",
            modifier = Modifier
        )
    }
}
