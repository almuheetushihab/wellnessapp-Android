package com.example.wellnessapp.domain.usecase

import com.example.wellnessapp.model.WellnessVideo
import com.example.wellnessapp.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFilteredVideosUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    operator fun invoke(category: String? = null): Flow<List<WellnessVideo>> {
        return repository.getVideos().map { videos ->
            if (category.isNullOrEmpty() || category == "All") {
                videos
            } else {
                videos.filter { it.category == category }
            }
        }
    }
}
