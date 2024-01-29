package net.nomia.pos.ui.onboarding.domain.usecase.remote

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import net.nomia.main.domain.PrincipalRepository
import net.nomia.pos.ui.onboarding.data.repo.RemoteOnboardingRepository
import net.nomia.pos.ui.onboarding.model.ManagerData
import net.nomia.pos.ui.onboarding.model.Resource
import javax.inject.Inject

internal interface SaveManagerDataUseCase {
    operator fun invoke(
        managerData: ManagerData,
    ): Flow<Resource<Nothing>>
}

internal class SaveManagerDataUseCaseImpl @Inject constructor(
    principalRepository: PrincipalRepository,
    private val remoteOnboardingRepository: RemoteOnboardingRepository,
) : SaveManagerDataUseCase {

    private val authFlow = principalRepository.getAuthFlow()
        .stateIn(
            scope = CoroutineScope(Dispatchers.IO),
            started = SharingStarted.Eagerly,
            initialValue = null,
        )

    override fun invoke(
        managerData: ManagerData,
    ): Flow<Resource<Nothing>> = remoteOnboardingRepository
        .saveManagerData(
            auth = authFlow.value,
            managerData = managerData,
        )
}
