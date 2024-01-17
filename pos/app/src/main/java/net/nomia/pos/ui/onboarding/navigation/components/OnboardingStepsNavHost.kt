package net.nomia.pos.ui.onboarding.navigation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.nomia.pos.ui.onboarding.OnboardingFifthStepScreen
import net.nomia.pos.ui.onboarding.OnboardingFirstStepScreen
import net.nomia.pos.ui.onboarding.OnboardingFourthStepScreen
import net.nomia.pos.ui.onboarding.OnboardingSecondStepScreen
import net.nomia.pos.ui.onboarding.OnboardingSixthStepScreen
import net.nomia.pos.ui.onboarding.OnboardingThirdStepScreen

@Composable
internal fun OnboardingStepsNavHost(
    navHostController: NavHostController,
    isTablet:Boolean,
) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingFirstStepDestination.route,
    ) {
        composable(
            route = OnboardingFirstStepDestination.route,
            arguments = OnboardingFirstStepDestination.argumentList
        ) {
            OnboardingFirstStepScreen(isTablet =isTablet)
        }

        composable(
            route = OnboardingSecondStepDestination.route,
            arguments = OnboardingSecondStepDestination.argumentList
        ) {
            OnboardingSecondStepScreen(isTablet =isTablet)
        }

        composable(
            route = OnboardingThirdStepDestination.route,
            arguments = OnboardingThirdStepDestination.argumentList
        ) {
            OnboardingThirdStepScreen(isTablet = isTablet)
        }

        composable(
            route = OnboardingFourthStepDestination.route,
            arguments = OnboardingFourthStepDestination.argumentList
        ) {
            OnboardingFourthStepScreen(isTablet =isTablet)
        }

        composable(
            route = OnboardingFifthStepDestination.route,
            arguments = OnboardingFifthStepDestination.argumentList
        ) {
            OnboardingFifthStepScreen(isTablet =isTablet)
        }

        composable(
            route = OnboardingSixthStepDestination.route,
            arguments = OnboardingSixthStepDestination.argumentList
        ) {
            OnboardingSixthStepScreen(isTablet =isTablet)
        }
    }
}
