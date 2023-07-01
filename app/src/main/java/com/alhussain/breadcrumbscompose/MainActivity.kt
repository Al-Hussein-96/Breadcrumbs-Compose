package com.alhussain.breadcrumbscompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.rememberTopAppBarState

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.alhussain.breadcrumbscompose.ui.theme.BreadcrumbsComposeTheme
import com.alhussain.library.BreadCrumbs
import com.alhussain.library.UnlimitedBreadCrumbs
import com.alhussain.library.rememberBreadCrumbsState
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

                val context = LocalContext.current


                val breadCrumbsState = rememberBreadCrumbsState(
                    data = data,
                    onClicked = {
                        Toast.makeText(
                            context,
                            "Clicked on Bread with ${it.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        UnlimitedBreadCrumbs(breadCrumbsState)
                        Button(onClick = {
                            breadCrumbsState.addNewItem(
                                BreadCrumbs(
                                    "tasdasd",
                                    Random.nextInt(10,123123)
                                )
                            )
                        }) {
                            Text(text = "Add new item")
                        }
                        Button(onClick = { breadCrumbsState.removeLast() }) {
                            Text(text = "Remove last item")
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