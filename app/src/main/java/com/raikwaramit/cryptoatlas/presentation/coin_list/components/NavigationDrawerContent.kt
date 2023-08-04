package com.raikwaramit.cryptoatlas.presentation.coin_list.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raikwaramit.cryptoatlas.R

@Composable
fun NavigationDrawerContent() {
    ModalDrawerSheet(
        drawerTonalElevation = LocalAbsoluteTonalElevation.current
    ) {
        val modifier = Modifier.padding(5.dp)

        OutlinedCard(
            colors = CardDefaults.outlinedCardColors(),
            modifier = modifier
                .fillMaxWidth()
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coin))
            LottieAnimation(
                composition = composition,
                iterations = Int.MAX_VALUE,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
            )

            Text(
                text = "Crypto Atlas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
        }

        HorizontalDivider(modifier)
        NavigationDrawerItem(
            icon = { Icon(imageVector = Icons.TwoTone.Info, contentDescription = "") },
            label = { Text(text = "About app") },
            selected = false,
            modifier = modifier,
            onClick = {})

        NavigationDrawerItem(
            icon = { Icon(imageVector = Icons.TwoTone.Share, contentDescription = "") },
            label = { Text(text = "Share app") },
            selected = false,
            modifier = modifier,
            onClick = {})

        NavigationDrawerItem(
            icon = { Icon(imageVector = Icons.TwoTone.Email, contentDescription = "") },
            label = { Text(text = "Contact us") },
            selected = false,
            modifier = modifier,
            onClick = {})

        HorizontalDivider(modifier)
        NavigationDrawerItem(
            icon = { Icon(imageVector = Icons.TwoTone.ExitToApp, contentDescription = "") },
            label = { Text(text = "Exit") },
            selected = false,
            modifier = modifier,
            onClick = {})
    }
}