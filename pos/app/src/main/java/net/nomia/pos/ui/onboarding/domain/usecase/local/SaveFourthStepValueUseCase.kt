package net.nomia.pos.ui.onboarding.domain.usecase.local

import net.nomia.pos.ui.onboarding.domain.repo.LocalOnboardingRepository
import net.nomia.pos.ui.onboarding.domain.model.FourthStepValue
import javax.inject.Inject

internal interface SaveFourthStepValueUseCase {
    operator fun invoke(fourthStepValue: FourthStepValue)
}

internal class SaveFourthStepValueUseCaseImpl @Inject constructor(
    private val localOnboardingRepository: LocalOnboardingRepository,
) : SaveFourthStepValueUseCase {

    override fun invoke(fourthStepValue: FourthStepValue) {
        localOnboardingRepository.saveFourthStepValue(fourthStepValue = fourthStepValue)
    }
}
