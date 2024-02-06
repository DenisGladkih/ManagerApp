package net.nomia.pos.ui.onboarding.domain.usecase.local

import net.nomia.pos.ui.onboarding.domain.repo.LocalOnboardingRepository
import net.nomia.pos.ui.onboarding.domain.model.ThirdStepValue
import javax.inject.Inject

internal interface SaveThirdStepValueUseCase {
    operator fun invoke(thirdStepValue: ThirdStepValue)
}

internal class SaveThirdStepValueUseCaseImpl @Inject constructor(
    private val localOnboardingRepository: LocalOnboardingRepository,
) : SaveThirdStepValueUseCase {

    override fun invoke(thirdStepValue: ThirdStepValue) {
        localOnboardingRepository.saveThirdStepValue(thirdStepValue = thirdStepValue)
    }
}
