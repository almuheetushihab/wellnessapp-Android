package com.example.wellnessapp.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
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
    // Stunning, bright pastel gradient background
    val pastelGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFF0F3), // Soft Peach
            Color(0xFFFFE5EC), // Blushing Peach
            Color(0xFFF3E8FF), // Light Lavender
            Color(0xFFE8DEF8), // Soft Violet
            Color(0xFFE0F2FE), // Pale Cyan
            Color(0xFFE0F4FF)  // Sky Cyan
        ),
        start = Offset(0f, 0f),
        end = Offset(1200f, 1800f)
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(pastelGradient)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        val cardWidth = 155.dp
        val cardHeight = 240.dp
        val horizontalPadding = 16.dp

        // Ambient radial pastel glowing blobs in the background
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Soft Peach glow top-right
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFD1DC).copy(alpha = 0.6f), Color.Transparent),
                    center = Offset(size.width * 0.85f, size.height * 0.15f),
                    radius = size.width * 0.6f
                )
            )
            // Soft Lavender glow mid-left
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFE2D4F9).copy(alpha = 0.5f), Color.Transparent),
                    center = Offset(size.width * 0.15f, size.height * 0.45f),
                    radius = size.width * 0.7f
                )
            )
            // Soft Cyan glow bottom-right
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFBEE3F8).copy(alpha = 0.55f), Color.Transparent),
                    center = Offset(size.width * 0.8f, size.height * 0.75f),
                    radius = size.width * 0.65f
                )
            )
        }

        var maxContentY = 0.dp

        videos.forEachIndexed { index, video ->
            // Calculate dynamic horizontal scatter across FULL width (Left, Center, Right)
            val computedX: Dp = when {
                video.offsetX <= 50f -> horizontalPadding + (video.offsetX % 16).dp
                (video.offsetX in 51f..150f) -> {
                    val centerX = (maxWidth - cardWidth) / 2
                    centerX + ((video.offsetX - 100f) * 0.35f).dp
                }
                video.offsetX > 150f -> {
                    val rightX = maxWidth - cardWidth - horizontalPadding
                    (rightX - ((video.offsetX - 200f) * 0.25f).dp).coerceAtLeast(horizontalPadding)
                }
                else -> {
                    val slot = index % 3
                    when (slot) {
                        0 -> horizontalPadding + ((index * 8) % 16).dp
                        1 -> (maxWidth - cardWidth - horizontalPadding) - ((index * 6) % 16).dp
                        else -> ((maxWidth - cardWidth) / 2) + (if (index % 2 == 0) 10.dp else (-10).dp)
                    }
                }
            }

            // Calculate vertical Y scatter
            val computedY: Dp = if (video.offsetY > 0f) {
                video.offsetY.dp
            } else {
                (index * 145).dp + 20.dp
            }

            if (computedY > maxContentY) {
                maxContentY = computedY
            }

            FloatingVideoCard(
                video = video,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                onClick = { onVideoClick(video) },
                modifier = Modifier.offset(x = computedX, y = computedY)
            )
        }

        // Invisible spacer at the bottom so verticalScroll accommodates all scattered cards plus bottom bar floating space
        Spacer(
            modifier = Modifier
                .height(maxContentY + cardHeight + 140.dp)
        )
    }
}
