package net.nomia.pos.ui.onboarding.mvi

import net.nomia.pos.ui.onboarding.model.ContinueButtonState
import net.nomia.pos.ui.onboarding.model.FifthStepValue
import net.nomia.pos.ui.onboarding.model.FirstStepValue
import net.nomia.pos.ui.onboarding.model.FourthStepValue
import net.nomia.pos.ui.onboarding.model.OnboardingStep
import net.nomia.pos.ui.onboarding.model.SecondStepValue
import net.nomia.pos.ui.onboarding.model.ThirdStepValue

internal sealed interface OnboardingMviEffect {

    object Logout : OnboardingMviEffect

    data class Continue(
        val onboardingStep: OnboardingStep,
        val skipButtonVisible: Boolean,
        val backButtonVisible: Boolean,
        val footerVisible: Boolean,
        val continueButtonState: ContinueButtonState,
    ) : OnboardingMviEffect


    data class Back(
        val onboardingStep: OnboardingStep,
        val skipButtonVisible: Boolean,
        val backButtonVisible: Boolean,
        val footerVisible: Boolean,
        val continueButtonState: ContinueButtonState,
    ) : OnboardingMviEffect

    data class FetchData(
        val firstStepValue: FirstStepValue,
        val secondStepValue: SecondStepValue,
        val thirdStepValue: ThirdStepValue,
        val fourthStepValue: FourthStepValue,
        val fifthStepValue: FifthStepValue,
        val continueButtonState: ContinueButtonState,
    ) : OnboardingMviEffect

//    data class SetFirstStepValue(
//        val firstStepValue: FirstStepValue,
//    ) : OnboardingMviEffect
//
//    data class SetSecondStepValue(
//        val secondStepValue: SecondStepValue,
//    ) : OnboardingMviEffect
//
//    data class SetThirdStepValue(
//        val thirdStepValue: ThirdStepValue,
//    ) : OnboardingMviEffect
//
//    data class SetFourthStepValue(
//        val fourthStepValue: FourthStepValue,
//    ) : OnboardingMviEffect
//
//    data class SetFifthStepValue(
//        val fifthStepValue: FifthStepValue,
//    ) : OnboardingMviEffect

    data class ChangeContinueButtonState(
        val continueButtonState: ContinueButtonState,
    ) : OnboardingMviEffect


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
