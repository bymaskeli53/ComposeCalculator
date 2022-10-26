package com.example.composecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecalculator.ui.theme.ComposeCalculatorTheme
import java.lang.ArithmeticException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculatorApp()

                }
            }
        }
    }
}


@Composable
fun CalculatorApp() {
    var firstValue by remember { mutableStateOf("") }
    var secondValue by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        TextField(value = firstValue, onValueChange = { firstValue = it })
        Spacer(modifier = Modifier.height(24.dp))
        TextField(value = secondValue, onValueChange = { secondValue = it })
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { result = (firstValue.toInt() + secondValue.toInt()).toString() },
                modifier = Modifier.width(36.dp)
            ) {
                Text(text = "+")
            }
            Button(
                onClick = { result = (firstValue.toInt() - secondValue.toInt()).toString() },
                modifier = Modifier.width(36.dp)
            ) {
                Text(text = "-")

            }

            Button(
                onClick = { result = (firstValue.toInt() * secondValue.toInt()).toString() },
                modifier = Modifier.width(36.dp)
            ) {
                Text(text = "X")

            }

            Button(
                onClick = {
                    // for division by zero
                    try {
                        result = (firstValue.toInt() / secondValue.toInt()).toString()
                    } catch (e: ArithmeticException){
                        result = "second value can not be zero"

                    }
                    },
                modifier = Modifier.width(36.dp)
            ) {
                Text(text = "/")

            }
        }

        Spacer(modifier = Modifier.height(36.dp))
        showText(text = result)


    }

}
@Composable
fun showText(text: String) {
    Text(text = text)
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCalculatorTheme {
        CalculatorApp()
    }
}