package net.nomia.pos.ui.onboarding.domain.usecase

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.ADDRESS
import net.nomia.pos.ui.onboarding.AUTOMATION_SYSTEM
import net.nomia.pos.ui.onboarding.COUNTRY_AND_CITY
import net.nomia.pos.ui.onboarding.IS_NEW_PLACE
import net.nomia.pos.ui.onboarding.NAME_OF_SHOP
import net.nomia.pos.ui.onboarding.model.SecondStepValue
import javax.inject.Inject

internal interface SaveSecondStepValueUseCase {
    operator fun invoke(secondStepValue: SecondStepValue)
}

internal class SaveSecondStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : SaveSecondStepValueUseCase {
    override fun invoke(secondStepValue: SecondStepValue) {
        globalStateHandlerRepository.save(
            key = NAME_OF_SHOP,
            value = secondStepValue.nameOfShop,
        )

        globalStateHandlerRepository.save(
            key = COUNTRY_AND_CITY,
            value = secondStepValue.countryAndCity,
        )

        globalStateHandlerRepository.save(
            key = ADDRESS,
            value = secondStepValue.address,
        )

        globalStateHandlerRepository.save(
            key = IS_NEW_PLACE,
            value = secondStepValue.isNewPlace,
        )

        globalStateHandlerRepository.save(
            key = AUTOMATION_SYSTEM,
            value = secondStepValue.automationSystem,
        )
    }
}
