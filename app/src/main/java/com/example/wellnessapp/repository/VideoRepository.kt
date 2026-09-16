package com.example.wellnessapp.repository

import com.example.wellnessapp.model.WellnessVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface VideoRepository {
    fun getVideos(): Flow<List<WellnessVideo>>
}

class VideoRepositoryImpl : VideoRepository {
    override fun getVideos(): Flow<List<WellnessVideo>> = flow {
        // Dummy data with varied X/Y offsets for the scattered floating effect across Left, Center, Right
        val dummyVideos = listOf(
            WellnessVideo(
                id = "1",
                title = "Morning Flow",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Yoga",
                offsetX = 16f,   // Left
                offsetY = 30f
            ),
            WellnessVideo(
                id = "2",
                title = "Deep Inner Peace",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Meditation",
                offsetX = 210f,  // Right
                offsetY = 130f
            ),
            WellnessVideo(
                id = "3",
                title = "Forest River Serenity",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Soundscape",
                offsetX = 100f,  // Center
                offsetY = 270f
            ),
            WellnessVideo(
                id = "4",
                title = "Box Breathing Reset",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Breathing",
                offsetX = 24f,   // Left
                offsetY = 410f
            ),
            WellnessVideo(
                id = "5",
                title = "Evening Stretch",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Yoga",
                offsetX = 200f,  // Right
                offsetY = 550f
            ),
            WellnessVideo(
                id = "6",
                title = "Ocean Waves Harmony",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Soundscape",
                offsetX = 105f,  // Center
                offsetY = 690f
            ),
            WellnessVideo(
                id = "7",
                title = "Mindful Breathwork",
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                category = "Breathing",
                offsetX = 20f,   // Left
                offsetY = 830f
            )
        )
        emit(dummyVideos)
    }
}
