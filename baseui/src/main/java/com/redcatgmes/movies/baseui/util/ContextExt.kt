package com.redcatgmes.movies.baseui.util

import android.content.Context
import android.os.Build
import java.util.*

@Suppress("DEPRECATION")
val Context.currentLocale: Locale
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales.get(0)
    } else {
        resources.configuration.locale
    }
