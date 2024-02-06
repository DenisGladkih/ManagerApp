package net.nomia.pos.ui.onboarding.domain.usecase.local

import net.nomia.pos.ui.onboarding.domain.repo.LocalOnboardingRepository
import net.nomia.pos.ui.onboarding.domain.model.FifthStepValue
import javax.inject.Inject

internal interface SaveFifthStepValueUseCase {
    operator fun invoke(fifthStepValue: FifthStepValue)
}

internal class SaveFifthStepValueUseCaseImpl @Inject constructor(
    private val localOnboardingRepository: LocalOnboardingRepository,
) : SaveFifthStepValueUseCase {
    override fun invoke(fifthStepValue: FifthStepValue) {
        localOnboardingRepository.saveFifthStepValue(fifthStepValue = fifthStepValue)
    }
}
