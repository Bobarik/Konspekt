package com.bobarik.konspekt.login.screen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bobarik.konspekt.login.screen.mvi.LoginEvent
import com.bobarik.konspekt.login.screen.mvi.LoginState

@Composable
internal fun LoginScreenContent(
  stateProvider: () -> LoginState,
  onEvent: LoginEvent.() -> Unit,
) = Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {

  Row(
    horizontalArrangement = Arrangement.Center,
  ) {
    Text(
      text = "Login",
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(16.dp),
    )
  }

  OutlinedTextField(
    value = stateProvider().login,
    onValueChange = {
      LoginEvent.OnLoginChanged(it).onEvent()
    },
    label = { Text("Email") },
    singleLine = true,
    modifier = Modifier.fillMaxWidth().padding(16.dp),
  )

  OutlinedTextField(
    value = stateProvider().password,
    onValueChange = {
      LoginEvent.OnPasswordChanged(it).onEvent()
    },
    label = { Text("Password") },
    singleLine = true,
    visualTransformation = if (stateProvider().isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Password,
    ),
    trailingIcon = {
      IconButton(
        onClick = {
          LoginEvent.OnPasswordVisibilityChanged.onEvent()
        },
      ) {
        val imageVector =
          if (stateProvider().isPasswordVisible) Icons.Default.Close else Icons.Default.Edit
        Icon(
          imageVector,
          contentDescription = if (stateProvider().isPasswordVisible) "Hide password" else "Show password",
        )
      }
    },
  )

  Button(
    onClick = { /* Handle login logic here */ },
    modifier = Modifier.fillMaxWidth().padding(16.dp),
  ) {
    Text("Login")
  }

  TextButton(
    onClick = { },
    modifier = Modifier.fillMaxWidth().padding(16.dp),
  ) {
    Text("Open github")
  }

  TextButton(
    onClick = {
      LoginEvent.OnHomeNavigateClick.onEvent()
    },
    modifier = Modifier.fillMaxWidth().padding(16.dp),
  ) {
    Text("Open home screen")
  }
}
