package net.nomia.pos.ui.onboarding.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import net.nomia.common.ui.composable.NomiaOutlinedTextField
import net.nomia.common.ui.composable.list_item.NomiaCheckboxListItem
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.domain.model.SecondStepValue
import net.nomia.pos.ui.onboarding.presentation.mvi.OnboardingMviAction

@Composable
internal fun OnboardingSecondStep(
    state: SecondStepValue,
    onAction: (OnboardingMviAction) -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.create_your_first_place),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.small))

        Text(
            text = stringResource(id = R.string.you_can_always_change),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.large))

        var nameOfShop by remember(state.nameOfShop) {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.nameOfShop,
                    selection = TextRange(state.nameOfShop.length)
                ),
            )
        }

        NomiaOutlinedTextField(
            value = nameOfShop,
            onValueChange = { newValue ->
                nameOfShop = newValue
                onAction(OnboardingMviAction.InputNameOfShop(nameOfShop = newValue.text))
            },
            placeholder = {
                Text(text = stringResource(id = R.string.name_of_shop))
            },
            supportingText = {
                Text(text = stringResource(id = R.string.then_you_can))
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            showKeyboard = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))

        var countryAndCity by remember(state.countryAndCity) { mutableStateOf(value = state.countryAndCity) }

        NomiaOutlinedTextField(
            value = countryAndCity,
            onValueChange = { newValue ->
                countryAndCity = newValue
                onAction(OnboardingMviAction.InputCountryAndCity(countryAndCity = newValue))
            },
            placeholder = {
                Text(text = stringResource(id = R.string.country_and_city))
            },
            supportingText = {
                Text(text = stringResource(id = R.string.required_to_determine_the_time_zone))
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))

        var address by remember(state.address) { mutableStateOf(value = state.address) }

        NomiaOutlinedTextField(
            value = address,
            onValueChange = { newValue ->
                address = newValue
                onAction(OnboardingMviAction.InputAddress(address = newValue))
            },
            placeholder = {
                Text(text = stringResource(id = R.string.address))
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.extraSmall + MaterialTheme.spacers.small))

        NomiaCheckboxListItem(
            headlineText = {
                Text(
                    text = stringResource(id = R.string.this_is_a_new_place),
                )
            },
            supportingText = {
                Text(
                    text = stringResource(id = R.string.check_the_box),
                )
            },
            onClick = { newValue ->
                onAction(OnboardingMviAction.SetIsNewPlace(isNewPlace = newValue))
            },
            checked = state.isNewPlace,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.extraSmall + MaterialTheme.spacers.small))

        AnimatedVisibility(visible = !state.isNewPlace) {
            var automationSystem by remember(state.automationSystem) { mutableStateOf(value = state.automationSystem) }

            NomiaOutlinedTextField(
                value = automationSystem,
                onValueChange = { newValue ->
                    automationSystem = newValue
                    onAction(OnboardingMviAction.InputAutomationSystem(automationSystem = newValue))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.automation_system))
                },
                supportingText = {
                    Text(text = stringResource(id = R.string.name_of_the_system))
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))
        }
    }
}
