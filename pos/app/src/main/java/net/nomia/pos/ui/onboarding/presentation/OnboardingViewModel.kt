package net.nomia.pos.ui.onboarding.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import net.nomia.mvi.MviViewModel
import net.nomia.pos.ui.onboarding.presentation.mvi.OnboardingMviAction
import net.nomia.pos.ui.onboarding.presentation.mvi.OnboardingMviFeatureFactory
import net.nomia.pos.ui.onboarding.presentation.mvi.OnboardingMviState
import javax.inject.Inject

@HiltViewModel
internal class OnboardingViewModel @Inject constructor(
    factory: OnboardingMviFeatureFactory,
) : MviViewModel<OnboardingMviState, OnboardingMviAction>(factory)
