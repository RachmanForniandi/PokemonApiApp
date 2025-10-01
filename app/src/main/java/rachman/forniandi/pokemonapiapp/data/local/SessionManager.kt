package rachman.forniandi.pokemonapiapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_session")
class SessionManager (private val context: Context){


    suspend fun saveSession(username: String, email: String) {
        context.dataStore.edit { pref ->
            pref[KEY_IS_LOGGED_IN] = true
            pref[KEY_USERNAME] = username
            pref[KEY_EMAIL] = email
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { pref ->
            pref.clear()
        }
    }

    val isLoggedIn = context.dataStore.data.map { pref ->
        pref[KEY_IS_LOGGED_IN] ?: false
    }


    val username: Flow<String> = context.dataStore.data.map { pref ->
        pref[KEY_USERNAME] ?: ""
    }

    val email: Flow<String> = context.dataStore.data.map { pref ->
        pref[KEY_EMAIL] ?: ""
    }

    companion object {
        private val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val KEY_USERNAME = stringPreferencesKey("username")
        private val KEY_EMAIL = stringPreferencesKey("email")
    }
}