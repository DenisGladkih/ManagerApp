package net.nomia.pos.ui.onboarding.domain.usecase

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.IS_BAR
import net.nomia.pos.ui.onboarding.IS_CAFE
import net.nomia.pos.ui.onboarding.IS_COFFEE_HOUSE
import net.nomia.pos.ui.onboarding.IS_COOKING
import net.nomia.pos.ui.onboarding.IS_DINING_ROOM
import net.nomia.pos.ui.onboarding.IS_OTHER
import net.nomia.pos.ui.onboarding.IS_RESTAURANT
import net.nomia.pos.ui.onboarding.model.ThirdStepValue
import javax.inject.Inject

internal interface SaveThirdStepValueUseCase {
    operator fun invoke(thirdStepValue: ThirdStepValue)
}

internal class SaveThirdStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : SaveThirdStepValueUseCase {
    override fun invoke(thirdStepValue: ThirdStepValue) {
        globalStateHandlerRepository.save(
            key = IS_RESTAURANT,
            value = thirdStepValue.isRestaurant,
        )

        globalStateHandlerRepository.save(
            key = IS_BAR,
            value = thirdStepValue.isBar,
        )

        globalStateHandlerRepository.save(
            key = IS_CAFE,
            value = thirdStepValue.isCafe,
        )

        globalStateHandlerRepository.save(
            key = IS_DINING_ROOM,
            value = thirdStepValue.isDiningRoom,
        )

        globalStateHandlerRepository.save(
            key = IS_COFFEE_HOUSE,
            value = thirdStepValue.isCoffeeHouse,
        )

        globalStateHandlerRepository.save(
            key = IS_COOKING,
            value = thirdStepValue.isCooking,
        )

        globalStateHandlerRepository.save(
            key = IS_OTHER,
            value = thirdStepValue.isOther,
        )
    }
}
