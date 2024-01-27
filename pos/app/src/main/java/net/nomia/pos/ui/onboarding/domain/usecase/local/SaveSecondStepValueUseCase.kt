package net.nomia.pos.ui.onboarding.domain.usecase.local

import net.nomia.pos.ui.onboarding.data.repo.LocalOnboardingRepository
import net.nomia.pos.ui.onboarding.model.SecondStepValue
import javax.inject.Inject

internal interface SaveSecondStepValueUseCase {
    operator fun invoke(secondStepValue: SecondStepValue)
}

internal class SaveSecondStepValueUseCaseImpl @Inject constructor(
    private val localOnboardingRepository: LocalOnboardingRepository,
) : SaveSecondStepValueUseCase {
    override fun invoke(secondStepValue: SecondStepValue) {
        localOnboardingRepository.saveSecondStepValue(secondStepValue = secondStepValue)
    }
}
