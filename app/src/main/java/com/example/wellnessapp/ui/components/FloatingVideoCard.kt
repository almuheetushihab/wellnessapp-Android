package com.example.wellnessapp.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.model.WellnessVideo
import kotlin.math.abs

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

    // Deterministic random rotation between -4f and 4f degrees for scattered look
    val rotationDegrees = remember(video.id) {
        val hash = abs(video.id.hashCode())
        val rawDegree = ((hash % 81) / 10f) - 4.0f // -4.0f to 4.0f
        if (rawDegree == 0f) 2.5f else rawDegree
    }

    val cardShape = RoundedCornerShape(32.dp)

    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .width(155.dp)
                .height(240.dp)
                .graphicsLayer {
                    rotationZ = rotationDegrees
                }
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "video_${video.id}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
                // Diffused soft colored drop shadow with violet/cyan glowing tint
                .shadow(
                    elevation = 20.dp,
                    shape = cardShape,
                    spotColor = Color(0x387C4DFF), // Soft violet glow
                    ambientColor = Color(0x2800B0FF) // Soft cyan ambient glow
                )
                .clip(cardShape)
                .background(Color.White.copy(alpha = 0.25f)) // Glass background base
                // Solid 1.dp White border for glass-edge effect
                .border(
                    border = BorderStroke(1.dp, Color.White),
                    shape = cardShape
                )
                .clickable { onClick() }
        ) {
            if (isInspectionMode) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF818CF8), Color(0xFFC084FC))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Video\nThumbnail",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                ExoPlayerView(
                    videoUrl = video.videoUrl,
                    modifier = Modifier.fillMaxSize(),
                    isMuted = true
                )
            }

            // Glassmorphism bottom gradient overlay with video info tag
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            )
                        )
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Column {
                    Text(
                        text = video.category.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.85f),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = video.title,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
