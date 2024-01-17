package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.components.onboardingContentWidth

@Composable
internal fun OnboardingSixthStepScreen(
    isTablet: Boolean,
) = Column(
    modifier = Modifier.onboardingContentWidth(isTablet = isTablet),
) {
    Text(
        text = stringResource(id = R.string.thanks_for_application),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface,
    )

    Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.small))

    Text(
        text = stringResource(id = R.string.our_manager_will_contact),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}
