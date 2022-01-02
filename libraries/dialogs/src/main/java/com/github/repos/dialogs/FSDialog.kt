package com.github.repos.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.feliperrm.utils.getViewModel
import com.github.repos.core.FullScreenDialogFragment
import com.github.repos.dialogs.databinding.DialogFragmentBinding
import kotlinx.android.synthetic.main.dialog_fragment.*


/**
 * Useful dialog instance that will be used throughout the app
 */

class FSDialog : FullScreenDialogFragment() {

    companion object {
        fun newInstance() = FSDialog()
    }

    private val vm by lazy {
        getViewModel {
            FSDialogViewModel(
                imageRes,
                titleRes,
                textRes,
                lottieRes,
                text,
                negativeButtonTextRes,
                positiveButtonTextRes
            )
        }
    }

    @DrawableRes
    private var imageRes: Int? = null

    @StringRes
    private var titleRes: Int? = null

    @StringRes
    private var textRes: Int? = null

    @RawRes
    private var lottieRes: Int? = null

    @StringRes
    private var negativeButtonTextRes: Int? = null

    @StringRes
    private var positiveButtonTextRes: Int? = null

    private var text: String? = null


    private var negativeButtonClicked: ((dialog: FSDialog, button: TextView) -> Unit)? = null

    private var positiveButtonClicked: ((dialog: FSDialog, button: TextView) -> Unit)? = null

    override fun onStart() {
        super.onStart()
        dialog?.window?.setWindowAnimations(R.style.DialogNoAnimation)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogFragmentBinding.inflate(
        inflater,
        container,
        false
    ).apply { lifecycleOwner = viewLifecycleOwner; viewModel = vm }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animateElements()
        btnNegative.setOnClickListener {
            negativeButtonClicked?.invoke(this, btnNegative) ?: dismiss()
        }
        btnPositive.setOnClickListener { positiveButtonClicked?.invoke(this, btnPositive) }
        dialog?.setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                dismiss()
                true
            } else {
                false
            }
        }
    }

    private fun animateElements() {
        cardView.scaleX = 0f
        cardView.scaleY = 0f
        cardView.visibility = View.VISIBLE
        frameBlackBackground.alpha = 0f
        frameBlackBackground.visibility = View.VISIBLE
        frameBlackBackground.animate().alpha(1f).setDuration(200).start()
        cardView.animate().scaleX(1f).scaleY(1f).setDuration(250)
            .setInterpolator(OvershootInterpolator()).setStartDelay(25).start()
    }

    override fun dismiss() {
        animateExit { super.dismiss() }
    }

    override fun onCancel(dialog: DialogInterface) {
        animateExit { super.onCancel(dialog) }
    }

    private fun animateExit(endAction: () -> Unit) {
        cardView.animate().scaleX(0f).scaleY(0f).alpha(0f).setInterpolator(LinearInterpolator())
            .setDuration(175).start()
        frameBlackBackground.animate().alpha(0f).setDuration(175).withEndAction(endAction).start()
    }

    fun setImageRes(@DrawableRes imageRes: Int?): FSDialog {
        this.imageRes = imageRes
        if (!isDetached && isAdded) {
            vm.setImageRes(imageRes)
        }
        return this
    }

    fun setTitleRes(@StringRes titleRes: Int?): FSDialog {
        this.titleRes = titleRes
        if (!isDetached && isAdded) {
            vm.setTitleRes(titleRes)
        }
        return this
    }

    fun setTextRes(@StringRes textRes: Int?): FSDialog {
        this.textRes = textRes
        if (!isDetached && isAdded) {
            vm.setTextRes(textRes)
        }
        return this
    }

    fun setText(text: String?): FSDialog {
        this.text = text
        if (!isDetached && isAdded) {
            vm.setText(text)
        }
        return this
    }

    fun setLottieImage(@RawRes lottieRes: Int?): FSDialog {
        this.lottieRes = lottieRes
        if (!isDetached && isAdded) {
            vm.setLottieImage(lottieRes)
        }
        return this
    }

    /**
     * Sets the text resource to be displayed in the positive button and a callback for clicking it.
     */
    fun setPositiveButton(
        @StringRes buttonTextRes: Int?,
        clickCallback: (dialog: FSDialog, button: TextView) -> Unit
    ): FSDialog {
        this.positiveButtonTextRes = buttonTextRes
        this.positiveButtonClicked = clickCallback
        if (!isDetached && isAdded) {
            vm.setPositiveButtonTextRes(buttonTextRes)
        }
        return this
    }

    /**
     * Sets the text resource to be displayed in the negative button and an optional callback for clicking it.
     * If no callback is set, the default behavior is to dismiss the dialog.
     */
    fun setNegativeButton(
        @StringRes buttonTextRes: Int?,
        clickCallback: ((dialog: FSDialog, button: TextView) -> Unit)? = null
    ): FSDialog {
        this.negativeButtonTextRes = buttonTextRes
        this.negativeButtonClicked = clickCallback
        if (!isDetached && isAdded) {
            vm.setNegativeButtonTextRes(buttonTextRes)
        }
        return this
    }

}
