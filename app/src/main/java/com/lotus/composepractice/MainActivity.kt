package com.lotus.composepractice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lotus.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                GreetingWidjet()
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {

    val isExpanded = rememberSaveable {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(
        if (isExpanded.value) 48.dp else 0.dp, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { isExpanded.value = !isExpanded.value }
            ) {
                if (isExpanded.value) Text(text = "Show More ") else Text(text = "Show much less")
            }

        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        GreetingWidjetCol()
    }
}

@Composable
private fun GreetingWidjet() {
    var toShow by rememberSaveable {
        mutableStateOf(false)
    }
    if (toShow)
        GreetingWidjetCol()
    else
        SplashScreen {
            toShow = true
        }

}

@Composable
private fun GreetingWidjetCol() {
    val list = List(200) { "Hello $it" }
    LazyColumn(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        items(items = list) { valie ->
            Greeting(name = "Some value $valie")
        }
    }
}

@Composable
private fun SplashScreen(onClickedButton: () -> Unit) {
    Surface(modifier = Modifier.background(Color.Black)) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to me test app of learning")
            Button(onClick = onClickedButton) {
                Text(text = "Some nice text")
            }

        }
    }
}