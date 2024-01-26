package net.nomia.pos.ui.onboarding.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.nomia.pos.ui.onboarding.data.OnboardingRepository
import net.nomia.pos.ui.onboarding.domain.OnboardingRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface OnboardingRepositoriesModule {
    @Binds
    fun bindGetFirstStepValueUseCase(
        onboardingRepositoryImpl: OnboardingRepositoryImpl,
    ): OnboardingRepository
}
