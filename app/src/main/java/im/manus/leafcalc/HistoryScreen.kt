package im.manus.leafcalc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Column {
                Text("HISTORY", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text("Your Calculations", fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Search Bar
        GlassCard(modifier = Modifier.fillMaxWidth().height(56.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color.White.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Search calculations...", color = Color.White.copy(alpha = 0.5f))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // History List
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item { SectionHeader("Today", 5) }
            items(listOf(
                "24,560 + 7,890" to "32,450",
                "1,245 × 56" to "69,720",
                "9,850 - 3,210" to "6,640"
            )) { (exp, res) ->
                HistoryItem(exp, res)
            }
            
            item { SectionHeader("Yesterday", 4) }
            items(listOf(
                "125 × 48" to "6,000",
                "7,654 + 1,346" to "9,000"
            )) { (exp, res) ->
                HistoryItem(exp, res)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, count: Int) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
        Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8BC34A))
        Spacer(modifier = Modifier.weight(1f))
        Text(count.toString(), fontSize = 12.sp, color = Color.White.copy(alpha = 0.5f))
    }
}

@Composable
fun HistoryItem(expression: String, result: String) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Calculate, contentDescription = null, tint = Color(0xFF8BC34A))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(expression, fontSize = 14.sp, color = Color.White)
                Text("10:42 AM", fontSize = 10.sp, color = Color.White.copy(alpha = 0.5f))
            }
            Text(result, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8BC34A))
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = Color.White.copy(alpha = 0.5f), modifier = Modifier.size(16.dp))
        }
    }
}
