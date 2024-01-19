package net.nomia.pos.ui.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import net.nomia.mvi.MviViewModel
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviAction
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviFeatureFactory
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviState
import javax.inject.Inject

@HiltViewModel
internal class OnboardingViewModel @Inject constructor(
    factory: OnboardingMviFeatureFactory,
) : MviViewModel<OnboardingMviState, OnboardingMviAction>(factory)
