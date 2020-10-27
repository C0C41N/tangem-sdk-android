package com.tangem.tangem_sdk_new.ui.widget.howTo

import android.content.Context
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextSwitcher
import com.skyfishjy.library.RippleBackground
import com.tangem.tangem_sdk_new.R
import com.tangem.tangem_sdk_new.SessionViewDelegateState
import com.tangem.tangem_sdk_new.extensions.*
import com.tangem.tangem_sdk_new.nfc.NfcLocationProvider
import com.tangem.tangem_sdk_new.nfc.NfcManager
import com.tangem.tangem_sdk_new.ui.animation.VoidCallback
import com.tangem.tangem_sdk_new.ui.widget.BaseSessionDelegateStateWidget
import com.tangem.tangem_sdk_new.ui.widget.BaseStateWidget
import com.tangem.tangem_sdk_new.ui.widget.progressBar.ProgressbarStateWidget

/**
 * Created by Anton Zhilenkov on 11/10/2020.
 */
class HowToTapWidget constructor(
    mainView: View,
    private val nfcManager: NfcManager,
    private val nfcLocationProvider: NfcLocationProvider,
) : BaseSessionDelegateStateWidget(mainView) {

    var onCloseListener: VoidCallback? = null
        set(value) {
            field = value
            controller?.onClose = value
        }
    var previousState: SessionViewDelegateState? = null

    private val vibrator = mainView.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    private val nfcLocation = nfcLocationProvider.getLocation()

    private var initialMode: HowToMode = if (nfcLocation == null) HowToMode.UNKNOWN else HowToMode.KNOWN
    private var currentMode: HowToMode = initialMode
    private var controller: HowToController? = null

    private val viewContainer = mainView as ViewGroup

    override fun setState(params: SessionViewDelegateState) {
        when (params) {
            SessionViewDelegateState.HowToTap -> {
                controller = HowToController(createWidget(currentMode), vibrator, nfcManager)
                controller?.onClose = onCloseListener
                viewContainer.removeAllViews()
                viewContainer.addView(controller?.getView())
                controller?.start()
            }
            else -> {
                currentMode = initialMode
                controller?.stop()
                viewContainer.removeAllViews()
            }
        }
    }

    private fun createWidget(mode: HowToMode): NfcHowToWidget {
        val layoutInflater = LayoutInflater.from(mainView.context)
        return when (mode) {
            HowToMode.KNOWN -> {
                val view = layoutInflater.inflate(R.layout.how_to_known, null)
                NfcKnownWidget(view, nfcLocation!!, onSwitch = {
                    currentMode = HowToMode.UNKNOWN
                    setState(SessionViewDelegateState.HowToTap)
                })
            }
            HowToMode.UNKNOWN -> {
                NfcUnknownWidget(layoutInflater.inflate(R.layout.how_to_unknown, null))
            }
        }
    }
}


abstract class NfcHowToWidget(mainView: View) : BaseStateWidget<HowToState>(mainView) {

    var onClose: VoidCallback? = null
    var onAnimationEnd: VoidCallback? = null

    val view: View
        get() = mainView

    protected val FLIP_DURATION = 650L
    protected val FADE_DURATION = 400L
    protected val FADE_DURATION_HALF = FADE_DURATION / 2

    protected val context = mainView.context
    protected val rippleView: RippleBackground = mainView.findViewById(R.id.rippleBg)
    protected val tvSwitcher: TextSwitcher = mainView.findViewById(R.id.tvHowToSwitcher)
    protected val phone: ImageView = mainView.findViewById(R.id.imvPhone)
    protected val btnCancel: Button = mainView.findViewById(R.id.btnCancel)
    protected val btnShowAgain: Button = mainView.findViewById(R.id.btnShowAgain)
    protected val progressStateWidget: ProgressbarStateWidget by lazy {
        val view = mainView.findViewById<View>(R.id.clProgress)
        view.elevation = view.dpToPx(5f)
        ProgressbarStateWidget(view)
    }

    protected var currentState: HowToState? = null
    protected var isCancelled = false

    init {
        initTextChangesAnimation()
        btnShowAgain.hideWithFade(0)
        btnShowAgain.setOnClickListener {
            setState(HowToState.Init)
            setState(HowToState.Animate)
        }
        btnCancel.setOnClickListener { onClose?.invoke() }
        progressStateWidget.setState(SessionViewDelegateState.None)
    }

    override fun onBottomSheetDismiss() {
        setState(HowToState.Cancel)
    }

    protected fun setText(textId: Int) {
        tvSwitcher.setText(tvSwitcher.context.getString(textId))
    }

    protected fun setMainButtonText(textId: Int) {
        btnCancel.setText(textId)
    }

    protected fun dpToPx(value: Float): Float = mainView.dpToPx(value)

    private fun initTextChangesAnimation() {
        tvSwitcher.setInAnimation(context, android.R.anim.slide_in_left)
        tvSwitcher.setOutAnimation(context, android.R.anim.slide_out_right)
    }
}

fun View.hideWithFade(duration: Long, onEnd: VoidCallback? = null) {
    this.fadeOut(duration) {
        this.hide()
        onEnd?.invoke()
    }
}

fun View.showWithFade(duration: Long, onEnd: VoidCallback? = null) {
    this.show()
    this.fadeIn(duration, onEnd = onEnd)
}

