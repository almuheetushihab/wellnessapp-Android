package com.example.wellnessapp.repository

import com.example.wellnessapp.model.WellnessVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface VideoRepository {
    fun getVideos(): Flow<List<WellnessVideo>>
}

class VideoRepositoryImpl : VideoRepository {
    override fun getVideos(): Flow<List<WellnessVideo>> = flow {
        // Dummy data with varied X/Y offsets for the floating effect
        // Real app would fetch this from a local DB or network API
        val dummyVideos = listOf(
            WellnessVideo(
                id = "1",
                title = "Morning Yoga",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Yoga",
                offsetX = 50f,
                offsetY = 50f
            ),
            WellnessVideo(
                id = "2",
                title = "Deep Meditation",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Meditation",
                offsetX = -80f,
                offsetY = 250f
            ),
            WellnessVideo(
                id = "3",
                title = "Relaxing Sounds",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Soundscape",
                offsetX = 100f,
                offsetY = 450f
            ),
            WellnessVideo(
                id = "4",
                title = "Breathwork Basics",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Breathing",
                offsetX = -40f,
                offsetY = 650f
            ),
            WellnessVideo(
                id = "5",
                title = "Stretching Routine",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Yoga",
                offsetX = 70f,
                offsetY = 850f
            )
        )
        emit(dummyVideos)
    }
}
