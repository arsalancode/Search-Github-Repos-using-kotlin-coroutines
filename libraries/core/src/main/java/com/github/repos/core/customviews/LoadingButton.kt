package com.github.repos.core.customviews

import android.animation.*
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.repos.core.R

class LoadingButton : AppCompatButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var widthButton: Int = 0
    private var heightButton: Int = 0
    private var buttonText: String? = null
    private var buttonStartDrawable: Drawable? = null

    private var isMorphinInProgress: Boolean = false
    private var buttonState: State =
        State.IDLE
    private var gradientDrawable: GradientDrawable =
        ContextCompat.getDrawable(
            context,
            R.drawable.shape_rounded_rect_button
        ) as GradientDrawable

    /**
     * Method called to start the animation. Morphs in to a ball and then starts a loading spinner.
     */
    private fun startInitLoadingAnimation() {
        if (buttonState !== State.IDLE) {
            return
        }

        widthButton = width
        heightButton = height
        buttonText = text.toString()
        buttonStartDrawable = compoundDrawables[0]

        val initialCornerRadius =
            INITIAL_CORNER_RADIUS
        val finalCornerRadius =
            FINAL_CORNER_RADIUS

        isMorphinInProgress = true

        disableButton()

        val toWidth = height

        val cornerAnimation: ObjectAnimator = ObjectAnimator.ofFloat(
            gradientDrawable,
            "cornerRadius",
            initialCornerRadius,
            finalCornerRadius
        )

        val widthAnimation = ValueAnimator.ofInt(widthButton, toWidth)

        widthAnimation.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.width = value
            setLayoutParams(layoutParams)
        }

        val heightAnimation = ValueAnimator.ofInt(heightButton, toWidth)

        heightAnimation.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.height = value
            setLayoutParams(layoutParams)
        }

        val mMorphingAnimatorSet = AnimatorSet()

        mMorphingAnimatorSet.duration =
            ANIM_DURATION
        mMorphingAnimatorSet.playTogether(cornerAnimation, widthAnimation, heightAnimation)
        mMorphingAnimatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                isMorphinInProgress = false
            }
        })

        mMorphingAnimatorSet.start()
    }

    /**
     * Method called to start the animation. Go back into a button shape and then dismiss a loading spinner.
     */
    private fun startFinalLoadingAnimation() {
        if (buttonState !== State.PROGRESS) {
            return
        }

        val initialWidth = width
        val initialHeight = height
        val initialCornerRadius =
            INITIAL_CORNER_RADIUS
        val finalCornerRadius =
            FINAL_CORNER_RADIUS

        val cornerAnimation: ObjectAnimator = ObjectAnimator.ofFloat(
            gradientDrawable,
            "cornerRadius",
            finalCornerRadius,
            initialCornerRadius
        )

        val widthAnimation = ValueAnimator.ofInt(initialWidth, widthButton)

        widthAnimation.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.width = value
            setLayoutParams(layoutParams)
        }

        val heightAnimation = ValueAnimator.ofInt(initialHeight, heightButton)

        heightAnimation.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.height = value
            setLayoutParams(layoutParams)
        }

        val mMorphingAnimatorSet = AnimatorSet()

        mMorphingAnimatorSet.duration =
            ANIM_DURATION
        mMorphingAnimatorSet.playTogether(cornerAnimation, widthAnimation, heightAnimation)
        mMorphingAnimatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                isMorphinInProgress = false

                enableButton()
            }
        })

        mMorphingAnimatorSet.start()
    }

    private fun disableButton() {
        buttonState =
            State.PROGRESS

        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        this.text = null
        this.isClickable = false
    }

    private fun enableButton() {
        buttonState =
            State.IDLE

        setCompoundDrawablesWithIntrinsicBounds(buttonStartDrawable, null, null, null)

        this.text = buttonText
        this.isClickable = true
    }

    var isLoading: Boolean? = null
        set(value) {
            field = value

            if (value == true) {
                startInitLoadingAnimation()
            } else if (value == false) {
                startFinalLoadingAnimation()
            }
        }

    enum class State {
        PROGRESS, IDLE
    }

    companion object {
        const val INITIAL_CORNER_RADIUS = 40f
        const val FINAL_CORNER_RADIUS = 1000f
        const val ANIM_DURATION = 300L

        @JvmStatic
        @BindingAdapter("loadingState")
        fun LoadingButton.setLoadingState(loadingState: Int?) {
            isLoading = loadingState == View.VISIBLE
        }
    }
}