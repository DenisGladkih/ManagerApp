package net.nomia.pos.ui.onboarding.mvi

data class OnboardingMviState(
    val skipButtonVisible: Boolean,
    val backButtonVisible: Boolean,
    val isButtonNextEnabled: Boolean,
    val footerVisible: Boolean,
    val coloredSegmentsCount: Int,
) {
    companion object {
        val INITIAL = OnboardingMviState(
            skipButtonVisible = true,
            backButtonVisible = true,
            isButtonNextEnabled = true,
            footerVisible = true,
            coloredSegmentsCount = 1,
        )
    }
}
