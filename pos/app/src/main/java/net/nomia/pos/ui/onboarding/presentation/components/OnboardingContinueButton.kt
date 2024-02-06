package net.nomia.pos.ui.onboarding.presentation.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.nomia.common.ui.composable.NomiaFilledButton
import net.nomia.common.ui.composable.NomiaSpinner
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.domain.model.ContinueButtonState

@Composable
internal fun OnboardingContinueButton(
    continueButtonState: ContinueButtonState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (continueButtonState) {
        ContinueButtonState.ENABLED ->
            NomiaFilledButton(
                onClick = onClick,
                modifier = modifier
            ) {
                Text(text = stringResource(id = R.string.continue_action))
            }

        ContinueButtonState.DISABLED ->
            NomiaFilledButton(
                enabled = false,
                onClick = onClick,
                modifier = modifier,
            ) {
                Text(text = stringResource(id = R.string.continue_action))
            }

        ContinueButtonState.PROGRESS ->
            NomiaFilledButton(
                enabled = false,
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = modifier,
            ) {
                NomiaSpinner()
            }
    }
}



