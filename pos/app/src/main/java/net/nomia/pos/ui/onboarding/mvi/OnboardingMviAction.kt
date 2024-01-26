package net.nomia.pos.ui.onboarding.mvi

internal sealed interface OnboardingMviAction {
    object SkipStep : OnboardingMviAction

    object MoveForward : OnboardingMviAction

    object MoveBack : OnboardingMviAction

    object FetchManagerData : OnboardingMviAction

    data class InputClientName(val clientName: String) : OnboardingMviAction

    data class InputPhoneOrEmail(val phoneOrEmail: String) : OnboardingMviAction

    data class InputNameOfShop(val nameOfShop: String) : OnboardingMviAction

    data class InputCountryAndCity(val countryAndCity: String) : OnboardingMviAction

    data class InputAddress(val address: String) : OnboardingMviAction

    data class InputAutomationSystem(val automationSystem: String) : OnboardingMviAction

    data class SetIsNewPlace(val isNewPlace: Boolean) : OnboardingMviAction

    data class SetIsRestaurant(val isRestaurant: Boolean) : OnboardingMviAction

    data class SetIsBar(val isBar: Boolean) : OnboardingMviAction

    data class SetIsCafe(val isCafe: Boolean) : OnboardingMviAction

    data class SetIsDiningRoom(val isDiningRoom: Boolean) : OnboardingMviAction

    data class SetIsCoffeeHouse(val isCoffeeHouse: Boolean) : OnboardingMviAction

    data class SetIsCooking(val isCooking: Boolean) : OnboardingMviAction

    data class SetIsOther(val isOther: Boolean) : OnboardingMviAction

    data class SetTotalArea(val totalArea: String) : OnboardingMviAction

    data class SetNumberOfSeats(val numberOfSeats: String) : OnboardingMviAction

    data class SetPublicArea(val publicArea: String) : OnboardingMviAction

    data class SetKitchenArea(val kitchenArea: String) : OnboardingMviAction

    data class SetIsTakeAway(val isTakeAway: Boolean) : OnboardingMviAction

    data class SetIsInTheStore(val isInTheStore: Boolean) : OnboardingMviAction

    data class SetIsDelivery(val isDelivery: Boolean) : OnboardingMviAction
}
