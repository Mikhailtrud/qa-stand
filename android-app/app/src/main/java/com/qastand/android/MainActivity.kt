package com.qastand.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.qastand.android.network.ApiClient
import com.qastand.android.network.CreateUserRequest
import com.qastand.android.network.LoginRequest
import com.qastand.android.network.UpdateUserRequest
import com.qastand.android.network.UserResponse
import java.io.IOException
import kotlinx.coroutines.launch
import retrofit2.HttpException

private enum class Screen {
    LOGIN,
    USERS,
    PLAYGROUND,
    CREATE_USER,
    EDIT_USER,
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var token by remember { mutableStateOf(AuthTokenStore.get(this@MainActivity)) }
                    var screen by remember {
                        mutableStateOf(if (token == null) Screen.LOGIN else Screen.USERS)
                    }
                    var selectedUser by remember { mutableStateOf<UserResponse?>(null) }
                    var refreshKey by remember { mutableIntStateOf(0) }

                    when (screen) {
                        Screen.LOGIN -> LoginScreen { loginToken ->
                            AuthTokenStore.save(this@MainActivity, loginToken)
                            token = loginToken
                            screen = Screen.USERS
                        }

                        Screen.USERS -> UsersScreen(
                            token = token!!,
                            refreshKey = refreshKey,
                            onCreateUser = { screen = Screen.CREATE_USER },
                            onPlayground = { screen = Screen.PLAYGROUND },
                            onEditUser = { user ->
                                selectedUser = user
                                screen = Screen.EDIT_USER
                            },
                            onRefresh = { refreshKey++ },
                            onLogout = {
                                AuthTokenStore.clear(this@MainActivity)
                                token = null
                                selectedUser = null
                                refreshKey = 0
                                screen = Screen.LOGIN
                            },
                        )

                        Screen.PLAYGROUND -> PlaygroundScreen(
                            onUsers = { screen = Screen.USERS },
                        )

                        Screen.CREATE_USER -> UserFormScreen(
                            token = token!!,
                            user = null,
                            onSaved = {
                                refreshKey++
                                screen = Screen.USERS
                            },
                            onCancel = { screen = Screen.USERS },
                        )

                        Screen.EDIT_USER -> UserFormScreen(
                            token = token!!,
                            user = selectedUser!!,
                            onSaved = {
                                refreshKey++
                                screen = Screen.USERS
                            },
                            onCancel = { screen = Screen.USERS },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var status by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .appiumEditableTag("login_email", email) { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .appiumEditableTag("login_password", password) { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
        )

        status?.let {
            Text(text = it, modifier = Modifier.padding(top = 16.dp))
        }

        Button(
            onClick = {
                focusManager.clearFocus()
                keyboardController?.hide()
                if (email.isBlank() || password.isBlank()) {
                    status = "Enter email and password"
                    return@Button
                }

                scope.launch {
                    isLoading = true
                    status = null
                    try {
                        val response = ApiClient.authApi.login(LoginRequest(email, password))
                        status = "Login successful\nRole: ${response.role}"
                        onLoginSuccess(response.token)
                    } catch (error: HttpException) {
                        status = when (error.code()) {
                            400 -> "Check the entered email and password"
                            401 -> "Invalid email or password"
                            else -> "Server error (${error.code()})"
                        }
                    } catch (_: IOException) {
                        status = "Cannot connect to backend"
                    } catch (_: Exception) {
                        status = "Unexpected error"
                    } finally {
                        isLoading = false
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .appiumTag("login_button"),
            enabled = !isLoading,
        ) {
            Text("Login")
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
private fun UsersScreen(
    token: String,
    refreshKey: Int,
    onCreateUser: () -> Unit,
    onPlayground: () -> Unit,
    onEditUser: (UserResponse) -> Unit,
    onRefresh: () -> Unit,
    onLogout: () -> Unit,
) {
    var users by remember { mutableStateOf(emptyList<UserResponse>()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var userToDelete by remember { mutableStateOf<UserResponse?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(token, refreshKey) {
        isLoading = true
        errorMessage = null
        try {
            users = ApiClient.usersApi.getUsers("Bearer $token")
        } catch (error: Exception) {
            errorMessage = requestErrorMessage(error, "load users")
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .appiumTag("users_screen"),
    ) {
        Text(text = "Users", style = MaterialTheme.typography.headlineMedium)

        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .appiumTag("logout_button"),
        ) {
            Text("Logout")
        }

        Button(
            onClick = onPlayground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .appiumTag("open_playground_button"),
        ) {
            Text("QA Playground")
        }

        Button(
            onClick = onCreateUser,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .appiumTag("create_user_button"),
        ) {
            Text("Create user")
        }

        Button(
            onClick = onRefresh,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .appiumTag("refresh_users_button"),
            enabled = !isLoading,
        ) {
            Text("Refresh")
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }

        errorMessage?.let {
            Text(text = it, modifier = Modifier.padding(top = 16.dp))
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .appiumTag("users_list"),
        ) {
            items(users, key = { it.id }) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("ID: ${user.id}", style = MaterialTheme.typography.labelMedium)
                        Text(
                            user.name,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                        Text("Email: ${user.email}", modifier = Modifier.padding(top = 4.dp))
                        Text("Role: ${user.role}", modifier = Modifier.padding(top = 4.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(top = 12.dp),
                        ) {
                            Button(
                                onClick = { onEditUser(user) },
                                modifier = Modifier.appiumTag("edit_user_${user.id}"),
                            ) {
                                Text("Edit")
                            }
                            Button(
                                onClick = { userToDelete = user },
                                modifier = Modifier.appiumTag("delete_user_button_${user.id}"),
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }

    userToDelete?.let { user ->
        AlertDialog(
            modifier = Modifier.appiumTag("confirmation_dialog"),
            onDismissRequest = { userToDelete = null },
            title = { Text("Delete user?") },
            text = { Text("Delete ${user.name}?") },
            confirmButton = {
                TextButton(
                    modifier = Modifier.appiumTag("confirm_delete_button"),
                    onClick = {
                        userToDelete = null
                        scope.launch {
                            try {
                                ApiClient.usersApi.deleteUser(user.id, "Bearer $token")
                                onRefresh()
                            } catch (error: Exception) {
                                errorMessage = requestErrorMessage(error, "delete user")
                            }
                        }
                    },
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { userToDelete = null }) {
                    Text("Cancel")
                }
            },
        )
    }
}

@Composable
private fun UserFormScreen(
    token: String,
    user: UserResponse?,
    onSaved: () -> Unit,
    onCancel: () -> Unit,
) {
    var name by remember(user) { mutableStateOf(user?.name.orEmpty()) }
    var email by remember(user) { mutableStateOf(user?.email.orEmpty()) }
    var password by remember(user) { mutableStateOf("") }
    var role by remember(user) { mutableStateOf(user?.role ?: "USER") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isSaving by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
            .appiumTag(if (user == null) "create_user_screen" else "edit_user_screen"),
    ) {
        Text(
            text = if (user == null) "Create user" else "Edit user",
            style = MaterialTheme.typography.headlineMedium,
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .appiumEditableTag("user_name", name) { name = it },
            label = { Text("Name") },
            singleLine = true,
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .appiumEditableTag("user_email", email) { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
        )
        if (user == null) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .appiumEditableTag("user_password", password) { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
            )
        }
        OutlinedTextField(
            value = role,
            onValueChange = { role = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .appiumEditableTag("user_role", role) { role = it },
            label = { Text("Role (USER or ADMIN)") },
            singleLine = true,
        )

        errorMessage?.let {
            Text(text = it, modifier = Modifier.padding(top = 16.dp))
        }

        Button(
            onClick = {
                focusManager.clearFocus()
                keyboardController?.hide()
                val normalizedRole = role.trim().uppercase()
                if (name.isBlank() || email.isBlank() || (user == null && password.isBlank())) {
                    errorMessage = "Fill in all required fields"
                    return@Button
                }
                if (normalizedRole != "USER" && normalizedRole != "ADMIN") {
                    errorMessage = "Role must be USER or ADMIN"
                    return@Button
                }

                scope.launch {
                    isSaving = true
                    errorMessage = null
                    try {
                        if (user == null) {
                            ApiClient.usersApi.createUser(
                                authorization = "Bearer $token",
                                request = CreateUserRequest(
                                    name = name.trim(),
                                    email = email.trim(),
                                    password = password,
                                    role = normalizedRole,
                                ),
                            )
                        } else {
                            ApiClient.usersApi.updateUser(
                                id = user.id,
                                authorization = "Bearer $token",
                                request = UpdateUserRequest(
                                    name = name.trim(),
                                    email = email.trim(),
                                    role = normalizedRole,
                                ),
                            )
                        }
                        onSaved()
                    } catch (error: Exception) {
                        errorMessage = requestErrorMessage(
                            error,
                            if (user == null) "create user" else "update user",
                        )
                    } finally {
                        isSaving = false
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .appiumTag("save_user_button"),
            enabled = !isSaving,
        ) {
            Text("Save")
        }

        TextButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Cancel")
        }

        if (isSaving) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

private fun requestErrorMessage(error: Exception, action: String): String = when (error) {
    is HttpException -> when (error.code()) {
        400 -> "Could not $action: check the entered values"
        401, 403 -> "Could not $action: you are not authorized"
        404 -> "Could not $action: user not found"
        409 -> "Could not $action: email already exists"
        else -> "Could not $action (server error ${error.code()})"
    }

    is IOException -> "Could not $action: cannot connect to the server"
    else -> "Could not $action"
}
