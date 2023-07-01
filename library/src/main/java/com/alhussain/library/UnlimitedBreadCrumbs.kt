package com.alhussain.library


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

private fun Modifier.defaultModifier(): Modifier {
    return composed {
        this
            .padding(12.dp)
            .fillMaxWidth()

    }
}


@Composable
fun UnlimitedBreadCrumbs(
    state: BreadCrumbsState,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier.defaultModifier(),
    icon: ImageVector = Icons.Default.ArrowForward,
    colors: BreadCrumbsColors = BreadCrumbsDefaults.breadCrumbsColors(),
    maxItemToShow: Int = Int.MAX_VALUE,
    onBreadClicked: (BreadCrumbs) -> Unit
) {
    val scrollState = rememberLazyListState()

    Box(
        modifier = Modifier
            .defaultModifier()
            .then(modifier)
            .background(color = colors.backgroundColor),
        contentAlignment = Alignment.TopStart
    ) {
        LazyRow(state = scrollState) {


            itemsIndexed(items = state.data.takeLast(maxItemToShow), key = { _, item ->
                item.index
            }) { index, it ->
                Log.i("Composable", "recompose with item $index ${state.lastIndex}")

                if (index != 0)
                    Icon(
                        imageVector = icon,
                        contentDescription = "next",
                        modifier = Modifier.padding(4.dp),
                        tint = colors.iconColor
                    )


                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable(onClick = { onBreadClicked.invoke(it) }), text = it.name, color = colors.textColor
                )


            }
        }
    }

    LaunchedEffect(key1 = state.size) {
        scrollState.scrollToItem(state.lastIndex)
    }
}


@Stable
class BreadCrumbsColors internal constructor(
    internal val backgroundColor: Color = Color.Transparent,
    internal val iconColor: Color = Color.Black,
    internal val textColor: Color = Color.Black
)


object BreadCrumbsDefaults {

    @Composable
    fun breadCrumbsColors(
        backgroundColor: Color = BreadCrumbsTokens.BackgroundColor,
        iconColor: Color = BreadCrumbsTokens.IconColor,
        textColor: Color = BreadCrumbsTokens.TextColor
    ): BreadCrumbsColors =
        BreadCrumbsColors(
            backgroundColor, iconColor, textColor
        )

}