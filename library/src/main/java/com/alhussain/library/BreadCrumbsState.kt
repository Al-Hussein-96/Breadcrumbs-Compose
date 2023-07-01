package com.alhussain.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList

@Stable
class BreadCrumbsState(
    data: List<BreadCrumbs>, currentIndex: Int,
) {


    private var currentIndex by mutableStateOf(currentIndex)

    val data = data.toMutableStateList()

    val currentItem: BreadCrumbs
        get() = data[currentIndex]

    var lastIndex = data.lastIndex

    val size: Int
        get() = data.size


    fun removeLast() {
        if (size > 1) {
            currentIndex--;
            data.removeLast()
        }
    }
    fun addNewItem(breadCrumbs: BreadCrumbs) {
        data.add(breadCrumbs)
        lastIndex = data.lastIndex
        currentIndex = lastIndex
    }
}


@Composable
fun rememberBreadCrumbsState(
    data: List<BreadCrumbs>,
    currentIndex: Int = 0,
): BreadCrumbsState = remember(key1 = data) {
    BreadCrumbsState(data = data, currentIndex)
}