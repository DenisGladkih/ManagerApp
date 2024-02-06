package net.nomia.pos.ui.onboarding.domain.repo

import net.nomia.pos.ui.onboarding.domain.model.FifthStepValue
import net.nomia.pos.ui.onboarding.domain.model.FirstStepValue
import net.nomia.pos.ui.onboarding.domain.model.FourthStepValue
import net.nomia.pos.ui.onboarding.domain.model.SecondStepValue
import net.nomia.pos.ui.onboarding.domain.model.ThirdStepValue

internal interface LocalOnboardingRepository {
    fun getFirstStepValue(): FirstStepValue

    fun getSecondStepValue(): SecondStepValue

    fun getThirdStepValue(): ThirdStepValue

    fun getFourthStepValue(): FourthStepValue

    fun getFifthStepValue(): FifthStepValue

    fun saveFirstStepValue(firstStepValue: FirstStepValue)

    fun saveSecondStepValue(secondStepValue: SecondStepValue)

    fun saveThirdStepValue(thirdStepValue: ThirdStepValue)

    fun saveFourthStepValue(fourthStepValue: FourthStepValue)

    fun saveFifthStepValue(fifthStepValue: FifthStepValue)
}
