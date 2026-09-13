package com.example.wellnessapp.ui.components

@Composable
fun FloatingMasonryView(videos: List<WellnessVideo>) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        videos.forEach { video ->
            FloatingVideoCard(
                video = video,
                modifier = Modifier
                    .graphicsLayer {
                        // Protiti video tar data model theke X, Y position nebe
                        translationX = video.offsetX
                        translationY = video.offsetY
                        // Scroll er shomoy scale ba alpha change kore depth effect anben
                    }
                    .clip(RoundedCornerShape(24.dp))
                    .clickable { /* Navigate to detail */ }
            )
        }
    }
}