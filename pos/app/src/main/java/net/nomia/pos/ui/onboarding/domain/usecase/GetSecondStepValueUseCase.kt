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

internal interface GetSecondStepValueUseCase {
    operator fun invoke(): SecondStepValue
}

internal class GetSecondStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : GetSecondStepValueUseCase {
    override fun invoke(): SecondStepValue {
        val nameOfShop = globalStateHandlerRepository.get(
            key = NAME_OF_SHOP,
            clazz = String::class,
        )

        val countryAndCity = globalStateHandlerRepository.get(
            key = COUNTRY_AND_CITY,
            clazz = String::class,
        )

        val address = globalStateHandlerRepository.get(
            key = ADDRESS,
            clazz = String::class,
        )

        val isNewPlace = globalStateHandlerRepository.get(
            key = IS_NEW_PLACE,
            clazz = Boolean::class,
        )

        val automationSystem = globalStateHandlerRepository.get(
            key = AUTOMATION_SYSTEM,
            clazz = String::class,
        )

        return SecondStepValue(
            nameOfShop = nameOfShop ?: "",
            countryAndCity = countryAndCity ?: "",
            address = address ?: "",
            isNewPlace = isNewPlace ?: false,
            automationSystem = automationSystem ?: "",
        )
    }
}
