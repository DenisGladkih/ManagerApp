package net.nomia.pos.ui.onboarding.domain.usecase

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.CLIENT_NAME
import net.nomia.pos.ui.onboarding.PHONE_OR_EMAIL
import net.nomia.pos.ui.onboarding.model.FirstStepValue
import javax.inject.Inject

internal interface SaveFirstStepValueUseCase {
    operator fun invoke(firstStepValue: FirstStepValue)
}

internal class SaveFirstStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : SaveFirstStepValueUseCase {

    override fun invoke(firstStepValue: FirstStepValue) {
        globalStateHandlerRepository.save(
            key = CLIENT_NAME,
            value = firstStepValue.clientName,
        )

        globalStateHandlerRepository.save(
            key = PHONE_OR_EMAIL,
            value = firstStepValue.phoneOrEmail,
        )
    }
}
