package net.nomia.pos.ui.onboarding.mvi

import net.nomia.pos.ui.onboarding.model.ContinueButtonState
import net.nomia.pos.ui.onboarding.model.FifthStepValue
import net.nomia.pos.ui.onboarding.model.FirstStepValue
import net.nomia.pos.ui.onboarding.model.FourthStepValue
import net.nomia.pos.ui.onboarding.model.OnboardingStep
import net.nomia.pos.ui.onboarding.model.SecondStepValue
import net.nomia.pos.ui.onboarding.model.ThirdStepValue

internal data class OnboardingMviState(
    val skipButtonVisible: Boolean,
    val backButtonVisible: Boolean,
    val footerVisible: Boolean,
    val onboardingStep: OnboardingStep,
    val coloredSegments: Int,
    val topBarSegmentsCount: Int,
    val continueButtonState: ContinueButtonState,
    val firstStepValue: FirstStepValue,
    val secondStepValue: SecondStepValue,
    val thirdStepValue: ThirdStepValue,
    val fourthStepValue: FourthStepValue,
    val fifthStepValue: FifthStepValue,
) {
    companion object {
        private val topBarSegmentsCount = OnboardingStep.values().size
        private const val defaultColoredSegments = 1
        val INITIAL = OnboardingMviState(
            skipButtonVisible = false,
            backButtonVisible = false,
            footerVisible = true,
            onboardingStep = OnboardingStep.FIRST,
            coloredSegments = defaultColoredSegments,
            topBarSegmentsCount = topBarSegmentsCount,
            continueButtonState = ContinueButtonState.DISABLED,
            firstStepValue = FirstStepValue.INITIAL,
            secondStepValue = SecondStepValue.INITIAL,
            thirdStepValue = ThirdStepValue.INITIAL,
            fourthStepValue = FourthStepValue.INITIAL,
            fifthStepValue = FifthStepValue.INITIAL,
        )
    }
}
