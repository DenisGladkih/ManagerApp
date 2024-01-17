package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.nomia.common.ui.composable.NomiaOutlinedTextField
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.components.onboardingContentWidth

@Composable
internal fun OnboardingFourthStepScreen(
    isTablet: Boolean,
) = Column(
    verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.large),
    modifier = Modifier.onboardingContentWidth(isTablet = isTablet),
) {
    Text(
        text = stringResource(id = R.string.indicate_the_size),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface,
    )

    NomiaOutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                text = stringResource(id = R.string.total_area),
            )
        },
        modifier = Modifier.fillMaxWidth(),
    )

    NomiaOutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                text = stringResource(id = R.string.number_of_seats),
            )
        },
        modifier = Modifier.fillMaxWidth(),
    )

    NomiaOutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                text = stringResource(id = R.string.area_of_halls),
            )
        },
        modifier = Modifier.fillMaxWidth(),
    )

    NomiaOutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                text = stringResource(id = R.string.kitchen_area),
            )
        },
        modifier = Modifier.fillMaxWidth(),
    )
}
