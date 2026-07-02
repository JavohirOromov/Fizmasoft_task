package com.javohir.fizmasofttask.presentation.auth.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.login.component
 * Description: PassportField component
 */

@Composable
fun PassportField(
    value: String,
    isVisible: Boolean,
    onValueChange: (String) -> Unit,
    onToggleVisibility: () -> Unit,
    isError: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = when {
        isError -> Color.Red
        isFocused -> Color(0xFF326940)
        else -> Color(0xFF717971)
    }

    Column {
        Text(
            text = "Passport ma'lumoti",
            fontSize = 12.sp,
            color = if (isError) Color.Red else Color(0xFF414941),
            modifier = Modifier
                .padding(start = 12.dp, bottom = 4.dp)
                .background(Color(0xFFF6FBF3))
                .padding(horizontal = 4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = Color(0xFF326940),
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged { isFocused = it.isFocused },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    color = Color(0xFF171D18),
                    letterSpacing = 0.5.sp
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                visualTransformation = if (isVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                singleLine = true,
                decorationBox = { inner ->
                    if (value.isEmpty()) {
                        Text(
                            text = "AA1234567",
                            fontSize = 16.sp,
                            color = Color(0xFF8A938B)
                        )
                    }
                    inner()
                }
            )
            IconButton(
                onClick = onToggleVisibility,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = if (isVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null,
                    tint = Color(0xFF414941),
                    modifier = Modifier.size(22.dp)
                )
            }
        }
        if (isError) {
            Text(
                text = "Format: 2 ta harf + 7 ta raqam (masalan: AE4104050)",
                fontSize = 11.sp,
                color = Color.Red,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}