package net.nomia.pos.ui.onboarding.presentation.mvi

import net.nomia.mvi.Event
import net.nomia.mvi.EventProducer

internal object OnboardingMviEventProducer : EventProducer<OnboardingMviEffect, Event> {
    override fun invoke(effect: OnboardingMviEffect): Event? =
        when (effect) {
            is OnboardingMviEffect.ShowError -> OnboardingMviEvent.OnShowError(error = effect.message)
            else -> null
        }
}
