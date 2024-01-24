package net.nomia.pos.ui.onboarding.domain.usecase

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.KITCHEN_AREA
import net.nomia.pos.ui.onboarding.NUMBER_OF_SEATS
import net.nomia.pos.ui.onboarding.PUBLIC_AREA
import net.nomia.pos.ui.onboarding.TOTAL_AREA
import net.nomia.pos.ui.onboarding.model.FourthStepValue
import javax.inject.Inject

internal interface GetFourthStepValueUseCase {
    operator fun invoke(): FourthStepValue
}

internal class GetFourthStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : GetFourthStepValueUseCase {
    override fun invoke(): FourthStepValue {
        val totalArea = globalStateHandlerRepository.get(
            key = TOTAL_AREA,
            clazz = String::class,
        )

        val numberOfSeats = globalStateHandlerRepository.get(
            key = NUMBER_OF_SEATS,
            clazz = String::class,
        )

        val publicArea = globalStateHandlerRepository.get(
            key = PUBLIC_AREA,
            clazz = String::class,
        )

        val kitchenArea = globalStateHandlerRepository.get(
            key = KITCHEN_AREA,
            clazz = String::class,
        )

        return FourthStepValue(
            totalArea = totalArea ?: "",
            numberOfSeats = numberOfSeats ?: "",
            publicArea = publicArea ?: "",
            kitchenArea = kitchenArea ?: "",
        )
    }
}
