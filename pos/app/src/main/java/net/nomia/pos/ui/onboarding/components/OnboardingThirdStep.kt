package net.nomia.pos.ui.onboarding.components

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
import net.nomia.pos.ui.onboarding.model.ThirdStepValue
import net.nomia.pos.ui.onboarding.mvi.OnboardingMviAction

@Composable
internal fun OnboardingThirdStep(
    state: ThirdStepValue,
    onAction: (OnboardingMviAction) -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.what_type_of_place),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.small))

        Text(
            text = stringResource(id = R.string.tell_us_a_little_more),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.large))

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_brunch_dining),
            headlineText = stringResource(id = R.string.restaurant),
            checked = state.isRestaurant,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsRestaurant(isRestaurant = newValue))
            },
        )

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_local_bar),
            headlineText = stringResource(id = R.string.bar),
            checked = state.isBar,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsBar(isBar = newValue))
            },
        )

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_fastfood),
            headlineText = stringResource(id = R.string.cafe),
            checked = state.isCafe,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsCafe(isCafe = newValue))
            },
        )

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_soup_kitchen),
            headlineText = stringResource(id = R.string.dining_room),
            checked = state.isDiningRoom,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsDiningRoom(isDiningRoom = newValue))
            },
        )

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_local_cafe),
            headlineText = stringResource(id = R.string.coffee_house),
            checked = state.isCoffeeHouse,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsCoffeeHouse(isCoffeeHouse = newValue))
            },
        )

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_kebab_dining),
            headlineText = stringResource(id = R.string.cooking),
            checked = state.isCooking,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsCooking(isCooking = newValue))
            },
        )

        OnboardingListItem(
            iconPainter = painterResource(id = R.drawable.ic_store),
            headlineText = stringResource(id = R.string.other),
            checked = state.isOther,
            onCheckedChange = { newValue ->
                onAction(OnboardingMviAction.SetIsOther(isOther = newValue))
            },
        )
    }
}
