package net.nomia.pos.ui.onboarding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.nomia.auth.domain.LogoutUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetFifthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetFirstStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetFourthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetSecondStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetThirdStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFifthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFirstStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFourthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveManagerDataUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveSecondStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveThirdStepValueUseCase
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviActor
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviFeatureFactory

@Module
@InstallIn(ViewModelComponent::class)
class OnboardingModule {
    @Provides
    internal fun provideOnboardingMviActor(
        logoutUseCase: LogoutUseCase,
        getFirstStepValueUseCase: GetFirstStepValueUseCase,
        saveFirstStepValueUseCase: SaveFirstStepValueUseCase,
        saveSecondStepValueUseCase: SaveSecondStepValueUseCase,
        getSecondStepValueUseCase: GetSecondStepValueUseCase,
        getThirdStepValueUseCase: GetThirdStepValueUseCase,
        saveThirdStepValueUseCase: SaveThirdStepValueUseCase,
        getFourthStepValueUseCase: GetFourthStepValueUseCase,
        saveFourthStepValueUseCase: SaveFourthStepValueUseCase,
        getFifthStepValueUseCase: GetFifthStepValueUseCase,
        saveFifthStepValueUseCase: SaveFifthStepValueUseCase,
        saveManagerDataUseCase: SaveManagerDataUseCase,
    ): OnboardingMviActor = OnboardingMviActor(
        logoutUseCase = logoutUseCase,
        getFirstStepValueUseCase = getFirstStepValueUseCase,
        saveFirstStepValueUseCase = saveFirstStepValueUseCase,
        getSecondStepValueUseCase = getSecondStepValueUseCase,
        saveSecondStepValueUseCase = saveSecondStepValueUseCase,
        getThirdStepValueUseCase = getThirdStepValueUseCase,
        saveThirdStepValueUseCase = saveThirdStepValueUseCase,
        getFourthStepValueUseCase = getFourthStepValueUseCase,
        saveFourthStepValueUseCase = saveFourthStepValueUseCase,
        getFifthStepValueUseCase = getFifthStepValueUseCase,
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
