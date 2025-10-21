package com.pkmn.app.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pkmn.app.R
import com.pkmn.app.navigation.AppRoute
import com.pkmn.app.ui.component.CustomEditTextRounded
import com.pkmn.app.ui.component.CustomRoundedButton
import com.pkmn.app.ui.theme.Alert_Error
import com.pkmn.app.ui.theme.ColorBlack
import com.pkmn.app.ui.theme.ColorWhite
import com.pkmn.app.ui.theme.Gray95
import com.pkmn.app.ui.theme.Grey40


@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiData by viewModel.uiData.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 10.dp),
        content = {
            when (uiState) {
                LoginUiState.Idle -> {
                    Spacer(modifier = Modifier.height(96.dp))

                    Text(
                        text = stringResource(R.string.login),
                        style = TextStyle(
                            color = ColorBlack,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    EmailTextField(emailValue = uiData.email, onValueChange = { viewModel.onEmailChange(it) })

                    Spacer(modifier = Modifier.height(12.dp))

                    PasswordTextField(passwordValue = uiData.password, onValueChange = { viewModel.onPasswordChange(it) })

                    uiData.errorMessage.let {
                        if (it.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(32.dp))

                            Text(
                                text = it,
                                style = TextStyle(
                                    color = Alert_Error,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    LoginButton({
                        viewModel.login()
                    })

                    Spacer(modifier = Modifier.height(32.dp))

                    SingUpTextField(onClick = {
                        navController.navigate(AppRoute.RegisterRoute.id)
                    })

                }
                LoginUiState.Loading -> {
                    Box(
                        modifier = modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                LoginUiState.Success -> {
                    navController.navigate(AppRoute.HomeRoute.id) {
                        popUpTo(AppRoute.LoginRoute.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ShowLeadingIcon(imageVector: ImageVector) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        modifier = Modifier.padding(start = 10.dp),
        tint = Grey40
    )
}

@Composable
fun EmailTextField(emailValue: String, onValueChange: (String) -> Unit) {
    CustomEditTextRounded(
        value = emailValue,
        onValueChange = onValueChange,
        hint = stringResource(id = R.string.enter_email),
        keyboardType = KeyboardType.Email,
        containerColor = Gray95,
        leadingIcon = {
            ShowLeadingIcon(Icons.Default.Email)
        },
    )
}

@Composable
fun PasswordTextField(passwordValue: String, onValueChange: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    CustomEditTextRounded(
        value = passwordValue,
        keyboardType = KeyboardType.Password,
        onValueChange = onValueChange,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        hint = stringResource(id = R.string.enter_password),
        containerColor = Gray95,
        leadingIcon = {
            ShowLeadingIcon(Icons.Default.Lock)
        },
        trailingIcon = {
            val image =
                if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = image,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 10.dp),
                    tint = Grey40
                )
            }
        }
    )
}

@Composable
fun LoginButton(
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomRoundedButton(
            btnText = stringResource(id = R.string.login),
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(56.dp)
                .border(
                    BorderStroke(width = 0.dp, color = ColorWhite),
                    shape = RoundedCornerShape(30.dp)
                ),
            textColor = ColorWhite,
            fontSize = 16.sp,
            onBtnClick = onClick,
            btnElevation = 8.dp,
        )

    }
}

@Composable
fun SingUpTextField(onClick: () -> Unit) {
    Row {
        Text(
            text = stringResource(id = R.string.dont_have_account),
            style = TextStyle(
                color = Grey40,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            modifier = Modifier.clickable(
                enabled = true,
                onClick = onClick
            ),
            text = stringResource(id = R.string.sign_up),
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }

}