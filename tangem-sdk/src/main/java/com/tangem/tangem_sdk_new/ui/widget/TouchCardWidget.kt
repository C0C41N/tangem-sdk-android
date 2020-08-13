package com.tangem.tangem_sdk_new.ui.widget

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.skyfishjy.library.RippleBackground
import com.tangem.tangem_sdk_new.R
import com.tangem.tangem_sdk_new.SessionViewDelegateState
import com.tangem.tangem_sdk_new.ui.TouchCardAnimation

/**
 * Created by Anton Zhilenkov on 09/08/2020.
 */
class TouchCardWidget(mainView: View) : BaseSessionDelegateStateWidget(mainView) {

    private val rippleBackgroundNfc = mainView.findViewById<RippleBackground>(R.id.rippleBackgroundNfc)
    private val ivHandCardHorizontal = mainView.findViewById<ImageView>(R.id.ivHandCardHorizontal)
    private val ivHandCardVertical = mainView.findViewById<ImageView>(R.id.ivHandCardVertical)
    private val llHand = mainView.findViewById<LinearLayout>(R.id.llHand)
    private val llNfc = mainView.findViewById<LinearLayout>(R.id.llNfc)

    private val nfcDeviceAntenna = TouchCardAnimation(mainView.context, ivHandCardHorizontal, ivHandCardVertical, llHand, llNfc)

    init {
        nfcDeviceAntenna.init()
    }

    override fun setState(params: SessionViewDelegateState) {
        when (params) {
            is SessionViewDelegateState.Ready -> animate()
            is SessionViewDelegateState.TagLost -> animate()
            else -> stopAnimation()
        }
    }

    private fun animate() {
        rippleBackgroundNfc.startRippleAnimation()
        nfcDeviceAntenna.animate()
    }

    private fun stopAnimation() {
        rippleBackgroundNfc.stopRippleAnimation()
        nfcDeviceAntenna.stopAnimation()
    }

    override fun onBottomSheetDismiss() {
        stopAnimation()
    }
}

