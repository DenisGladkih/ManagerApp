package net.nomia.pos.ui.onboarding.mvi

import net.nomia.mvi.Event
import net.nomia.mvi.MviFeatureFactory

internal class OnboardingMviFeatureFactory(
    actor: OnboardingMviActor,
) : MviFeatureFactory<OnboardingMviAction, OnboardingMviEffect, OnboardingMviState, Event>(
    initialState = OnboardingMviState.INITIAL,
    actor = actor,
    reducer = OnboardingMviReducer,
    eventProducer = OnboardingMviEventProducer,
    tagPostfix = "OnboardingMviFeature"
)
