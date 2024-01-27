package net.nomia.pos.ui.onboarding.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFifthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFifthStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFirstStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFirstStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFourthStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveFourthStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveSecondStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveSecondStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveThirdStepValueUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.SaveThirdStepValueUseCaseImpl
import net.nomia.pos.ui.onboarding.domain.usecase.local.GetManagerDataUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.local.GetManagerDataUseCaseImp
import net.nomia.pos.ui.onboarding.domain.usecase.remote.SaveManagerDataUseCase
import net.nomia.pos.ui.onboarding.domain.usecase.remote.SaveManagerDataUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface OnboardingUseCaseModule {
    @Binds
    fun bindGetManagerDataUseCase(
        getManagerDataUseCase: GetManagerDataUseCaseImp,
    ): GetManagerDataUseCase

    @Binds
    fun bindSaveFirstStepValueUseCase(
        saveFirstStepValueImpl: SaveFirstStepValueUseCaseImpl,
    ): SaveFirstStepValueUseCase

    @Binds
    fun bindSaveSecondStepValueUseCase(
        saveSecondStepValueUseCaseImpl: SaveSecondStepValueUseCaseImpl,
    ): SaveSecondStepValueUseCase

    @Binds
    fun bindSaveThirdStepValueUseCase(
        saveThirdStepValueUseCase: SaveThirdStepValueUseCaseImpl,
    ): SaveThirdStepValueUseCase


    @Binds
    fun bindSaveFourthStepValueUseCase(
        saveFourthStepValueUseCase: SaveFourthStepValueUseCaseImpl,
    ): SaveFourthStepValueUseCase

    @Binds
    fun bindSaveFifthStepValueUseCase(
        saveFifthStepValueUseCase: SaveFifthStepValueUseCaseImpl,
    ): SaveFifthStepValueUseCase

    @Binds
    fun bindSaveManagerDataUseCase(
        saveManagerDataUseCase: SaveManagerDataUseCaseImpl,
    ): SaveManagerDataUseCase
}
