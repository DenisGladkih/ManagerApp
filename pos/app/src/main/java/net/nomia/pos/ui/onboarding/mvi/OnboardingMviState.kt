package net.nomia.pos.ui.onboarding.mvi

import net.nomia.pos.ui.onboarding.model.OnboardingStep

internal data class OnboardingMviState(
    val skipButtonVisible: Boolean,
    val backButtonVisible: Boolean,
    val isButtonContinueEnabled: Boolean,
    val footerVisible: Boolean,
    val onboardingStep: OnboardingStep,
) {
    companion object {
        val INITIAL = OnboardingMviState(
            skipButtonVisible = false,
            backButtonVisible = false,
            isButtonContinueEnabled = true,
            footerVisible = true,
            onboardingStep = OnboardingStep.FIRST,
        )
    }
}
