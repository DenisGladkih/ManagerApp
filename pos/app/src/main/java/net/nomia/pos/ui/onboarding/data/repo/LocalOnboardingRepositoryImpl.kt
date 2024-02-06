package net.nomia.pos.ui.onboarding.data.repo

import net.nomia.core.common.state_handler.di.GlobalStateHandler
import net.nomia.core.common.state_handler.domain.repository.StateHandlerRepository
import net.nomia.pos.ui.onboarding.domain.repo.LocalOnboardingRepository
import net.nomia.pos.ui.onboarding.domain.ADDRESS
import net.nomia.pos.ui.onboarding.domain.AUTOMATION_SYSTEM
import net.nomia.pos.ui.onboarding.domain.CLIENT_NAME
import net.nomia.pos.ui.onboarding.domain.COUNTRY_AND_CITY
import net.nomia.pos.ui.onboarding.domain.IS_BAR
import net.nomia.pos.ui.onboarding.domain.IS_CAFE
import net.nomia.pos.ui.onboarding.domain.IS_COFFEE_HOUSE
import net.nomia.pos.ui.onboarding.domain.IS_COOKING
import net.nomia.pos.ui.onboarding.domain.IS_DELIVERY
import net.nomia.pos.ui.onboarding.domain.IS_DINING_ROOM
import net.nomia.pos.ui.onboarding.domain.IS_IN_THE_STORE
import net.nomia.pos.ui.onboarding.domain.IS_NEW_PLACE
import net.nomia.pos.ui.onboarding.domain.IS_OTHER
import net.nomia.pos.ui.onboarding.domain.IS_RESTAURANT
import net.nomia.pos.ui.onboarding.domain.IS_TAKE_AWAY
import net.nomia.pos.ui.onboarding.domain.KITCHEN_AREA
import net.nomia.pos.ui.onboarding.domain.NAME_OF_SHOP
import net.nomia.pos.ui.onboarding.domain.NUMBER_OF_SEATS
import net.nomia.pos.ui.onboarding.domain.PHONE_OR_EMAIL
import net.nomia.pos.ui.onboarding.domain.PUBLIC_AREA
import net.nomia.pos.ui.onboarding.domain.TOTAL_AREA
import net.nomia.pos.ui.onboarding.domain.model.FifthStepValue
import net.nomia.pos.ui.onboarding.domain.model.FirstStepValue
import net.nomia.pos.ui.onboarding.domain.model.FourthStepValue
import net.nomia.pos.ui.onboarding.domain.model.SecondStepValue
import net.nomia.pos.ui.onboarding.domain.model.ThirdStepValue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LocalOnboardingRepositoryImpl @Inject constructor(
    @GlobalStateHandler private val globalStateHandlerRepository: StateHandlerRepository,
) : LocalOnboardingRepository {
    override fun getFirstStepValue(): FirstStepValue {
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

    override fun getSecondStepValue(): SecondStepValue {
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

    override fun getThirdStepValue(): ThirdStepValue {
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

    override fun getFourthStepValue(): FourthStepValue {
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

    override fun getFifthStepValue(): FifthStepValue {
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

    override fun saveFirstStepValue(firstStepValue: FirstStepValue) {
        globalStateHandlerRepository.save(
            key = CLIENT_NAME,
            value = firstStepValue.clientName,
        )

        globalStateHandlerRepository.save(
            key = PHONE_OR_EMAIL,
            value = firstStepValue.phoneOrEmail,
        )
    }

    override fun saveSecondStepValue(secondStepValue: SecondStepValue) {
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

    override fun saveThirdStepValue(thirdStepValue: ThirdStepValue) {
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

    override fun saveFourthStepValue(fourthStepValue: FourthStepValue) {
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

    override fun saveFifthStepValue(fifthStepValue: FifthStepValue) {
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

