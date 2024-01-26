package net.nomia.pos.ui.onboarding.mvi

import net.nomia.mvi.Event

internal sealed interface OnboardingMviEvent : Event {
    data class OnShowError(val error: String?) : OnboardingMviEvent
}
