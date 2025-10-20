package com.pkmn.app.ui.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pkmn.app.R
import com.pkmn.app.ui.component.CustomEditTextRounded
import com.pkmn.app.ui.component.CustomRoundedButton
import com.pkmn.app.ui.theme.ColorWhite
import com.pkmn.app.ui.theme.Gray95
import com.pkmn.app.ui.theme.Grey40

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Boolean
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 10.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        EmailTextField(emailValue = email, onValueChange = { email = it })

        Spacer(modifier = Modifier.height(12.dp))

        PasswordTextField(passwordValue = password, onValueChange = { password = it })

        Spacer(modifier = Modifier.height(12.dp))

        NameTextField(nameValue = email, onValueChange = { name = it })

        Spacer(modifier = Modifier.height(32.dp))

        RegisterButton({
            /**
             * Viewmodel todo
             */
        })

    }

}

@Composable
fun NameTextField(nameValue: String, onValueChange: (String) -> Unit) {
    CustomEditTextRounded(
        value = nameValue,
        onValueChange = onValueChange,
        hint = stringResource(id = R.string.full_name),
        keyboardType = KeyboardType.Text,
        containerColor = Gray95,
    )
}

@Composable
fun EmailTextField(emailValue: String, onValueChange: (String) -> Unit) {
    CustomEditTextRounded(
        value = emailValue,
        onValueChange = onValueChange,
        hint = stringResource(id = R.string.email),
        keyboardType = KeyboardType.Email,
        containerColor = Gray95,
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
        hint = stringResource(id = R.string.password),
        containerColor = Gray95,
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
fun RegisterButton(
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomRoundedButton(
            btnText = stringResource(id = R.string.register),
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