package net.nomia.pos.ui.onboarding.presentation.components

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
import net.nomia.pos.ui.onboarding.domain.model.FifthStepValue
import net.nomia.pos.ui.onboarding.presentation.mvi.OnboardingMviAction

@Composable
internal fun OnboardingFifthStep(
    state: FifthStepValue,
    onAction: (OnboardingMviAction) -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.what_types_of),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.large))

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_brunch_dining),
            headlineText = stringResource(id = R.string.takeaway),
            checked = state.isTakeAway,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsTakeAway(newValue))
            }
        )

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_local_bar),
            headlineText = stringResource(id = R.string.in_the_store),
            checked = state.isInTheStore,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsInTheStore(newValue))
            }
        )

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_fastfood),
            headlineText = stringResource(id = R.string.delivery),
            checked = state.isDelivery,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsDelivery(newValue))
            }
        )
    }
}
