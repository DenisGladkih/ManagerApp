package net.nomia.pos.ui.onboarding.mvi

import net.nomia.mvi.MviReducer
import net.nomia.pos.ui.onboarding.model.ContinueButtonState
import net.nomia.pos.ui.onboarding.model.OnboardingStep

internal object OnboardingMviReducer : MviReducer<OnboardingMviEffect, OnboardingMviState> {

    override suspend fun invoke(
        previousState: OnboardingMviState,
        effect: OnboardingMviEffect,
    ): OnboardingMviState {
        return when (effect) {
            is OnboardingMviEffect.MoveFroward -> effect(previousState)
            is OnboardingMviEffect.MoveBack -> effect(previousState)
            is OnboardingMviEffect.InputClientName -> effect(previousState)
            is OnboardingMviEffect.InputPhoneOrEmail -> effect(previousState)
            is OnboardingMviEffect.InputNameOfShop -> effect(previousState)
            is OnboardingMviEffect.InputCountryAndCity -> effect(previousState)
            is OnboardingMviEffect.InputAddress -> effect(previousState)
            is OnboardingMviEffect.InputAutomationSystem -> effect(previousState)
            is OnboardingMviEffect.SetIsNewPlace -> effect(previousState)
            is OnboardingMviEffect.SetIsBar -> effect(previousState)
            is OnboardingMviEffect.SetIsCafe -> effect(previousState)
            is OnboardingMviEffect.SetIsCoffeeHouse -> effect(previousState)
            is OnboardingMviEffect.SetIsCooking -> effect(previousState)
            is OnboardingMviEffect.SetIsDiningRoom -> effect(previousState)
            is OnboardingMviEffect.SetIsOther -> effect(previousState)
            is OnboardingMviEffect.SetIsRestaurant -> effect(previousState)
            is OnboardingMviEffect.SetKitchenArea -> effect(previousState)
            is OnboardingMviEffect.SetNumberOfSeats -> effect(previousState)
            is OnboardingMviEffect.SetPublicArea -> effect(previousState)
            is OnboardingMviEffect.SetTotalArea -> effect(previousState)
            is OnboardingMviEffect.SetIsDelivery -> effect(previousState)
            is OnboardingMviEffect.SetIsInTheStore -> effect(previousState)
            is OnboardingMviEffect.SetIsTakeAway -> effect(previousState)
            is OnboardingMviEffect.FetchManagerData -> effect(previousState)
            is OnboardingMviEffect.SetContinueButtonState -> effect(previousState)
            is OnboardingMviEffect.ShowError -> effect(previousState)
        }
    }

    private operator fun OnboardingMviEffect.MoveFroward.invoke(previousState: OnboardingMviState): OnboardingMviState {
        val isStateValid = if (onboardingStep == OnboardingStep.SECOND) {
            previousState.secondStepValue.isValid()
        } else {
            true
        }

        return previousState.copy(
            onboardingStep = this.onboardingStep,
            skipButtonVisible = this.skipButtonVisible,
            backButtonVisible = this.backButtonVisible,
            footerVisible = this.footerVisible,
            coloredSegments = this.coloredSegmentsCount,
            continueButtonState = getContinueButtonState(isStateValid = isStateValid),
        )
    }
}

private operator fun OnboardingMviEffect.MoveBack.invoke(previousState: OnboardingMviState) =
    previousState.copy(
        onboardingStep = this.onboardingStep,
        skipButtonVisible = this.skipButtonVisible,
        backButtonVisible = this.backButtonVisible,
        footerVisible = this.footerVisible,
        coloredSegments = this.coloredSegmentsCount,
        continueButtonState = this.continueButtonState,
    )

private operator fun OnboardingMviEffect.InputClientName.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.firstStepValue.copy(clientName = this.clientName)
    val continueButtonState = getContinueButtonState(isStateValid = newValue.isValid())

    return previousState.copy(
        firstStepValue = newValue,
        continueButtonState = continueButtonState,
    )
}

private operator fun OnboardingMviEffect.InputPhoneOrEmail.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.firstStepValue.copy(phoneOrEmail = this.phoneOrEmail)
    val continueButtonState = getContinueButtonState(isStateValid = newValue.isValid())

    return previousState.copy(
        firstStepValue = newValue,
        continueButtonState = continueButtonState,
    )
}

private operator fun OnboardingMviEffect.InputNameOfShop.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.secondStepValue.copy(nameOfShop = this.nameOfShop)
    val continueButtonState = getContinueButtonState(isStateValid = newValue.isValid())

    return previousState.copy(
        secondStepValue = newValue,
        continueButtonState = continueButtonState
    )
}

