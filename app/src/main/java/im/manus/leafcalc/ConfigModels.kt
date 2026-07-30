package im.manus.leafcalc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data class AppConfig(
    val packageName: String,
    val appName: String,
    var isProtected: Boolean = false,
    var lockType: LockType = LockType.PIN,
    var customPin: String? = null,
    var customPassword: String? = null,
    var noticeTemplate: String = "Template B",
    var customNotice: String? = null,
    var fakeButtonLabel: String = "Customer Service Support",
    var realButtonLabel: String = "Customer Service Support",
    var hexColor: String = "#8BC34A",
    var timerA: Int = 0, // Session Timer
    var timerB: Int = 0, // Wait Timer
    var isStealthMode: Boolean = false
)

enum class LockType { PIN, PASSWORD }

class HexInputViewModel {
    var hexText by mutableStateOf("#")
        private set

    fun onHexInput(input: String) {
        val clean = input.replace("#", "").filter { it.isDigit() || it.lowercaseChar() in 'a'..'f' }
        
        if (clean.length <= 6) {
            hexText = "#$clean"
        } else if (clean.length == 7) {
            // The "Premium" Shift Logic: #236826 + 1 -> #236861
            // (Replaces the 2nd-to-last character)
            val shifted = clean.substring(0, 5) + clean.last()
            hexText = "#$shifted"
        }
    }

    fun isValid(): Boolean = hexText.length == 7

    fun getColor(): Color {
        return try {
            Color(android.graphics.Color.parseColor(hexText))
        } catch (e: Exception) {
            Color(0xFF8BC34A)
        }
    }
}
