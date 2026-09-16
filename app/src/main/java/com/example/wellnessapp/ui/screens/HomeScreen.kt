package com.example.wellnessapp.ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        FloatingMasonryView(
            videos = videos,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            onVideoClick = onVideoClick,
            modifier = Modifier.fillMaxSize()
        )

        BottomFilterBar(
            categories = viewModel.categories,
            selectedCategory = selectedCategory,
            onCategorySelected = viewModel::onCategorySelected,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 20.dp, start = 16.dp, end = 16.dp)
        )
    }
}