private operator fun OnboardingMviEffect.InputCountryAndCity.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.secondStepValue.copy(countryAndCity = this.countryAndCity)
    val continueButtonState = getContinueButtonState(isStateValid = newValue.isValid())

    return previousState.copy(
        secondStepValue = newValue,
        continueButtonState = continueButtonState,
    )
}

private operator fun OnboardingMviEffect.InputAddress.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.secondStepValue.copy(address = this.address)
    val continueButtonState = getContinueButtonState(isStateValid = newValue.isValid())

    return previousState.copy(
        secondStepValue = newValue,
        continueButtonState = continueButtonState,
    )
}

private operator fun OnboardingMviEffect.InputAutomationSystem.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.secondStepValue.copy(automationSystem = this.automationSystem)
    val continueButtonState = getContinueButtonState(isStateValid = newValue.isValid())

    return previousState.copy(
        secondStepValue = newValue,
        continueButtonState = continueButtonState,
    )
}

private operator fun OnboardingMviEffect.SetIsNewPlace.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.secondStepValue.copy(isNewPlace = this.isNewPlace)
    val continueButtonState = getContinueButtonState(isStateValid = newValue.isValid())

    return previousState.copy(
        secondStepValue = newValue,
        continueButtonState = continueButtonState,
    )
}

private operator fun OnboardingMviEffect.SetIsBar.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.thirdStepValue.copy(isBar = this.isBar)
    return previousState.copy(thirdStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsCafe.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.thirdStepValue.copy(isCafe = this.isCafe)
    return previousState.copy(thirdStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsCoffeeHouse.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.thirdStepValue.copy(isCoffeeHouse = this.isCoffeeHouse)
    return previousState.copy(thirdStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsCooking.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.thirdStepValue.copy(isCooking = this.isCooking)
    return previousState.copy(thirdStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsDiningRoom.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.thirdStepValue.copy(isDiningRoom = this.isDiningRoom)
    return previousState.copy(thirdStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsOther.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.thirdStepValue.copy(isOther = this.isOther)
    return previousState.copy(thirdStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsRestaurant.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.thirdStepValue.copy(isRestaurant = this.isRestaurant)
    return previousState.copy(thirdStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetKitchenArea.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.fourthStepValue.copy(kitchenArea = this.kitchenArea)
    return previousState.copy(fourthStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetNumberOfSeats.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.fourthStepValue.copy(numberOfSeats = this.numberOfSeats)
    return previousState.copy(fourthStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetPublicArea.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.fourthStepValue.copy(publicArea = this.publicArea)
    return previousState.copy(fourthStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetTotalArea.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.fourthStepValue.copy(totalArea = this.totalArea)
    return previousState.copy(fourthStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsDelivery.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.fifthStepValue.copy(isDelivery = this.isDelivery)
    return previousState.copy(fifthStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsInTheStore.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.fifthStepValue.copy(isInTheStore = this.isInTheStore)
    return previousState.copy(fifthStepValue = newValue)
}

private operator fun OnboardingMviEffect.SetIsTakeAway.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState {
    val newValue = previousState.fifthStepValue.copy(isTakeAway = this.isTakeAway)
    return previousState.copy(fifthStepValue = newValue)
}

private operator fun OnboardingMviEffect.ShowError.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState = previousState.copy(continueButtonState = ContinueButtonState.ENABLED)

private operator fun OnboardingMviEffect.SetContinueButtonState.invoke(
    previousState: OnboardingMviState,
): OnboardingMviState = previousState.copy(continueButtonState = this.continueButtonState)

private operator fun OnboardingMviEffect.FetchManagerData.invoke(
    previousState: OnboardingMviState
): OnboardingMviState = previousState.copy(
    continueButtonState = getContinueButtonState(this.managerData.firstStepValue.isValid()),
    firstStepValue = this.managerData.firstStepValue,
    secondStepValue = this.managerData.secondStepValue,
    thirdStepValue = this.managerData.thirdStepValue,
    fourthStepValue = this.managerData.fourthStepValue,
    fifthStepValue = this.managerData.fifthStepValue,
)

private fun getContinueButtonState(isStateValid: Boolean): ContinueButtonState {
    val continueButtonState = if (isStateValid) {
        ContinueButtonState.ENABLED
    } else {
        ContinueButtonState.DISABLED
    }
    return continueButtonState
}
