package net.nomia.pos.ui.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.nomia.common.ui.composable.NomiaFilledButton
import net.nomia.common.ui.composable.NomiaFooter
import net.nomia.common.ui.composable.NomiaOutlinedTextField
import net.nomia.common.ui.composable.NomiaScrollableScaffold
import net.nomia.common.ui.composable.list_item.NomiaCheckboxListItem
import net.nomia.common.ui.composable.list_item.NomiaListItem
import net.nomia.common.ui.theme.appResources
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R

private val tabletContentWidth = 560.dp

@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val isTablet = booleanResource(id = R.bool.isTablet)
    var stepNumber by remember { mutableStateOf(value = 1) }
    BackHandler {
        when (stepNumber) {
            in 2..5 -> --stepNumber
            else -> viewModel.logOut()
        }
    }

    NomiaScrollableScaffold(
        title = {
            Icon(
                painter = painterResource(id = MaterialTheme.appResources.textLogoResId),
                tint = Color.Unspecified,
                contentDescription = null
            )
        },
        actions = {
            AnimatedVisibility(
                visible = stepNumber in 3..5,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = Color.Transparent,
                    ),
                ) {
                    Text(text = stringResource(id = R.string.next_action))
                }
            }
        },
        footer = {
            AnimatedVisibility(
                visible = stepNumber != 6,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Footer(
                    isTablet = isTablet,
                    showBackButton = stepNumber in 2..5,
                    onBackClick = {
                        if (stepNumber > 1) {
                            --stepNumber
                        }
                    },
                    onContinueClick = {
                        if (stepNumber < 6) {
                            ++stepNumber
                        }
                    },
                )
            }
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacers.medium)
                .fillMaxWidth(),
        ) {
            //TODO: refactoring
            SegmentsBar(stepNumber = stepNumber)

            Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))

            if (isTablet) {
                Spacer(modifier = Modifier.height(height = MaterialTheme.spacers.medium))
            }

            when (stepNumber) {
                1 -> Step1(isTablet = isTablet)
                2 -> Step2(isTablet = isTablet)
                3 -> Step3(isTablet = isTablet)
                4 -> Step4(isTablet = isTablet)
                5 -> Step5(isTablet = isTablet)
                6 -> Step6(isTablet = isTablet)
                else -> Unit
            }
        }
    }
}

@Composable
private fun SegmentsBar(
    stepNumber: Int
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.extraSmall)
) {
    val stepsCount = 6
    for (i in 1..stepsCount) {
        val animatedColor by animateColorAsState(
            if (i > stepNumber) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.primary,
            label = "color"
        )
        Spacer(
            modifier = Modifier
                .height(height = MaterialTheme.spacers.extraSmall)
                .weight(weight = 1f)
                .background(color = animatedColor)
        )
    }
}

@Composable
private fun Step1(
    isTablet: Boolean,
) = Column(
    modifier = Modifier
        .run {
            if (isTablet) {
                width(width = tabletContentWidth)
            } else {
                fillMaxWidth()
            }
        },
) {
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

@Composable
private fun Step2(
    isTablet: Boolean,
) = Column(
    modifier = Modifier
        .run {
            if (isTablet) {
                width(width = tabletContentWidth)
            } else {
                fillMaxWidth()
            }
        },
) {
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

@Composable
private fun Step3(
    isTablet: Boolean,
) = Column(
    modifier = Modifier
        .run {
            if (isTablet) {
                width(width = tabletContentWidth)
            } else {
                fillMaxWidth()
            }
        },
) {
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
        checked = false,
        onCheckedChange = {}
    )

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_local_bar),
        headlineText = stringResource(id = R.string.bar),
        checked = false,
        onCheckedChange = {}
    )

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_fastfood),
        headlineText = stringResource(id = R.string.cafe),
        checked = false,
        onCheckedChange = {}
    )

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_soup_kitchen),
        headlineText = stringResource(id = R.string.dining_room),
        checked = false,
        onCheckedChange = {}
    )

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_local_cafe),
        headlineText = stringResource(id = R.string.coffee_house),
        checked = false,
        onCheckedChange = {}
    )

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_kebab_dining),
        headlineText = stringResource(id = R.string.cooking),
        checked = false,
        onCheckedChange = {}
    )

    OnboardingListItem(
        iconPainter = painterResource(id = R.drawable.ic_store),
        headlineText = stringResource(id = R.string.other),
        checked = false,
        onCheckedChange = {}
    )
}

@Composable
private fun Step4(
    isTablet: Boolean,
) = Column(
    verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.large),
    modifier = Modifier
        .run {
            if (isTablet) {
                width(width = tabletContentWidth)
            } else {
                fillMaxWidth()
            }
        },
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

@Composable
private fun Step5(
    isTablet: Boolean,
) = Column(
    modifier = Modifier
        .run {
            if (isTablet) {
                width(width = tabletContentWidth)
            } else {
                fillMaxWidth()
            }
        },
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

@Composable
private fun Step6(
    isTablet: Boolean,
) = Column(
    modifier = Modifier
        .run {
            if (isTablet) {
                width(width = tabletContentWidth)
            } else {
                fillMaxWidth()
            }
        },
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

@Composable
private fun OnboardingListItem(
    iconPainter: Painter,
    headlineText: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
) = Column {
    NomiaListItem(
        headlineText = {
            Text(
                text = headlineText,
            )
        },
        leadingContent = {
            Icon(
                modifier = Modifier.size(size = 24.dp),
                painter = iconPainter,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null,
            )
        },
    ) {
        Checkbox(
            modifier = Modifier.requiredSize(MaterialTheme.spacers.large),
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }

    Divider()
}

@Composable
private fun ColumnScope.Footer(
    isTablet: Boolean,
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
) = NomiaFooter {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.small),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .run {
                    if (isTablet) {
                        width(width = tabletContentWidth)
                    } else {
                        fillMaxWidth()
                    }
                }
        ) {
            //TODO: Вынести кнопку в компоненты
            AnimatedVisibility(visible = showBackButton) {
                if (isTablet) {
                    OutlinedButton(
                        onClick = onBackClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ),
                        modifier = Modifier.width(width = 91.dp)
                    ) {
                        Text(text = stringResource(id = R.string.back_action))
                    }
                } else {
                    OutlinedIconButton(
                        onClick = onBackClick,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier.size(size = 40.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_24),
                            contentDescription = null,
                        )
                    }
                }
            }

            NomiaFilledButton(
                onClick = onContinueClick,
                modifier = Modifier.weight(weight = 1f)
            ) {
                Text(text = stringResource(id = R.string.continue_action))
            }
        }
    }
}
