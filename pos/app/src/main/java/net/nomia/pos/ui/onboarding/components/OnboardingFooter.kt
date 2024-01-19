package net.nomia.pos.ui.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.nomia.common.ui.composable.NomiaFilledButton
import net.nomia.common.ui.composable.NomiaFooter
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.R

@Composable
internal fun ColumnScope.OnboardingFooter(
    backButtonVisible: Boolean,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
) = NomiaFooter {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.small),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .onboardingContentWidth(),
        ) {
            AnimatedVisibility(visible = backButtonVisible) {
                val isTablet = booleanResource(id = R.bool.isTablet)
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
                        modifier = Modifier.size(size = 40.dp),
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
                modifier = Modifier.weight(weight = 1f),
            ) {
                Text(text = stringResource(id = R.string.continue_action))
            }
        }
    }
}
