package net.nomia.pos.ui.onboarding.domain.usecase

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.KITCHEN_AREA
import net.nomia.pos.ui.onboarding.NUMBER_OF_SEATS
import net.nomia.pos.ui.onboarding.PUBLIC_AREA
import net.nomia.pos.ui.onboarding.TOTAL_AREA
import net.nomia.pos.ui.onboarding.model.FourthStepValue
import javax.inject.Inject

internal interface SaveFourthStepValueUseCase {
    operator fun invoke(fourthStepValue: FourthStepValue)
}

internal class SaveFourthStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : SaveFourthStepValueUseCase {

    override fun invoke(fourthStepValue: FourthStepValue) {
        globalStateHandlerRepository.save(
            key = TOTAL_AREA,
            value = fourthStepValue.totalArea,
        )

        globalStateHandlerRepository.save(
            key = NUMBER_OF_SEATS,
            value = fourthStepValue.numberOfSeats,
        )

        globalStateHandlerRepository.save(
            key = PUBLIC_AREA,
            value = fourthStepValue.publicArea,
        )

        globalStateHandlerRepository.save(
            key = KITCHEN_AREA,
            value = fourthStepValue.kitchenArea,
        )
    }
}
