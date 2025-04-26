package com.example.fitwatch.presentation.screens.heartrate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.fitwatch.R // Make sure you have a heart icon in your drawable folder

@Composable
fun HeartRateScreen() {
    var pulse by remember { mutableStateOf(72) } // Fake default pulse

    // Simulate fluctuating heart rate
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            pulse = (65..90).random()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Heart Rate") })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_heart_rate), // replace with your actual drawable
                    contentDescription = "Heart Icon",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "$pulse bpm",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Measuring...",
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}
