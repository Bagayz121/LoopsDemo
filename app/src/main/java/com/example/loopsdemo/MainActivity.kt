package com.example.loopsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loopsdemo.ui.theme.LoopsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoopsDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoopsScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoopsScreen(modifier: Modifier = Modifier) {
    var nText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Сумма 1/k! (k от 1 до n)",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = nText,
            onValueChange = { nText = it },
            label = { Text("Введите n") },
            singleLine = true
        )
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val n = nText.toIntOrNull()
            if (n == null || n <= 0) {
                result = "Введите натуральное n"
            } else {
                // Цикл do-while: тело выполняется минимум один раз,
                // условие проверяется в конце
                var sum = 0.0
                var fact = 1.0
                var k = 1
                do {
                    fact *= k
                    sum += 1.0 / fact
                    k++
                } while (k <= n)
                result = "S = $sum"
            }
        }) {
            Text("Вычислить")
        }

        Spacer(Modifier.height(16.dp))
        Text(text = "Результат: $result")
    }
}

@Preview(showBackground = true)
@Composable
fun LoopsScreenPreview() {
    LoopsDemoTheme {
        LoopsScreen()
    }
}