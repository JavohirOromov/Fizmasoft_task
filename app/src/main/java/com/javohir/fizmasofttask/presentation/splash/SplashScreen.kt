package com.javohir.fizmasofttask.presentation.splash
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.javohir.fizmasofttask.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.splash
 * Description: Composable screen
 */

@Composable
fun SplashScreen(
    paddingValues: PaddingValues,
    onNavigate: (SplashDestination) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
){
    val destination by viewModel.destination.collectAsStateWithLifecycle()

    LaunchedEffect(destination) {
        destination?.let { onNavigate }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.splash_background)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(0.6f))
            Box(
                modifier = Modifier
                    .size(104.dp)
                    .clip(RoundedCornerShape(size = 30.dp))
                    .background(colorResource(id = R.color.card_color)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "M",
                    fontSize = 58.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.splash_background),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.mini_app),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                letterSpacing = 0.3.sp
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.sub_title),
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 120.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White.copy(alpha = 0.24f))
                ) {
                    var progress by remember { mutableFloatStateOf(0f) }

                    LaunchedEffect(Unit) {
                        while (progress < 1f){
                            progress = if (progress < 0.42f){
                                progress + 0.03f
                            }else if (progress < 0.55f){
                                progress + 0.05f
                            }else{
                                progress + 0.02f
                            }
                            delay(30.milliseconds)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color.White)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.loading),
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.6f),

            )
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}
@Composable
@Preview(showBackground = true)
fun SplashPreview(){
    SplashScreen(
        paddingValues = PaddingValues(all = 12.dp),
        onNavigate = {}
    )
}