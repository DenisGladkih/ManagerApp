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
    val firstStepValue: FirstStepValue,
    val secondStepValue: SecondStepValue,
    val thirdStepValue: ThirdStepValue,
    val fourthStepValue: FourthStepValue,
    val fifthStepValue: FifthStepValue,
    val continueButtonState: ContinueButtonState,
) {
    companion object {
        val INITIAL = OnboardingMviState(
            skipButtonVisible = false,
            backButtonVisible = false,
            footerVisible = true,
            onboardingStep = OnboardingStep.FIRST,
            firstStepValue = FirstStepValue.INITIAL,
            secondStepValue = SecondStepValue.INITIAL,
            thirdStepValue = ThirdStepValue.INITIAL,
            fourthStepValue = FourthStepValue.INITIAL,
            fifthStepValue = FifthStepValue.INITIAL,
            continueButtonState = ContinueButtonState.DISABLED,
        )
    }
}
