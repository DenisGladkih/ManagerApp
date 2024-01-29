package net.nomia.pos.ui.onboarding.domain

import com.apollographql.apollo.api.Input
import net.nomia.erp.schema.type.AreaMetricsInput
import net.nomia.erp.schema.type.StoreCharacteristicsInput
import net.nomia.erp.schema.type.StoreCreateInput
import net.nomia.erp.schema.type.StoreSurveyInput
import net.nomia.erp.schema.type.StoreSurveyStoreService
import net.nomia.erp.schema.type.StoreSurveyStoreType
import net.nomia.erp.schema.type.StoreType
import net.nomia.pos.ui.onboarding.model.FifthStepValue
import net.nomia.pos.ui.onboarding.model.FourthStepValue
import net.nomia.pos.ui.onboarding.model.ManagerData
import net.nomia.pos.ui.onboarding.model.ThirdStepValue
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviState
import java.util.UUID

internal fun ManagerData.toCreateStoreInput(
    organizationId: UUID,
    catalogId: UUID,
    currency: String = "RUB",
): StoreCreateInput = StoreCreateInput(
    // TODO: Add phone and geo
    organizationId = organizationId,
    catalogId = catalogId,
    type = StoreType.RESTAURANT,
    currency = currency,
    name = this.firstStepValue.clientName,
    organizationName = Input.optional(this.secondStepValue.nameOfShop),
    address = Input.optional(this.secondStepValue.address),
    characteristics = Input.optional(
        StoreCharacteristicsInput(areaMetrics = this.fourthStepValue.toAreaMetricsInput()),
    ),
    survey = Input.optional(
        StoreSurveyInput(
            previousErp = Input.optional(this.secondStepValue.nameOfShop),
            storeTypes = this.thirdStepValue.toStoreSurveyStoreType(),
            storeServices = this.fifthStepValue.toStoreSurveyStoreService(),
        )
    ),
)

internal fun FourthStepValue.toAreaMetricsInput(): Input<AreaMetricsInput> = Input.optional(
    AreaMetricsInput(
        totalArea = Input.optional(this.totalArea.toIntOrNull()),
        publicArea = Input.optional(this.publicArea.toIntOrNull()),
        kitchenArea = Input.optional(this.kitchenArea.toIntOrNull()),
        seatingCapacity = Input.optional(this.numberOfSeats.toIntOrNull()),
    )
)

internal fun ThirdStepValue.toStoreSurveyStoreType(): List<StoreSurveyStoreType> =
    buildList {
        if (this@toStoreSurveyStoreType.isOther) {
            add(StoreSurveyStoreType.OTHER)
        }

        if (this@toStoreSurveyStoreType.isCooking) {
            add(StoreSurveyStoreType.CULINARY)
        }

        if (this@toStoreSurveyStoreType.isDiningRoom) {
            add(StoreSurveyStoreType.EATERY)
        }

        if (this@toStoreSurveyStoreType.isCafe) {
            add(StoreSurveyStoreType.CAFE)
        }

        if (this@toStoreSurveyStoreType.isCoffeeHouse) {
            add(StoreSurveyStoreType.COFFEE_HOUSE)
        }

        if (this@toStoreSurveyStoreType.isBar) {
            add(StoreSurveyStoreType.BAR)
        }

        if (this@toStoreSurveyStoreType.isRestaurant) {
            add(StoreSurveyStoreType.RESTAURANT)
        }
    }

internal fun FifthStepValue.toStoreSurveyStoreService(): List<StoreSurveyStoreService> =
    buildList {
        if (this@toStoreSurveyStoreService.isInTheStore) {
            add(StoreSurveyStoreService.HERE)
        }

        if (this@toStoreSurveyStoreService.isDelivery) {
            add(StoreSurveyStoreService.DELIVERY)
        }

        if (this@toStoreSurveyStoreService.isTakeAway) {
            add(StoreSurveyStoreService.TAKEAWAY)
        }
    }

internal fun OnboardingMviState.toManagerData(): ManagerData =
    ManagerData(
        firstStepValue = this.firstStepValue,
        secondStepValue = this.secondStepValue,
        thirdStepValue = thirdStepValue,
        fourthStepValue = this.fourthStepValue,
        fifthStepValue = this.fifthStepValue,
    )
