package com.tangem.tangem_sdk_new

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.tangem.tangem_sdk_new.nfc.NfcManager

/**
 * [LifecycleObserver] for [NfcManager], helps to coordinate NFC modes with Activity lifecycle.
 */
class NfcLifecycleObserver(private var nfcManager: NfcManager) : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        nfcManager.onStart()
    }

    override fun onStop(owner: LifecycleOwner) {
        nfcManager.onStop()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        nfcManager.onDestroy()
    }
}
