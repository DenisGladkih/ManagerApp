package net.nomia.pos.ui.onboarding.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import net.nomia.mvi.MviBootstrap

internal class OnboardingMviBootstrap(
) : MviBootstrap<OnboardingMviEffect> {
    override fun invoke(): Flow<OnboardingMviEffect> = emptyFlow()
}
