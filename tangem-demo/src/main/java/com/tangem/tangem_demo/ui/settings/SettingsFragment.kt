package com.tangem.tangem_demo.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat


/**
 * Created by Anton Zhilenkov on 24/03/2021.
 */
class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val context = preferenceManager.context
        val screen = preferenceManager.createPreferenceScreen(context)

        val notificationPreference = SwitchPreferenceCompat(context)
        notificationPreference.key = nightMode
        notificationPreference.title = "Enable night mode"

        screen.addPreference(notificationPreference)

        preferenceScreen = screen
    }

    companion object {
        val nightMode = "nightMode"
    }
}