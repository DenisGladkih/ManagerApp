package net.nomia.pos.ui.onboarding.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.nomia.pos.ui.onboarding.data.repo.LocalOnboardingRepository
import net.nomia.pos.ui.onboarding.data.repo.RemoteOnboardingRepository
import net.nomia.pos.ui.onboarding.domain.repo.LocalOnboardingRepositoryImpl
import net.nomia.pos.ui.onboarding.domain.repo.RemoteOnboardingRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface OnboardingRepositoriesModule {
    @Binds
    fun bindRemoteOnboardingRepository(
        remoteOnboardingRepositoryImpl: RemoteOnboardingRepositoryImpl,
    ): RemoteOnboardingRepository

    @Binds
    fun bindLocalOnboardingRepository(
        localOnboardingRepositoryImpl: LocalOnboardingRepositoryImpl,
    ): LocalOnboardingRepository
}
