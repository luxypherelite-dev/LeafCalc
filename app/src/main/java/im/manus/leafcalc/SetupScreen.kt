package im.manus.leafcalc

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SetupScreen(onComplete: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        
        // Icon
        Icon(
            Icons.Default.Lock,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color(0xFF8BC34A)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Create Your Secret Combo", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            "Use calculator buttons to create a secret combo. This combo will unlock your private Control Center.",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.7f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            FeatureItem("Easy to Remember", Icons.Default.Star)
            FeatureItem("You're the Only One", Icons.Default.Security)
            FeatureItem("Never Share It", Icons.Default.Lock)
        }

        Spacer(modifier = Modifier.height(48.dp))

        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Enter your secret combo", fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f))
                Text("7 6 5 × 4 2 +", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text("(= key is not allowed)", fontSize = 10.sp, color = Color.Red.copy(alpha = 0.7f))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onComplete,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text("Confirm Combo", fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
fun FeatureItem(label: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = null, tint = Color(0xFF8BC34A), modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 10.sp, color = Color.White.copy(alpha = 0.7f))
    }
}
