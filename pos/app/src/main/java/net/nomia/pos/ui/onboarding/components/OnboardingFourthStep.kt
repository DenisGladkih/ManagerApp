package net.nomia.pos.ui.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.model.FourthStepValue
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviAction

@Composable
internal fun OnboardingFourthStep(
    state: FourthStepValue,
    onAction: (OnboardingMviAction) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.large),
    ) {
        Text(
            text = stringResource(id = R.string.indicate_the_size),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        OnboardingDigitTextField(
            value = state.totalArea,
            placeholder = stringResource(id = R.string.total_area),
            onValueChange = { newValue ->
                onAction(OnboardingMviAction.SetTotalArea(totalArea = newValue))
            },
            showKeyboard = true,
        )

        OnboardingDigitTextField(
            value = state.numberOfSeats,
            placeholder = stringResource(id = R.string.number_of_seats),
            onValueChange = { newValue ->
                onAction(OnboardingMviAction.SetNumberOfSeats(numberOfSeats = newValue))
            }
        )

        OnboardingDigitTextField(
            value = state.publicArea,
            placeholder = stringResource(id = R.string.area_of_halls),
            onValueChange = { newValue ->
                onAction(OnboardingMviAction.SetPublicArea(publicArea = newValue))
            }
        )

        OnboardingDigitTextField(
            value = state.kitchenArea,
            placeholder = stringResource(id = R.string.kitchen_area),
            imeAction = ImeAction.Default,
            onValueChange = { newValue ->
                onAction(OnboardingMviAction.SetKitchenArea(kitchenArea = newValue))
            }
        )
    }
}
