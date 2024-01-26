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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import net.nomia.pos.ui.onboarding.components.OnboardingFifthStep
import net.nomia.pos.ui.onboarding.components.OnboardingFirstStep
import net.nomia.pos.ui.onboarding.components.OnboardingFooter
import net.nomia.pos.ui.onboarding.components.OnboardingFourthStep
import net.nomia.pos.ui.onboarding.components.OnboardingSecondStep
import net.nomia.pos.ui.onboarding.components.OnboardingSegmentsBar
import net.nomia.pos.ui.onboarding.components.OnboardingSixthStep
import net.nomia.pos.ui.onboarding.components.OnboardingThirdStep
import net.nomia.pos.ui.onboarding.composable.onboardingContentWidth
import net.nomia.pos.ui.onboarding.model.OnboardingStep
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviAction
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviEvent

@Composable
internal fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    BackHandler {
        viewModel.acceptAction(action = OnboardingMviAction.MoveBack)
    }
    val snackbarHostState = remember { SnackbarHostState() }

    val defaultErrorMessage = stringResource(id = R.string.something_went_wrong)

    val state by viewModel.collectAsState(
        launchActions = listOf(
            OnboardingMviAction.FetchManagerData,
        )
    ) { event ->
        if (event is OnboardingMviEvent.OnShowError) {
            val message = event.error
            snackbarHostState.showSnackbar(message = message ?: defaultErrorMessage)
        }
    }

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
                visible = state.skipButtonVisible,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                TextButton(
                    onClick = {
                        viewModel.acceptAction(action = OnboardingMviAction.SkipStep)
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
                visible = state.footerVisible,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                OnboardingFooter(
                    continueButtonState = state.continueButtonState,
                    backButtonVisible = state.backButtonVisible,
                    onBackClick = {
                        viewModel.acceptAction(OnboardingMviAction.MoveBack)
                    },
                    onContinueClick = {
                        viewModel.acceptAction(action = OnboardingMviAction.MoveForward)
                    },
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },

        contentPadding = PaddingValues(horizontal = MaterialTheme.spacers.medium),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            OnboardingSegmentsBar(
                segmentsCount = state.topBarSegmentsCount,
                coloredSegments = state.coloredSegments,
            )

            val isTablet = booleanResource(id = R.bool.isTablet)

            if (isTablet) {
                Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.extraLarge))
            } else {
                Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))
            }

            Crossfade(targetState = state.onboardingStep, label = "Onboarding content") { step ->
                Column(modifier = Modifier.onboardingContentWidth()) {
                    when (step) {
                        OnboardingStep.FIRST -> OnboardingFirstStep(
                            onAction = viewModel::acceptAction,
                            state = state.firstStepValue,
                        )

                        OnboardingStep.SECOND -> OnboardingSecondStep(
                            onAction = viewModel::acceptAction,
                            state = state.secondStepValue,
                        )

                        OnboardingStep.THIRD -> OnboardingThirdStep(
                            onAction = viewModel::acceptAction,
                            state = state.thirdStepValue,
                        )

                        OnboardingStep.FOURTH -> OnboardingFourthStep(
                            onAction = viewModel::acceptAction,
                            state = state.fourthStepValue,
                        )

                        OnboardingStep.FIFTH -> OnboardingFifthStep(
                            onAction = viewModel::acceptAction,
                            state = state.fifthStepValue,
                        )

                        OnboardingStep.SIXTH -> OnboardingSixthStep()
                    }
                }
            }
        }
    }
}
