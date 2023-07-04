package com.alhussain.library

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList

@Stable
class BreadCrumbsState(
    data: List<BreadCrumbs>, currentIndex: Int, private val onBreadClicked: (BreadCrumbs) -> Unit
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

    fun performClick(breadCrumbs: BreadCrumbs){
        removeAllToTheRight(breadCrumbs)
        onBreadClicked.invoke(breadCrumbs)
    }

    fun backToHome(){
        val home = data.first()
        removeAllToTheRight(home)
    }

    private fun removeAllToTheRight(breadCrumbs: BreadCrumbs) {
        val index = data.indexOf(breadCrumbs)

        val startIndexToRemove = maxOf(index,1)

        data.removeRange(startIndexToRemove,size)
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
    onBreadClicked: (BreadCrumbs) -> Unit = {},
): BreadCrumbsState = remember(key1 = data) {
    BreadCrumbsState(data = data, currentIndex, onBreadClicked = onBreadClicked)
}