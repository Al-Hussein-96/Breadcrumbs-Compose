package com.alhussain.library

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UnlimitedBreadCrumbs(state: BreadCrumbsState) {
    val scrollState = rememberLazyListState()

    LazyRow(state = scrollState, modifier = Modifier.padding(12.dp)) {



        itemsIndexed(items = state.data, key = {
                _, item -> item.index
        }) { index,it ->
            Text(modifier = Modifier
                .padding(4.dp)
                .clickable(onClick = {
                    state.performClick(it)
                }), text = it.name
            )

            Log.i("Composable","recompose with item $index")

            if (index != state.lastIndex)
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "next",
                    modifier = Modifier.padding(4.dp)
                )
        }
    }

    LaunchedEffect(key1 = state.size){
        scrollState.scrollToItem(state.lastIndex)
    }
}