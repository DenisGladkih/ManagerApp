package net.nomia.pos.ui.onboarding.presentation.mvi

import net.nomia.mvi.Event

internal sealed interface OnboardingMviEvent : Event {
    data class OnShowError(val error: String?) : OnboardingMviEvent
}
