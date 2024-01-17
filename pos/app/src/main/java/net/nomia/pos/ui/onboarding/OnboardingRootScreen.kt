package net.nomia.pos.ui.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.nomia.common.ui.composable.NomiaFilledButton
import net.nomia.common.ui.composable.NomiaFooter
import net.nomia.common.ui.composable.NomiaScrollableScaffold
import net.nomia.common.ui.theme.appResources
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.components.onboardingContentWidth
import net.nomia.pos.ui.onboarding.navigation.components.OnboardingFifthStepDestination
import net.nomia.pos.ui.onboarding.navigation.components.OnboardingFirstStepDestination
import net.nomia.pos.ui.onboarding.navigation.components.OnboardingFourthStepDestination
import net.nomia.pos.ui.onboarding.navigation.components.OnboardingSecondStepDestination
import net.nomia.pos.ui.onboarding.navigation.components.OnboardingSixthStepDestination
import net.nomia.pos.ui.onboarding.navigation.components.OnboardingStepsNavHost
import net.nomia.pos.ui.onboarding.navigation.components.OnboardingThirdStepDestination
import net.nomia.pos.ui.onboarding.navigation.components.route

@Composable
internal fun OnboardingRootScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val navHostController: NavHostController = rememberNavController()
    val isTablet = booleanResource(id = R.bool.isTablet)
    val state = viewModel.state
    val coloredSegmentsCount = state.coloredSegmentsCount
    val skipButtonVisible = state.skipButtonVisible
    val backButtonVisible = state.backButtonVisible
    val footerVisible = state.footerVisible

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
                    onClick = navHostController::navigateOnNextOnboardingStep,
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
                    isTablet = isTablet,
                    backButtonVisible = backButtonVisible,
                    onBackClick = navHostController::navigateUp,
                    onContinueClick = navHostController::navigateOnNextOnboardingStep,
                )
            }
        },
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacers.medium),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            OnboardingSegmentsBar(
                coloredSegmentsCount = coloredSegmentsCount,
            )

            Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))

            if (isTablet) {
                Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))
            }

            OnboardingStepsNavHost(
                isTablet = isTablet,
                navHostController = navHostController,
            )
        }
    }
}

@Composable
private fun OnboardingSegmentsBar(
    coloredSegmentsCount: Int,
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.extraSmall)
) {
    val totalSegmentsCount = 6
    repeat(times = totalSegmentsCount) { segment ->
        val animatedColor by animateColorAsState(
            if (segment >= coloredSegmentsCount) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.primary
            },
            label = "color"
        )

        Spacer(
            modifier = Modifier
                .height(height = MaterialTheme.spacers.extraSmall)
                .weight(weight = 1f)
                .background(color = animatedColor),
        )
    }
}

@Composable
private fun ColumnScope.OnboardingFooter(
    isTablet: Boolean,
    backButtonVisible: Boolean,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
) = NomiaFooter {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.small),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .onboardingContentWidth(isTablet = isTablet),
        ) {
            AnimatedVisibility(visible = backButtonVisible) {
                if (isTablet) {
                    OutlinedButton(
                        onClick = onBackClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ),
                        modifier = Modifier.width(width = 91.dp)
                    ) {
                        Text(text = stringResource(id = R.string.back_action))
                    }
                } else {
                    OutlinedIconButton(
                        onClick = onBackClick,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier.size(size = 40.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_24),
                            contentDescription = null,
                        )
                    }
                }
            }

            NomiaFilledButton(
                onClick = onContinueClick,
                modifier = Modifier.weight(weight = 1f),
            ) {
                Text(text = stringResource(id = R.string.continue_action))
            }
        }
    }
}

private fun NavHostController.navigateOnNextOnboardingStep() {
    val navHostController = this
    val targetRoute = when (this.currentDestination?.route) {
        OnboardingFirstStepDestination.route -> OnboardingSecondStepDestination.route
        OnboardingSecondStepDestination.route -> OnboardingThirdStepDestination.route
        OnboardingThirdStepDestination.route -> OnboardingFourthStepDestination.route
        OnboardingFourthStepDestination.route -> OnboardingFifthStepDestination.route
        OnboardingFifthStepDestination.route -> OnboardingSixthStepDestination.route
        else -> return
    }
    navHostController.navigate(route = targetRoute) {
        launchSingleTop = true
    }
}
