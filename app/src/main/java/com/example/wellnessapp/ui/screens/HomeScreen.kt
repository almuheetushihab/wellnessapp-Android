package com.example.wellnessapp.ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wellnessapp.model.WellnessVideo
import com.example.wellnessapp.ui.components.BottomFilterBar
import com.example.wellnessapp.ui.components.FloatingMasonryView
import com.example.wellnessapp.viewmodel.HomeViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onVideoClick: (WellnessVideo) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val videos by viewModel.videos.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Scaffold(
        bottomBar = {
            BottomFilterBar(
                categories = viewModel.categories,
                selectedCategory = selectedCategory,
                onCategorySelected = viewModel::onCategorySelected
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FloatingMasonryView(
                videos = videos,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                onVideoClick = onVideoClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
