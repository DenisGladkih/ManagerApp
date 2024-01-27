package net.nomia.pos.ui.onboarding.domain.usecase.local

import net.nomia.pos.ui.onboarding.data.repo.LocalOnboardingRepository
import net.nomia.pos.ui.onboarding.model.ManagerData
import javax.inject.Inject

internal interface GetManagerDataUseCase {
    operator fun invoke(): ManagerData
}

internal class GetManagerDataUseCaseImp @Inject constructor(
    private val localOnboardingRepository: LocalOnboardingRepository,
) : GetManagerDataUseCase {
    override fun invoke(): ManagerData {
        return ManagerData(
            firstStepValue = localOnboardingRepository.getFirstStepValue(),
            secondStepValue = localOnboardingRepository.getSecondStepValue(),
            thirdStepValue = localOnboardingRepository.getThirdStepValue(),
            fourthStepValue = localOnboardingRepository.getFourthStepValue(),
            fifthStepValue = localOnboardingRepository.getFifthStepValue()

        )
    }
}
