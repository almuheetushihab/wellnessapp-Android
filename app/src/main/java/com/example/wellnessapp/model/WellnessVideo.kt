package com.example.wellnessapp.model

data class WellnessVideo(
    val id: String,
    val title: String,
    val videoUrl: String,
    val category: String,
    // Offsets for the scattered floating masonry effect
    val offsetX: Float,
    val offsetY: Float
)
