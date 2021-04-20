package com.tangem.tangem_sdk_jvm

import SmartCardReader
import com.tangem.CardReader
import com.tangem.Log
import javax.smartcardio.TerminalFactory

class ReaderFactory {

    fun create(indexOfTerminal: Int? = null): CardReader? {
        val factory = TerminalFactory.getDefault()
        val terminals = factory.terminals().list()
        if (terminals.isNullOrEmpty()) {
            return null
        }
        val index = indexOfTerminal ?: 0
        val terminal = terminals.getOrNull(index) ?: return null

        Log.nfc { "Using reader #${terminal.name}}" }

        val reader = SmartCardReader(terminal)
        reader.logException = true
        return reader
    }
}
