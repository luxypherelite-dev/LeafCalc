package im.manus.leafcalc

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        
        Text("WELCOME TO", fontSize = 14.sp, color = Color.White.copy(alpha = 0.7f))
        Text("NATURE\nCALCULATOR", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8BC34A), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text("Powerful calculations.\nComplete privacy.", fontSize = 16.sp, color = Color.White.copy(alpha = 0.7f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)

        Spacer(modifier = Modifier.height(48.dp))

        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF8BC34A))
                Spacer(modifier = Modifier.height(16.dp))
                Text("Your Privacy. Your Control.", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(
                    "Set a secret combo using calculator keys. Use it anytime to unlock your private Control Center.",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    FeatureIcon("Protect\nYour Apps", Icons.Default.Security)
                    FeatureIcon("Secure\nYour Data", Icons.Default.Lock)
                    FeatureIcon("Stay\nPrivate", Icons.Default.VisibilityOff)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onStart,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text("Get Started", fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = null)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text("Everything stays on your device.\nYour data is never shared.", fontSize = 10.sp, color = Color.White.copy(alpha = 0.5f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

@Composable
fun FeatureIcon(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = null, tint = Color(0xFF8BC34A), modifier = Modifier.size(20.dp))
        Text(label, fontSize = 10.sp, color = Color.White.copy(alpha = 0.7f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}
