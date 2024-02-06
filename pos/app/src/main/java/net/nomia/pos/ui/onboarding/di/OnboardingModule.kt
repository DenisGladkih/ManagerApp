package net.nomia.pos.ui.onboarding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.nomia.auth.domain.LogoutUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.GetManagerDataUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFifthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFirstStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFourthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveSecondStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveThirdStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.remote.SaveManagerDataUseCase
import net.nomia.pos.ui.onboarding.presentation.mvi.OnboardingMviActor
import net.nomia.pos.ui.onboarding.presentation.mvi.OnboardingMviFeatureFactory

@Module
@InstallIn(ViewModelComponent::class)
class OnboardingModule {
    @Provides
    internal fun provideOnboardingMviActor(
        logoutUseCase: LogoutUseCase,
        getManagerDataUseCase: GetManagerDataUseCase,
        saveFirstStepValueUseCase: SaveFirstStepValueUseCase,
        saveSecondStepValueUseCase: SaveSecondStepValueUseCase,
        saveThirdStepValueUseCase: SaveThirdStepValueUseCase,
        saveFourthStepValueUseCase: SaveFourthStepValueUseCase,
        saveFifthStepValueUseCase: SaveFifthStepValueUseCase,
        saveManagerDataUseCase: SaveManagerDataUseCase,
    ): OnboardingMviActor = OnboardingMviActor(
        logoutUseCase = logoutUseCase,
        getManagerDataUseCase = getManagerDataUseCase,
        saveFirstStepValueUseCase = saveFirstStepValueUseCase,
        saveSecondStepValueUseCase = saveSecondStepValueUseCase,
        saveThirdStepValueUseCase = saveThirdStepValueUseCase,
        saveFourthStepValueUseCase = saveFourthStepValueUseCase,
        saveFifthStepValueUseCase = saveFifthStepValueUseCase,
        saveManagerDataUseCase = saveManagerDataUseCase,
    )

    @Provides
    internal fun provideFeatureFactory(
        actor: OnboardingMviActor
    ): OnboardingMviFeatureFactory = OnboardingMviFeatureFactory(
        actor = actor,
    )
}
