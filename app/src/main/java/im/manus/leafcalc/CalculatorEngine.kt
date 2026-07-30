package im.manus.leafcalc

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import java.util.LinkedList

class CalculatorEngine(private val onSecretTriggered: () -> Unit) {
    private val _expression = mutableStateOf("")
    val expression: State<String> = _expression

    private val _result = mutableStateOf("0")
    val result: State<String> = _result

    private val secretBuffer = LinkedList<String>()
    private var targetSecret = "765x42+" // Default from spec

    fun onKeyPress(key: String) {
        // 1. Secret Buffer Logic
        if (key == "=") {
            secretBuffer.clear() // = cancels the combo context
        } else {
            secretBuffer.add(key)
            if (secretBuffer.size > 6) {
                secretBuffer.removeFirst()
            }
            checkSecret()
        }

        // 2. Calculator Logic
        when (key) {
            "AC" -> {
                _expression.value = ""
                _result.value = "0"
            }
            "=" -> evaluate()
            else -> {
                _expression.value += key
                // Real-time update logic would go here
            }
        }
    }

    private fun checkSecret() {
        val current = secretBuffer.joinToString("")
        if (current == targetSecret) {
            onSecretTriggered()
            secretBuffer.clear()
        }
    }

    private fun evaluate() {
        // Simple evaluator for demo/MVP
        try {
            // In a real app, use a math library like exp4j
            _result.value = "Result" 
        } catch (e: Exception) {
            _result.value = "Error"
        }
    }
    
    fun setTargetSecret(newSecret: String) {
        targetSecret = newSecret
    }
}
