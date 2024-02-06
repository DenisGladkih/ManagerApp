package net.nomia.pos.ui.onboarding.domain.model

internal data class FourthStepValue(
    val totalArea: String,
    val numberOfSeats: String,
    val publicArea: String,
    val kitchenArea: String,
) {
    companion object {
        val INITIAL = FourthStepValue(
            totalArea = "",
            numberOfSeats = "",
            publicArea = "",
            kitchenArea = "",
        )
    }
}
