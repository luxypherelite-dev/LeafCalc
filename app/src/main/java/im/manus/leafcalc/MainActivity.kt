package im.manus.leafcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeafCalcTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    
    // Background Image (Placeholder for Nature Theme)
    Box(modifier = Modifier.fillMaxSize()) {
        // In a real app, use the actual nature image resources
        // Image(
        //     painter = painterResource(id = R.drawable.nature_bg),
        //     contentDescription = null,
        //     modifier = Modifier.fillMaxSize(),
        //     contentScale = ContentScale.Crop
        // )
        
        NavHost(navController = navController, startDestination = "calculator") {
            composable("calculator") {
                CalculatorScreen(onSecretTriggered = {
                    navController.navigate("control_center")
                })
            }
            composable("control_center") {
                ControlCenterScreen(onBack = {
                    navController.navigate("calculator")
                })
            }
        }
    }
}

@Composable
fun CalculatorScreen(onSecretTriggered: () -> Unit) {
    val engine = remember { CalculatorEngine(onSecretTriggered) }
    var currentMode by remember { mutableStateOf("Standard") }
    
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text("LeafCalc", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) { /* History */ }
            IconButton(onClick = {}) { /* Settings */ }
        }

        // Display
        GlassCard(modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp)) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Text(engine.expression.value, fontSize = 24.sp)
                Text(engine.result.value, fontSize = 48.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Mode Selector
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("Standard", "Scientific", "Programmer", "Converter").forEach { mode ->
                TextButton(
                    onClick = { currentMode = mode },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (currentMode == mode) Color(0xFF8BC34A) else Color.White.copy(alpha = 0.5f)
                    )
                ) {
                    Text(mode, fontSize = 10.sp)
                }
            }
        }

        // Keypad
        val keys = if (currentMode == "Standard") {
            listOf(
                listOf("AC", "()", "%", "÷"),
                listOf("7", "8", "9", "×"),
                listOf("4", "5", "6", "−"),
                listOf("1", "2", "3", "+"),
                listOf("0", ".", "=")
            )
        } else {
            // Scientific Mode
            listOf(
                listOf("sin", "cos", "tan", "log"),
                listOf("AC", "()", "%", "÷"),
                listOf("7", "8", "9", "×"),
                listOf("4", "5", "6", "−"),
                listOf("1", "2", "3", "+"),
                listOf("0", ".", "=")
            )
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            keys.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    row.forEach { key ->
                        MossyButton(
                            modifier = Modifier.size(70.dp).padding(4.dp),
                            onClick = { engine.onKeyPress(key) }
                        ) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                Text(key, fontSize = 20.sp, color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ControlCenterScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Control Center", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        // Implementation of the grid and app management would go here
        Button(onClick = onBack) { Text("Back to Calculator") }
    }
}
