package net.nomia.pos.ui.onboarding.domain.usecase

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.IS_DELIVERY
import net.nomia.pos.ui.onboarding.IS_IN_THE_STORE
import net.nomia.pos.ui.onboarding.IS_TAKE_AWAY
import net.nomia.pos.ui.onboarding.model.FifthStepValue
import javax.inject.Inject

internal interface SaveFifthStepValueUseCase {
    operator fun invoke(fifthStepValue: FifthStepValue)
}

internal class SaveFifthStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : SaveFifthStepValueUseCase {
    override fun invoke(fifthStepValue: FifthStepValue) {
        globalStateHandlerRepository.save(
            key = IS_TAKE_AWAY,
            value = fifthStepValue.isTakeAway,
        )

        globalStateHandlerRepository.save(
            key = IS_IN_THE_STORE,
            value = fifthStepValue.isInTheStore,
        )

        globalStateHandlerRepository.save(
            key = IS_DELIVERY,
            value = fifthStepValue.isDelivery,
        )
    }
}
