package net.nomia.pos.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R
import net.nomia.pos.ui.onboarding.components.OnboardingListItem
import net.nomia.pos.ui.onboarding.components.onboardingContentWidth

@Composable
internal fun OnboardingFifthStepScreen(
    isTablet: Boolean,
) = Column(
    modifier = Modifier.onboardingContentWidth(isTablet = isTablet),
) {
    Text(
        text = stringResource(id = R.string.what_types_of),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface,
    )

    Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.large))

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_brunch_dining),
        headlineText = stringResource(id = R.string.takeaway),
        checked = false,
        onCheckedChange = {}
    )

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_local_bar),
        headlineText = stringResource(id = R.string.in_the_place),
        checked = false,
        onCheckedChange = {}
    )

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_fastfood),
        headlineText = stringResource(id = R.string.delivery),
        checked = false,
        onCheckedChange = {}
    )
}
