package com.raikwaramit.cryptoatlas.presentation.extension

import android.content.Context
import android.content.Intent
import com.raikwaramit.cryptoatlas.presentation.MainActivity

/**
 * Extension method of the Context to launch share intent.
 */
fun Context.launchShareIntent() {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "Please like and share Crypto Atlas at https://github.com/raikwaramit/CryptoAtlas."
        )
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    (this as MainActivity).startActivity(shareIntent)
}