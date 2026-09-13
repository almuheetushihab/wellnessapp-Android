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
                offsetX = 20f,
                offsetY = 40f
            ),
            WellnessVideo(
                id = "2",
                title = "Deep Meditation",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Meditation",
                offsetX = 200f,
                offsetY = 180f
            ),
            WellnessVideo(
                id = "3",
                title = "Relaxing Sounds",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Soundscape",
                offsetX = 100f,
                offsetY = 360f
            ),
            WellnessVideo(
                id = "4",
                title = "Breathwork Basics",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Breathing",
                offsetX = 30f,
                offsetY = 540f
            ),
            WellnessVideo(
                id = "5",
                title = "Stretching Routine",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Yoga",
                offsetX = 180f,
                offsetY = 720f
            )
        )
        emit(dummyVideos)
    }
}
