package im.manus.leafcalc

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {
    companion object {
        val SECRET_COMBO = stringPreferencesKey("secret_combo")
        val PROTECTED_APPS = stringPreferencesKey("protected_apps")
        val GLOBAL_PIN = stringPreferencesKey("global_pin")
        val GLOBAL_PASSWORD = stringPreferencesKey("global_password")
    }

    val secretCombo: Flow<String> = context.dataStore.data.map { it[SECRET_COMBO] ?: "765x42+" }
    val globalPin: Flow<String> = context.dataStore.data.map { it[GLOBAL_PIN] ?: "1234" }
    val globalPassword: Flow<String> = context.dataStore.data.map { it[GLOBAL_PASSWORD] ?: "abcde12345" }

    suspend fun saveSecretCombo(combo: String) {
        context.dataStore.edit { it[SECRET_COMBO] = combo }
    }

    suspend fun saveGlobalPin(pin: String) {
        context.dataStore.edit { it[GLOBAL_PIN] = pin }
    }
    
    suspend fun saveGlobalPassword(password: String) {
        context.dataStore.edit { it[GLOBAL_PASSWORD] = password }
    }
}
