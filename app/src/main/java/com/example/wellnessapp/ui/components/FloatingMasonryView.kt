package com.example.wellnessapp.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.model.WellnessVideo

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FloatingMasonryView(
    videos: List<WellnessVideo>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onVideoClick: (WellnessVideo) -> Unit,
    modifier: Modifier = Modifier
) {
    // Adding verticalScroll so the user can scroll through the scattered items
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E1E2C), // Deep blue/purple-ish dark
                        Color(0xFF121212)  // Dark grey/black
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        videos.forEach { video ->
            FloatingVideoCard(
                video = video,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                onClick = { onVideoClick(video) },
                modifier = Modifier
                    .graphicsLayer {
                        translationX = video.offsetX.dp.toPx()
                        translationY = video.offsetY.dp.toPx()
                    }
            )
        }
    }
}
