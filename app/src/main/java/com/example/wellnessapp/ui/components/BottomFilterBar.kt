package com.example.wellnessapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomFilterBar(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 16.dp,
                shape = CircleShape,
                spotColor = Color(0x204F46E5),
                ambientColor = Color(0x10000000)
            )
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.6f))
            .border(
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.9f)),
                shape = CircleShape
            )
            .padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(categories) { category ->
                val isSelected = category == selectedCategory
                val pillShape = CircleShape

                Box(
                    modifier = Modifier
                        .then(
                            if (isSelected) {
                                Modifier.shadow(
                                    elevation = 8.dp,
                                    shape = pillShape,
                                    spotColor = Color(0x664F46E5)
                                )
                            } else Modifier
                        )
                        .clip(pillShape)
                        .background(
                            if (isSelected) {
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF6366F1), // Soft Violet/Indigo
                                        Color(0xFF4F46E5)  // Deep Indigo
                                    )
                                )
                            } else {
                                SolidColor(Color.White.copy(alpha = 0.35f))
                            }
                        )
                        .clickable { onCategorySelected(category) }
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = category,
                        color = if (isSelected) Color.White else Color(0xFF374151),
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomFilterBarPreview() {
    MaterialTheme {
        BottomFilterBar(
            categories = listOf("All", "Yoga", "Meditation", "Soundscape", "Breathing"),
            selectedCategory = "Yoga",
            onCategorySelected = {}
        )
    }
}
