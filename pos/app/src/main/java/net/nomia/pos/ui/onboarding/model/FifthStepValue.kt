package net.nomia.pos.ui.onboarding.model

internal data class FifthStepValue(
    val isTakeAway: Boolean,
    val isInTheStore: Boolean,
    val isDelivery: Boolean,
) {
    companion object {
        val INITIAL = FifthStepValue(
            isTakeAway = false,
            isInTheStore = false,
            isDelivery = false,
        )
    }
}
