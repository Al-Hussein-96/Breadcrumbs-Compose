package com.alhussain.breadcrumbscompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.material3.rememberTopAppBarState

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.alhussain.breadcrumbscompose.ui.theme.BreadcrumbsComposeTheme
import com.alhussain.library.BreadCrumbs
import com.alhussain.library.UnlimitedBreadCrumbs
import com.alhussain.library.rememberBreadCrumbsState
//import com.alhussain.library.BreadCrumbs
//import com.alhussain.library.BreadCrumbsDefaults
//import com.alhussain.library.UnlimitedBreadCrumbs
//import com.alhussain.library.rememberBreadCrumbsState
import java.util.UUID
import kotlin.random.Random

val data = listOf(
    BreadCrumbs("One", 0),
    BreadCrumbs("Two", 1),
    BreadCrumbs("Three", 2),
    BreadCrumbs("Four", 3),
    BreadCrumbs("Five", 4),
    BreadCrumbs("Six", 5),
    BreadCrumbs("Seven", 6),
    BreadCrumbs("Eight", 7),
    BreadCrumbs("Nine", 8),
    BreadCrumbs("Ten", 9),
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreadcrumbsComposeTheme {
                // A surface container using the 'background' color from the theme

                val breadCrumbsState = rememberBreadCrumbsState(
                    data = data
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        UnlimitedBreadCrumbs(
                            breadCrumbsState,
                            maxItemToShow = 2
                        )
                        Button(onClick = {
                            breadCrumbsState.addNewItem(
                                BreadCrumbs(
                                    UUID.randomUUID().toString().substring(0,10),
                                    Random.nextInt(10, 123123)
                                )
                            )
                        }) {
                            Text(text = "Add new item")
                        }
                        Button(onClick = { breadCrumbsState.removeLast() }) {
                            Text(text = "Remove last item")
                        }
                        Button(onClick = { breadCrumbsState.backToHome() }) {
                            Text(text = "Back to home")
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BreadcrumbsComposeTheme {
//        UnlimitedBreadCrumbs(data) {
//
//        }
    }
}