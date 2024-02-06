package net.nomia.pos.ui.onboarding.domain.model

internal data class ThirdStepValue(
    val isRestaurant: Boolean,
    val isBar: Boolean,
    val isCafe: Boolean,
    val isDiningRoom: Boolean,
    val isCoffeeHouse: Boolean,
    val isCooking: Boolean,
    val isOther: Boolean,
) {
    companion object {
        val INITIAL = ThirdStepValue(
            isRestaurant = false,
            isBar = false,
            isCafe = false,
            isDiningRoom = false,
            isCoffeeHouse = false,
            isCooking = false,
            isOther = false,
        )
    }
}
