package com.javohir.fizmasofttask.presentation.auth.login
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.javohir.fizmasofttask.presentation.auth.login.component.BirthDateField
import com.javohir.fizmasofttask.presentation.auth.login.component.PassportField

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.login
 * Description: Composable Screen
 */

@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    navigateToFaceDetection: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when(event){
                LoginEvent.NavigateToFaceDetection -> {
                    navigateToFaceDetection()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6FBF3))
            .clickable { focusManager.clearFocus() }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF326940))
            ) {
                Text(
                    text = "M",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Xush kelibsiz",
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF171D18)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Davom etish uchun shaxsiy ma'lumotlaringizni kiriting",
                fontSize = 15.sp,
                color = Color(0xFF414941),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(34.dp))


            BirthDateField(
                value = state.birthData,
                onValueChange = {
                    viewModel.onAction(LoginIntent.BirthDataChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(22.dp))


            PassportField(
                value = state.passport,
                isVisible = state.isPassportVisible,
                onValueChange = {
                    viewModel.onAction(LoginIntent.PassportChanged(it))
                },
                onToggleVisibility = {
                    viewModel.onAction(LoginIntent.TogglePassportVisibility)
                }
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = Color(0xFF326940),
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Ma'lumotlar serverga yuborilmaydi",
                    fontSize = 12.sp,
                    color = Color(0xFF414941)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.onAction(LoginIntent.LoginClicked)
                },
                enabled = state.isValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.isValid) Color(0xFF326940) else Color(0xFFDBE5DB),
                    disabledContainerColor = Color(0xFFDBE5DB)
                )
            ) {
                Text(
                    text = "Davom etish",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (state.isValid) Color.White else Color(0xFF8A938B)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = if (state.isValid) Color.White else Color(0xFF8A938B),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Ma'lumotlar serverga yuborilmaydi",
                fontSize = 12.sp,
                color = Color(0xFF414941),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
@Composable
@Preview(showBackground = true)
fun LoginPreview(){
    LoginScreen(
        paddingValues = PaddingValues(all = 12.dp),
        navigateToFaceDetection = {}
    )
}
