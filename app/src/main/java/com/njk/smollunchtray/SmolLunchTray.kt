package com.njk.smollunchtray

import android.app.Application
import com.google.android.material.color.DynamicColors

class SmolLunchTray: Application() {
    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}