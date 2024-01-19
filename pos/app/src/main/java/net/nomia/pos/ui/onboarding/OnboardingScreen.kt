package net.nomia.pos.ui.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import net.nomia.common.ui.composable.NomiaScrollableScaffold
import net.nomia.common.ui.theme.appResources
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.components.OnboardingFooter
import net.nomia.pos.ui.onboarding.components.OnboardingSegmentsBar
import net.nomia.pos.ui.onboarding.components.onboardingContentWidth
import net.nomia.pos.ui.onboarding.composable.OnboardingFifthStep
import net.nomia.pos.ui.onboarding.composable.OnboardingFirstStep
import net.nomia.pos.ui.onboarding.composable.OnboardingFourthStep
import net.nomia.pos.ui.onboarding.composable.OnboardingSecondStep
import net.nomia.pos.ui.onboarding.composable.OnboardingSixthStep
import net.nomia.pos.ui.onboarding.composable.OnboardingThirdStep
import net.nomia.pos.ui.onboarding.model.OnboardingStep
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviAction

@Composable
internal fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

    BackHandler {
        viewModel.acceptAction(action=OnboardingMviAction.Back)
    }

    val skipButtonVisible = state.skipButtonVisible
    val backButtonVisible = state.backButtonVisible
    val footerVisible = state.footerVisible
    val onboardingStep = state.onboardingStep

    NomiaScrollableScaffold(
        title = {
            Icon(
                painter = painterResource(id = MaterialTheme.appResources.textLogoResId),
                tint = Color.Unspecified,
                contentDescription = null,
            )
        },
        actions = {
            AnimatedVisibility(
                visible = skipButtonVisible,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                TextButton(
                    onClick = {
                              viewModel.acceptAction(action =OnboardingMviAction.Skip )
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = Color.Transparent,
                    ),
                ) {
                    Text(text = stringResource(id = R.string.skip_action))
                }
            }
        },
        footer = {
            AnimatedVisibility(
                visible = footerVisible,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                OnboardingFooter(
                    backButtonVisible = backButtonVisible,
                    onBackClick = {
                        viewModel.acceptAction(OnboardingMviAction.Back)
                    },
                    onContinueClick = {
                        viewModel.acceptAction(action = OnboardingMviAction.Continue)
                    },
                )
            }
        },
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacers.medium),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            OnboardingSegmentsBar(onboardingStep = onboardingStep)

            val isTablet = booleanResource(id = R.bool.isTablet)
            if (isTablet) {
                Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.extraLarge))
            } else {
                Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))
            }

            Crossfade(targetState = onboardingStep, label = "Onboarding content") { step ->
                Column(modifier = Modifier.onboardingContentWidth()) {
                    when (step) {
                        OnboardingStep.FIRST -> OnboardingFirstStep()
                        OnboardingStep.SECOND -> OnboardingSecondStep()
                        OnboardingStep.THIRD -> OnboardingThirdStep()
                        OnboardingStep.FOURTH -> OnboardingFourthStep()
                        OnboardingStep.FIFTH -> OnboardingFifthStep()
                        OnboardingStep.SIXTH -> OnboardingSixthStep()
                    }
                }
            }
        }
    }
}
