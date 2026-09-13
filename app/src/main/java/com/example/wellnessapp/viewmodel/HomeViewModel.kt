package com.example.wellnessapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessapp.domain.usecase.GetFilteredVideosUseCase
import com.example.wellnessapp.model.WellnessVideo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFilteredVideosUseCase: GetFilteredVideosUseCase
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory

    val categories = listOf("All", "Yoga", "Meditation", "Soundscape", "Breathing")

    @OptIn(ExperimentalCoroutinesApi::class)
    val videos: StateFlow<List<WellnessVideo>> = _selectedCategory
        .flatMapLatest { category ->
            getFilteredVideosUseCase(category)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }
}
