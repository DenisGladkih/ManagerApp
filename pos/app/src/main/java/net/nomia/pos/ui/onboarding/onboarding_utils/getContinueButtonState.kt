package net.nomia.pos.ui.onboarding.onboarding_utils

import net.nomia.pos.ui.onboarding.model.ContinueButtonState

internal fun getContinueButtonState(isStateValid: Boolean): ContinueButtonState {
    val continueButtonState = if (isStateValid) {
        ContinueButtonState.ENABLED
    } else {
        ContinueButtonState.DISABLED
    }
    return continueButtonState
}
