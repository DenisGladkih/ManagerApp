package net.nomia.pos.ui.onboarding.model

internal data class SecondStepValue(
    val nameOfShop: String,
    val countryAndCity: String,
    val address: String,
    val isNewPlace: Boolean,
    val automationSystem: String,
) {
    companion object {
        val INITIAL = SecondStepValue(
            nameOfShop = "",
            countryAndCity = "",
            address = "",
            isNewPlace = true,
            automationSystem = "",
        )
    }

    fun isValid(): Boolean {
        val firstValidationStep = nameOfShop.isNotBlank()
                && countryAndCity.isNotBlank()
                && address.isNotBlank()
        val secondValidationStep = automationSystem.isNotBlank()

        return if (isNewPlace) firstValidationStep else firstValidationStep && secondValidationStep
    }
}
