package net.nomia.pos.ui.onboarding.domain.usecase

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.IS_DELIVERY
import net.nomia.pos.ui.onboarding.IS_IN_THE_STORE
import net.nomia.pos.ui.onboarding.IS_TAKE_AWAY
import net.nomia.pos.ui.onboarding.model.FifthStepValue
import javax.inject.Inject

internal interface GetFifthStepValueUseCase {
    operator fun invoke(): FifthStepValue
}

internal class GetFifthStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : GetFifthStepValueUseCase {
    override fun invoke(): FifthStepValue {
        val isTakeAway = globalStateHandlerRepository.get(
            key = IS_TAKE_AWAY,
            clazz = Boolean::class,
        )

        val isInTheStore = globalStateHandlerRepository.get(
            key = IS_IN_THE_STORE,
            clazz = Boolean::class,
        )

        val isDelivery = globalStateHandlerRepository.get(
            key = IS_DELIVERY,
            clazz = Boolean::class,
        )

        return FifthStepValue(
            isTakeAway = isTakeAway ?: false,
            isInTheStore = isInTheStore ?: false,
            isDelivery = isDelivery ?: false,
        )
    }
}
