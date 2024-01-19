package net.nomia.pos.ui.onboarding.mvi

import net.nomia.mvi.Event
import net.nomia.mvi.MviFeatureFactory

internal class OnboardingMviFeatureFactory(
//    bootstrap: OnboardingMviBootstrap,
    actor: OnboardingMviActor,
) : MviFeatureFactory<OnboardingMviAction, OnboardingMviEffect, OnboardingMviState, Event>(
    initialState = OnboardingMviState.INITIAL,
//    bootstrap = bootstrap,
    actor = actor,
    eventProducer = OnboardingMviEventProducer,
    reducer = OnboardingMviReducer,
    tagPostfix = "OnboardingMviFeature"
)
