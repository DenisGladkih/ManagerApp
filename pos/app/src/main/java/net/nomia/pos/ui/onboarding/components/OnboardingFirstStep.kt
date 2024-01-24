package net.nomia.pos.ui.onboarding.components

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
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.model.FirstStepValue
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviAction

@Composable
internal fun OnboardingFirstStep(
    state: FirstStepValue,
    onAction: (OnboardingMviAction) -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.welcome_title),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.small))

        Text(
            text = stringResource(id = R.string.enter_your_name),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.large))

        var clientName by remember(state.clientName) {
            mutableStateOf(
                value = TextFieldValue(
                    text = state.clientName,
                    selection = TextRange(state.clientName.length)
                )
            )
        }

        NomiaOutlinedTextField(
            value = clientName,
            onValueChange = { newValue ->
                clientName = newValue
                onAction(OnboardingMviAction.InputClientName(clientName = newValue.text))
            },
            placeholder = {
                Text(text = stringResource(id = R.string.your_name))
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            showKeyboard = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.large))

        var phoneOrEmail by remember(state.phoneOrEmail) { mutableStateOf(value = state.phoneOrEmail) }

        NomiaOutlinedTextField(
            value = phoneOrEmail,
            onValueChange = { newValue ->
                phoneOrEmail = newValue
                onAction(OnboardingMviAction.InputPhoneOrEmail(phoneOrEmail = newValue))
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.phone_or_email),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
