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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.login.component
 * Description: BirthDate component
 */
@Composable
fun BirthDateField(
    value: String,
    onValueChange: (String) -> Unit,
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
            text = "Tug'ilgan sana",
            fontSize = 12.sp,
            color = if (isError) Color.Red else Color(0xFF326940),
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
                imageVector = Icons.Default.CalendarToday,
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
                    color = Color(0xFF171D18)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = DateVisualTransformation,
                singleLine = true,
                decorationBox = { inner ->
                    if (value.isEmpty()) {
                        Text(
                            text = "DD.MM.YYYY",
                            fontSize = 16.sp,
                            color = Color(0xFF8A938B)
                        )
                    }
                    inner()
                }
            )
            if (value.isNotEmpty()) {
                IconButton(
                    onClick = { onValueChange("") },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = Color(0xFF8A938B),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
        if (isError) {
            Text(
                text = "Format: DD.MM.YYYY (masalan: 10.10.2005)",
                fontSize = 11.sp,
                color = Color.Red,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}

/**
 * Raw raqamlarni ("10102005") ekranda "10.10.2005" ko'rinishida ko'rsatadi.
 * State'da faqat raqamlar saqlangani uchun kursor nuqtalar tufayli sakramaydi.
 */
private val DateVisualTransformation = object : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.take(8)

        val masked = buildString {
            digits.forEachIndexed { index, c ->
                append(c)
                if ((index == 1 || index == 3) && index != digits.lastIndex) append('.')
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var transformed = offset
                if (offset > 2) transformed += 1
                if (offset > 4) transformed += 1
                return transformed.coerceAtMost(masked.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                var original = offset
                if (offset > 2) original -= 1
                if (offset > 5) original -= 1
                return original.coerceIn(0, digits.length)
            }
        }

        return TransformedText(AnnotatedString(masked), offsetMapping)
    }
}