package com.raikwaramit.cryptoatlas.presentation.coin_list_home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

/**
 * Composable to show the filter chip bar holding the two filter chip
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipBar(selectedIndex: Int, onIndexUpdate: (FilterChips) -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
        FilterChips.values().forEach { chip ->
            val selectedIndexState by
            rememberSaveable(selectedIndex) { mutableStateOf(selectedIndex == chip.ordinal) }
            FilterChip(
                selected = selectedIndexState, onClick = { onIndexUpdate(chip) },
                label = {
                    Text(text = chip.text, style = MaterialTheme.typography.button)
                },
                colors = FilterChipDefaults.elevatedFilterChipColors(),
            )
        }
    }
}

enum class FilterChips(val text: String) {
    RANK("Rank"),
    INACTIVE("InActive")
}