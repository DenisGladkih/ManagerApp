package net.nomia.pos.ui.onboarding.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.nomia.pos.ui.onboarding.domain.usecase.GetFifthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetFifthStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.GetFirstStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetFirstStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.GetFourthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetFourthStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.GetSecondStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetSecondStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.GetThirdStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.GetThirdStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFifthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFifthStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFirstStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFirstStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFourthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveFourthStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.SaveSecondStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveSecondStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.SaveThirdStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveThirdStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.SaveManagerDataUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.SaveManagerDataUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface OnboardingUseCaseModule {
    @Binds
    fun bindGetFirstStepValueUseCase(
        getFirstStepValueUseCase: GetFirstStepValueUseCaseImpl,
    ): GetFirstStepValueUseCase

    @Binds
    fun bindSaveFirstStepValueUseCaseImpl(
        saveFirstStepValueImpl: SaveFirstStepValueUseCaseImpl,
    ): SaveFirstStepValueUseCase

    @Binds
    fun bindGetSecondStepValueUseCase(
        getSecondStepValueUseCaseImpl: GetSecondStepValueUseCaseImpl,
    ): GetSecondStepValueUseCase

    @Binds
    fun bindSaveSecondStepValueUseCase(
        saveSecondStepValueUseCaseImpl: SaveSecondStepValueUseCaseImpl,
    ): SaveSecondStepValueUseCase

    @Binds
    fun bindGetThirdStepValueUseCase(
        getThirdStepValueUseCase: GetThirdStepValueUseCaseImpl,
    ): GetThirdStepValueUseCase

    @Binds
    fun bindSaveThirdStepValueUseCase(
        saveThirdStepValueUseCase: SaveThirdStepValueUseCaseImpl,
    ): SaveThirdStepValueUseCase

    @Binds
    fun bindGetFourthStepValueUseCase(
        getFourthStepValueUseCase: GetFourthStepValueUseCaseImpl,
    ): GetFourthStepValueUseCase

    @Binds
    fun bindSaveFourthStepValueUseCase(
        saveFourthStepValueUseCase: SaveFourthStepValueUseCaseImpl,
    ): SaveFourthStepValueUseCase

    @Binds
    fun bindGetFifthStepValueUseCase(
        getFifthStepValueUseCase: GetFifthStepValueUseCaseImpl,
    ): GetFifthStepValueUseCase

    @Binds
    fun bindSaveFifthStepValueUseCase(
        saveFifthStepValueUseCase: SaveFifthStepValueUseCaseImpl,
    ): SaveFifthStepValueUseCase

    @Binds
    fun bindSaveManagerDataUseCase(
        saveManagerDataUseCase: SaveManagerDataUseCaseImpl,
    ): SaveManagerDataUseCase
}
