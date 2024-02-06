package net.nomia.pos.ui.onboarding.presentation.mvi

import net.nomia.pos.ui.onboarding.domain.model.ContinueButtonState
import net.nomia.pos.ui.onboarding.domain.model.ManagerData
import net.nomia.pos.ui.onboarding.domain.model.OnboardingStep

internal sealed interface OnboardingMviEffect {
    data class MoveFroward(
        val onboardingStep: OnboardingStep,
        val skipButtonVisible: Boolean,
        val backButtonVisible: Boolean,
        val footerVisible: Boolean,
        val coloredSegmentsCount: Int,
    ) : OnboardingMviEffect

    data class MoveBack(
        val onboardingStep: OnboardingStep,
        val skipButtonVisible: Boolean,
        val backButtonVisible: Boolean,
        val footerVisible: Boolean,
        val coloredSegmentsCount: Int,
        val continueButtonState: ContinueButtonState,
    ) : OnboardingMviEffect


    data class SetContinueButtonState(
        val continueButtonState: ContinueButtonState,
    ) : OnboardingMviEffect

    data class FetchManagerData(val managerData: ManagerData) : OnboardingMviEffect

    data class ShowError(val message: String?) : OnboardingMviEffect

    data class InputClientName(val clientName: String) : OnboardingMviEffect

    data class InputPhoneOrEmail(val phoneOrEmail: String) : OnboardingMviEffect

    data class InputNameOfShop(val nameOfShop: String) : OnboardingMviEffect

    data class InputCountryAndCity(val countryAndCity: String) : OnboardingMviEffect

    data class InputAddress(val address: String) : OnboardingMviEffect

    data class InputAutomationSystem(val automationSystem: String) : OnboardingMviEffect

    data class SetIsNewPlace(val isNewPlace: Boolean) : OnboardingMviEffect

    data class SetIsBar(val isBar: Boolean) : OnboardingMviEffect

    data class SetIsCafe(val isCafe: Boolean) : OnboardingMviEffect

    data class SetIsCoffeeHouse(val isCoffeeHouse: Boolean) : OnboardingMviEffect

    data class SetIsCooking(val isCooking: Boolean) : OnboardingMviEffect

    data class SetIsDiningRoom(val isDiningRoom: Boolean) : OnboardingMviEffect

    data class SetIsOther(val isOther: Boolean) : OnboardingMviEffect

    data class SetIsRestaurant(val isRestaurant: Boolean) : OnboardingMviEffect

    data class SetTotalArea(val totalArea: String) : OnboardingMviEffect

    data class SetNumberOfSeats(val numberOfSeats: String) : OnboardingMviEffect

    data class SetPublicArea(val publicArea: String) : OnboardingMviEffect

    data class SetKitchenArea(val kitchenArea: String) : OnboardingMviEffect

    data class SetIsTakeAway(val isTakeAway: Boolean) : OnboardingMviEffect

    data class SetIsInTheStore(val isInTheStore: Boolean) : OnboardingMviEffect

    data class SetIsDelivery(val isDelivery: Boolean) : OnboardingMviEffect
}
