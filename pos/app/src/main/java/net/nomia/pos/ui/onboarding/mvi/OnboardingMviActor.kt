package net.nomia.pos.ui.onboarding.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import net.nomia.auth.domain.LogoutUseCase
import net.nomia.mvi.MviActor
import net.nomia.pos.ui.onboarding.domain.usecase.local.GetManagerDataUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFifthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFirstStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFourthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveSecondStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveThirdStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.remote.SaveManagerDataUseCase
import net.nomia.pos.ui.onboarding.model.ContinueButtonState
import net.nomia.pos.ui.onboarding.model.OnboardingStep
import net.nomia.pos.ui.onboarding.model.Resource

internal class OnboardingMviActor(
    private val logoutUseCase: LogoutUseCase,
    private val getManagerDataUseCase: GetManagerDataUseCase,
    private val saveFirstStepValueUseCase: SaveFirstStepValueUseCase,
    private val saveSecondStepValueUseCase: SaveSecondStepValueUseCase,
    private val saveThirdStepValueUseCase: SaveThirdStepValueUseCase,
    private val saveFourthStepValueUseCase: SaveFourthStepValueUseCase,
    private val saveFifthStepValueUseCase: SaveFifthStepValueUseCase,
    private val saveManagerDataUseCase: SaveManagerDataUseCase,
) : MviActor<OnboardingMviAction, OnboardingMviEffect, OnboardingMviState> {

    override fun invoke(
        previousState: OnboardingMviState, action: OnboardingMviAction
    ): Flow<OnboardingMviEffect> {
        return when (action) {
            is OnboardingMviAction.MoveForward -> action.moveForward(previousState = previousState)
            is OnboardingMviAction.MoveBack -> action.moveBack(previousState = previousState)
            is OnboardingMviAction.SkipStep -> action.skipStep(previousState = previousState)
            is OnboardingMviAction.InputClientName -> action()
            is OnboardingMviAction.InputPhoneOrEmail -> action()
            is OnboardingMviAction.InputNameOfShop -> action()
            is OnboardingMviAction.InputCountryAndCity -> action()
            is OnboardingMviAction.InputAddress -> action()
            is OnboardingMviAction.InputAutomationSystem -> action()
            is OnboardingMviAction.SetIsNewPlace -> action()
            is OnboardingMviAction.SetIsBar -> action()
            is OnboardingMviAction.SetIsCafe -> action()
            is OnboardingMviAction.SetIsCoffeeHouse -> action()
            is OnboardingMviAction.SetIsCooking -> action()
            is OnboardingMviAction.SetIsDiningRoom -> action()
            is OnboardingMviAction.SetIsOther -> action()
            is OnboardingMviAction.SetIsRestaurant -> action()
            is OnboardingMviAction.SetKitchenArea -> action()
            is OnboardingMviAction.SetNumberOfSeats -> action()
            is OnboardingMviAction.SetPublicArea -> action()
            is OnboardingMviAction.SetTotalArea -> action()
            is OnboardingMviAction.SetIsDelivery -> action()
            is OnboardingMviAction.SetIsInTheStore -> action()
            is OnboardingMviAction.SetIsTakeAway -> action()
            is OnboardingMviAction.FetchManagerData -> action()
        }
    }

    private infix fun OnboardingMviAction.moveForward(
        previousState: OnboardingMviState,
    ): Flow<OnboardingMviEffect> = flow {

        saveStepValue(previousState = previousState)

        when (previousState.onboardingStep) {
            OnboardingStep.FIRST -> emit(
                OnboardingMviEffect.MoveFroward(
                    onboardingStep = previousState.onboardingStep.getNext(),
                    skipButtonVisible = false,
                    backButtonVisible = true,
                    footerVisible = true,
                    coloredSegmentsCount = previousState.coloredSegments.inc(),
                )
            )

            OnboardingStep.SECOND -> emit(
                OnboardingMviEffect.MoveFroward(
                    onboardingStep = previousState.onboardingStep.getNext(),
                    skipButtonVisible = true,
                    backButtonVisible = true,
                    footerVisible = true,
                    coloredSegmentsCount = previousState.coloredSegments.inc(),
                )
            )

            OnboardingStep.THIRD -> emit(
                OnboardingMviEffect.MoveFroward(
                    onboardingStep = previousState.onboardingStep.getNext(),
                    skipButtonVisible = true,
                    backButtonVisible = true,
                    footerVisible = true,
                    coloredSegmentsCount = previousState.coloredSegments.inc(),
                )
            )

            OnboardingStep.FOURTH -> emit(
                OnboardingMviEffect.MoveFroward(
                    onboardingStep = previousState.onboardingStep.getNext(),
                    skipButtonVisible = true,
                    backButtonVisible = true,
                    footerVisible = true,
                    coloredSegmentsCount = previousState.coloredSegments.inc(),
                )
            )

            OnboardingStep.FIFTH -> {
                saveManagerDataUseCase().collect { result ->
                    when (result) {
                        is Resource.Loading -> emit(
                            OnboardingMviEffect.SetContinueButtonState(ContinueButtonState.PROGRESS)
                        )

                        is Resource.Success -> emit(
                            OnboardingMviEffect.MoveFroward(
                                onboardingStep = previousState.onboardingStep.getNext(),
                                skipButtonVisible = false,
                                backButtonVisible = false,
                                footerVisible = false,
                                coloredSegmentsCount = previousState.coloredSegments.inc()
                            )
                        )

                        is Resource.Error -> emit(
                            OnboardingMviEffect.ShowError(message = result.message)
                        )
                    }
                }
            }

            OnboardingStep.SIXTH -> Unit
        }
    }

    private infix fun OnboardingMviAction.moveBack(
        previousState: OnboardingMviState,
    ): Flow<OnboardingMviEffect> = flow {

        val skipButtonVisible: Boolean
        val backButtonVisible: Boolean

        when (previousState.onboardingStep) {
            OnboardingStep.FIRST -> {
                logoutUseCase.invoke()
                return@flow
            }

            OnboardingStep.SECOND -> {
                skipButtonVisible = false
                backButtonVisible = false
            }

            OnboardingStep.THIRD -> {
                skipButtonVisible = false
                backButtonVisible = true
            }

            OnboardingStep.FOURTH -> {
                skipButtonVisible = true
                backButtonVisible = true
            }

            OnboardingStep.FIFTH -> {
                skipButtonVisible = true
                backButtonVisible = true
            }

            OnboardingStep.SIXTH -> {
                skipButtonVisible = true
                backButtonVisible = true
            }
        }

        emit(
            OnboardingMviEffect.MoveBack(
                onboardingStep = previousState.onboardingStep.getPrevious(),
                skipButtonVisible = skipButtonVisible,
                backButtonVisible = backButtonVisible,
                continueButtonState = ContinueButtonState.ENABLED,
                coloredSegmentsCount = previousState.coloredSegments.dec(),
                footerVisible = true,
            )
        )
    }

    private infix fun OnboardingMviAction.skipStep(
        previousState: OnboardingMviState,
    ): Flow<OnboardingMviEffect> = if (previousState.onboardingStep == OnboardingStep.FIFTH) {
        saveManagerDataUseCase().map { result ->
            when (result) {
                is Resource.Loading ->
                    OnboardingMviEffect.SetContinueButtonState(ContinueButtonState.PROGRESS)

                is Resource.Success ->
                    OnboardingMviEffect.MoveFroward(
                        onboardingStep = previousState.onboardingStep.getNext(),
                        skipButtonVisible = false,
                        backButtonVisible = false,
                        footerVisible = false,
                        coloredSegmentsCount = previousState.coloredSegments.inc()
                    )

                is Resource.Error -> OnboardingMviEffect.ShowError(message = result.message)
            }
        }
    } else {
        flowOf(
            OnboardingMviEffect.MoveFroward(
                onboardingStep = previousState.onboardingStep.getNext(),
                skipButtonVisible = true,
                backButtonVisible = true,
                footerVisible = true,
                coloredSegmentsCount = previousState.coloredSegments.inc()
            )
        )
    }

    private operator fun OnboardingMviAction.InputClientName.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.InputClientName(clientName = this.clientName)
        )

    private operator fun OnboardingMviAction.InputPhoneOrEmail.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.InputPhoneOrEmail(phoneOrEmail = this.phoneOrEmail)
        )

    private operator fun OnboardingMviAction.InputNameOfShop.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.InputNameOfShop(nameOfShop = this.nameOfShop)
        )

    private operator fun OnboardingMviAction.InputCountryAndCity.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.InputCountryAndCity(countryAndCity = this.countryAndCity)
        )

    private operator fun OnboardingMviAction.InputAddress.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.InputAddress(address = this.address)
        )

    private operator fun OnboardingMviAction.InputAutomationSystem.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.InputAutomationSystem(automationSystem = this.automationSystem)
        )

    private operator fun OnboardingMviAction.SetIsNewPlace.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsNewPlace(isNewPlace = this.isNewPlace)
        )

    private operator fun OnboardingMviAction.SetIsBar.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsBar(isBar = this.isBar)
        )

    private operator fun OnboardingMviAction.SetIsCafe.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsCafe(isCafe = this.isCafe)
        )

    private operator fun OnboardingMviAction.SetIsCoffeeHouse.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsCoffeeHouse(isCoffeeHouse = this.isCoffeeHouse)
        )

    private operator fun OnboardingMviAction.SetIsCooking.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsCooking(isCooking = this.isCooking)
        )

    private operator fun OnboardingMviAction.SetIsDiningRoom.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsDiningRoom(isDiningRoom = this.isDiningRoom)
        )

    private operator fun OnboardingMviAction.SetIsOther.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsOther(isOther = this.isOther)
        )

    private operator fun OnboardingMviAction.SetIsRestaurant.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsRestaurant(isRestaurant = this.isRestaurant)
        )

    private operator fun OnboardingMviAction.SetKitchenArea.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetKitchenArea(kitchenArea = this.kitchenArea)
        )

    private operator fun OnboardingMviAction.SetNumberOfSeats.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetNumberOfSeats(numberOfSeats = this.numberOfSeats)
        )

    private operator fun OnboardingMviAction.SetPublicArea.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetPublicArea(publicArea = this.publicArea)
        )

    private operator fun OnboardingMviAction.SetTotalArea.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetTotalArea(totalArea = totalArea)
        )

    private operator fun OnboardingMviAction.SetIsDelivery.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsDelivery(isDelivery = this.isDelivery)
        )

    private operator fun OnboardingMviAction.SetIsInTheStore.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsInTheStore(isInTheStore = this.isInTheStore)
        )

    private operator fun OnboardingMviAction.SetIsTakeAway.invoke(): Flow<OnboardingMviEffect> =
        flowOf(
            OnboardingMviEffect.SetIsTakeAway(isTakeAway = this.isTakeAway)
        )

    private operator fun OnboardingMviAction.FetchManagerData.invoke(): Flow<OnboardingMviEffect> =
        flow {
            val managerData = getManagerDataUseCase()
            emit(
                OnboardingMviEffect.FetchManagerData(
                    managerData = managerData
                )
            )
        }

    private fun OnboardingStep.getNext(): OnboardingStep {
        val currentStep = this
        val nextStepIndex = currentStep.position.inc()
        val nextOnboardingStep = OnboardingStep.values().getOrElse(index = nextStepIndex) {
            currentStep
        }
        return nextOnboardingStep
    }

    private fun OnboardingStep.getPrevious(): OnboardingStep {
        val currentStep = this
        val previousStepIndex = currentStep.position.dec()
        val nextOnboardingStep = OnboardingStep.values().getOrElse(index = previousStepIndex) {
            currentStep
        }
        return nextOnboardingStep
    }

    private fun saveStepValue(previousState: OnboardingMviState) =
        when (previousState.onboardingStep) {
            OnboardingStep.FIRST -> saveFirstStepValueUseCase(previousState.firstStepValue)
            OnboardingStep.SECOND -> saveSecondStepValueUseCase(previousState.secondStepValue)
            OnboardingStep.THIRD -> saveThirdStepValueUseCase(previousState.thirdStepValue)
            OnboardingStep.FOURTH -> saveFourthStepValueUseCase(previousState.fourthStepValue)
            OnboardingStep.FIFTH -> saveFifthStepValueUseCase(previousState.fifthStepValue)
            else -> Unit
        }
}
