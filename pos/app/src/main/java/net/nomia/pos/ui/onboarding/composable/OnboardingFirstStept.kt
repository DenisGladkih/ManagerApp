package net.nomia.pos.ui.onboarding.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.nomia.common.ui.composable.NomiaOutlinedTextField
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R

@Composable
internal fun OnboardingFirstStep() {
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

        NomiaOutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.your_name),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.large))

        NomiaOutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.phone_or_email),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
