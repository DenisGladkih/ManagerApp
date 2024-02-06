package net.nomia.pos.ui.onboarding.domain.usecase.local

import net.nomia.pos.ui.onboarding.domain.repo.LocalOnboardingRepository
import net.nomia.pos.ui.onboarding.domain.model.FirstStepValue
import javax.inject.Inject

internal interface SaveFirstStepValueUseCase {
    operator fun invoke(firstStepValue: FirstStepValue)
}

internal class SaveFirstStepValueUseCaseImpl @Inject constructor(
    private val localOnboardingRepository: LocalOnboardingRepository,
) : SaveFirstStepValueUseCase {

    override fun invoke(firstStepValue: FirstStepValue) {
        localOnboardingRepository.saveFirstStepValue(firstStepValue = firstStepValue)
    }
}
