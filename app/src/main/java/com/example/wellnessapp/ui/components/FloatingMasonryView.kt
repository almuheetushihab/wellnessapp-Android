package com.example.wellnessapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.model.WellnessVideo

@Composable
fun FloatingMasonryView(
    videos: List<WellnessVideo>,
    modifier: Modifier = Modifier
) {
    // Adding verticalScroll so the user can scroll through the scattered items
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        videos.forEach { video ->
            FloatingVideoCard(
                videoUrl = video.videoUrl,
                modifier = Modifier
                    .graphicsLayer {
                        translationX = video.offsetX
                        translationY = video.offsetY
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FloatingMasonryViewPreview() {
    val dummyVideos = listOf(
        WellnessVideo(
            id = "1",
            title = "Morning Yoga",
            videoUrl = "",
            category = "Yoga",
            offsetX = 50f,
            offsetY = 50f
        ),
        WellnessVideo(
            id = "2",
            title = "Deep Meditation",
            videoUrl = "",
            category = "Meditation",
            offsetX = 300f,
            offsetY = 250f
        ),
        WellnessVideo(
            id = "3",
            title = "Relaxing Sounds",
            videoUrl = "",
            category = "Soundscape",
            offsetX = 100f,
            offsetY = 550f
        )
    )
    
    MaterialTheme {
        FloatingMasonryView(
            videos = dummyVideos,
            modifier = Modifier.fillMaxSize()
        )
    }
}
