package net.nomia.pos.ui.onboarding.domain.usecase.remote

import kotlinx.coroutines.flow.Flow
import net.nomia.pos.ui.onboarding.data.repo.RemoteOnboardingRepository
import net.nomia.pos.ui.onboarding.model.Resource
import javax.inject.Inject

internal interface SaveManagerDataUseCase {
    operator fun invoke(
    ): Flow<Resource<Nothing>>
}

internal class SaveManagerDataUseCaseImpl @Inject constructor(
    private val remoteOnboardingRepository: RemoteOnboardingRepository

) : SaveManagerDataUseCase {
    override fun invoke(): Flow<Resource<Nothing>> =
        remoteOnboardingRepository.saveManagerData(
            auth = null,
            catalogId = null,
            organizationId = null,
        )
}
