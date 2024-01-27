package net.nomia.pos.ui.onboarding.data.repo

import net.nomia.pos.ui.onboarding.model.FifthStepValue
import net.nomia.pos.ui.onboarding.model.FirstStepValue
import net.nomia.pos.ui.onboarding.model.FourthStepValue
import net.nomia.pos.ui.onboarding.model.SecondStepValue
import net.nomia.pos.ui.onboarding.model.ThirdStepValue

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
