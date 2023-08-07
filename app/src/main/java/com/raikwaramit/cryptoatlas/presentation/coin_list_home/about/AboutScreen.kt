package com.raikwaramit.cryptoatlas.presentation.coin_list_home.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ThumbUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raikwaramit.cryptoatlas.R

/**
 * Composable to show about screen which shows the app related details.
 */
@Composable
@Preview
fun AboutScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(10.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.app_icon_image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 3.dp)
                        .clip(RoundedCornerShape(60.dp))
                )
                Text(
                    text = "About CryptoAtlas",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                val context = LocalContext.current
                IconButton(onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/raikwaramit/CryptoAtlas")
                    )
                    context.startActivity(intent)
                }
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.ThumbUp,
                        contentDescription = "Rate us.",
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
            HorizontalDivider(Modifier.padding(top = 10.dp))
            HorizontalDivider(Modifier.padding(top = 2.dp, bottom = 10.dp))
            Text(
                text = "CryptoAtlas is your go-to app for exploring and monitoring " +
                        "cryptocurrencies. Stay updated with real-time data, historical " +
                        "charts, and essential insights for each coin. Create a personalized " +
                        "watchlist, set price alerts, and access curated news to make informed " +
                        "decisions. With an intuitive interface and reliable information," +
                        " CryptoAtlas is your ideal companion in the crypto world.",
                style = MaterialTheme.typography.bodyLarge
            )
            HorizontalDivider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

            Text(text = "Key Features", style = MaterialTheme.typography.titleLarge)

            Column(Modifier.padding(start = 10.dp, end = 10.dp)) {
                val style = MaterialTheme.typography.bodyLarge
                Text(text = "- Extensive Coin Listing", style = style)
                Text(text = "- Real-Time Data", style = style)
                Text(text = "- Detailed Coin Pages", style = style)
                Text(text = "- Favorite Coins", style = style)
                Text(text = "- Favorite Coins", style = style)
                Text(text = "- Price Alerts", style = style)
            }

            HorizontalDivider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
            Text(
                text = "Get Started Today!",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Text(
                text = buildAnnotatedString {
                    append(
                        "Download CryptoAtlas and explore the world of cryptocurrencies with " +
                                "confidence. Monitor prices, track trends, and make informed choices. Have" +
                                " questions? Contact us at "
                    )
                    pushStringAnnotation(tag = "support@cryptoatlas.com", annotation = "aa")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                        )
                    ) {
                        append("support@cryptoatlas.com")
                    }
                    pop()

                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}