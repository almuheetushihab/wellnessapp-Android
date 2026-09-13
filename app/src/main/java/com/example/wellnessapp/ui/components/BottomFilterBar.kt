package com.example.wellnessapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory
            
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50)) // Pill shape
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary 
                        else MaterialTheme.colorScheme.surfaceVariant
                    )
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = category,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary 
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium
                )
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
