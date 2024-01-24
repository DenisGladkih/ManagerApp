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

internal interface GetThirdStepValueUseCase {
    operator fun invoke(): ThirdStepValue
}

internal class GetThirdStepValueUseCaseImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : GetThirdStepValueUseCase {
    override fun invoke(): ThirdStepValue {
        val isRestaurant = globalStateHandlerRepository.get(
            key = IS_RESTAURANT,
            clazz = Boolean::class,
        )

        val isBar = globalStateHandlerRepository.get(
            key = IS_BAR,
            clazz = Boolean::class,
        )

        val isCafe = globalStateHandlerRepository.get(
            key = IS_CAFE,
            clazz = Boolean::class,
        )

        val isDiningRoom = globalStateHandlerRepository.get(
            key = IS_DINING_ROOM,
            clazz = Boolean::class,
        )

        val isCoffeeHouse = globalStateHandlerRepository.get(
            key = IS_COFFEE_HOUSE,
            clazz = Boolean::class,
        )

        val isCooking = globalStateHandlerRepository.get(
            key = IS_COOKING,
            clazz = Boolean::class,
        )

        val isOther = globalStateHandlerRepository.get(
            key = IS_OTHER,
            clazz = Boolean::class,
        )

        return ThirdStepValue(
            isRestaurant = isRestaurant ?: false,
            isBar = isBar ?: false,
            isCafe = isCafe ?: false,
            isDiningRoom = isDiningRoom ?: false,
            isCoffeeHouse = isCoffeeHouse ?: false,
            isCooking = isCooking ?: false,
            isOther = isOther ?: false,
        )
    }
}
