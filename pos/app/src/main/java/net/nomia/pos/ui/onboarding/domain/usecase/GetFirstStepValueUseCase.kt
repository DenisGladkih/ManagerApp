package net.nomia.pos.ui.onboarding.domain.usecase

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.CLIENT_NAME
import net.nomia.pos.ui.onboarding.PHONE_OR_EMAIL
import net.nomia.pos.ui.onboarding.model.FirstStepValue
import javax.inject.Inject

internal interface GetFirstStepValueUseCase {
    operator fun invoke(): FirstStepValue
}

internal class GetFirstStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : GetFirstStepValueUseCase {
    override fun invoke(): FirstStepValue {
        val clientName = globalStateHandlerRepository.get(
            key = CLIENT_NAME,
            clazz = String::class,
        )

        val phoneOrEmail = globalStateHandlerRepository.get(
            key = PHONE_OR_EMAIL,
            clazz = String::class,
        )

        return FirstStepValue(
            clientName = clientName ?: "",
            phoneOrEmail = phoneOrEmail ?: "",
        )
    }
}
