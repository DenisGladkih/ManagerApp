package net.nomia.pos.ui.onboarding.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.nomia.common.ui.composable.NomiaOutlinedTextField
import net.nomia.common.ui.composable.list_item.NomiaCheckboxListItem
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R

@Composable
internal fun OnboardingSecondStep(
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

        NomiaOutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.name_of_place),
                )
            },
            supportingText = {
                Text(
                    text = stringResource(id = R.string.then_you_can),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))

        NomiaOutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.country_and_city),
                )
            },
            supportingText = {
                Text(
                    text = stringResource(id = R.string.required_to_determine_the_time_zone),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))

        NomiaOutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.address),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.extraSmall + MaterialTheme.spacers.small))

        var checked by remember { mutableStateOf(value = true) }

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
            onClick = {
                checked = !checked
            },
            checked = checked,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.extraSmall + MaterialTheme.spacers.small))

        AnimatedVisibility(visible = !checked) {
            NomiaOutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.automation_system),
                    )
                },
                supportingText = {
                    Text(
                        text = stringResource(id = R.string.name_of_the_system),
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))
        }
    }
}
