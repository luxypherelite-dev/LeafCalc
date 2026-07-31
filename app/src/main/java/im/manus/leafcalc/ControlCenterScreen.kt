package im.manus.leafcalc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun ControlCenterScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text("CONTROL CENTER", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Hero Stats
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                StatItem("Protected Apps", "12")
                StatItem("Current Theme", "Waterfall")
                StatItem("Animations", "ON")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Modular Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(14) { index ->
                ConfigTile(index + 1)
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, fontSize = 10.sp, color = Color.White.copy(alpha = 0.7f))
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
fun ConfigTile(id: Int) {
    val (title, icon) = when(id) {
        1 -> "Access Method" to Icons.Filled.Lock
        2 -> "Notice" to Icons.Filled.Info
        3 -> "Buttons" to Icons.Filled.Edit
        4 -> "UI Style" to Icons.Filled.Palette
        5 -> "Timer" to Icons.Filled.Timer
        6 -> "Advanced" to Icons.Filled.Build
        else -> "Module $id" to Icons.Filled.Add
    }

    GlassCard(modifier = Modifier.aspectRatio(1f)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp), tint = Color(0xFF8BC34A))
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.White)
        }
    }
}
