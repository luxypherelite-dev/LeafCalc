package im.manus.leafcalc

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun NatureBackground(
    modifier: Modifier = Modifier,
    showParticles: Boolean = true,
    showMist: Boolean = true
) {
    Box(modifier = modifier.fillMaxSize()) {
        // In the real APK, this would be a high-res Waterfall/Forest image
        // For the code, we provide the logic for the "Alive" elements
        if (showParticles) {
            LeafParticleSystem()
        }
        if (showMist) {
            MistSystem()
        }
    }
}

@Composable
fun LeafParticleSystem() {
    val particles = remember { List(15) { LeafParticle() } }
    val infiniteTransition = rememberInfiniteTransition(label = "leaves")
    
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = durationBasedTween(5000),
            repeatMode = RepeatMode.Restart
        ),
        label = "leaf_progress"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        particles.forEach { particle ->
            val y = (particle.startY + (size.height + 200) * progress * particle.speed) % (size.height + 200) - 100
            val x = particle.startX + kotlin.math.sin(progress * 5 + particle.phase) * 50
            
            rotate(degrees = progress * 360 * particle.rotationSpeed, pivot = Offset(x, y)) {
                // Draw a simple leaf shape
                drawOval(
                    color = Color(0xFF8BC34A).copy(alpha = 0.4f),
                    topLeft = Offset(x, y),
                    size = androidx.compose.ui.geometry.Size(20f, 10f)
                )
            }
        }
    }
}

private fun durationBasedTween(duration: Int): TweenSpec<Float> = tween(
    durationMillis = duration,
    easing = LinearEasing
)

class LeafParticle {
    val startX = Random.nextFloat() * 1000
    val startY = Random.nextFloat() * 2000
    val speed = 0.5f + Random.nextFloat()
    val rotationSpeed = 0.5f + Random.nextFloat()
    val phase = Random.nextFloat() * 2 * kotlin.math.PI.toFloat()
}

@Composable
fun MistSystem() {
    // Mist logic using animated alpha layers
}
