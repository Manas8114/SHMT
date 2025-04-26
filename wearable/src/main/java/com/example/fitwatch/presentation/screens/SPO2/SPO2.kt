package com.example.fitwatch.presentation.screens.spo2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.ui.text.font.FontWeight
import com.example.fitwatch.R

@Composable
fun SPO2Screen() {
    var spo2 by remember { mutableStateOf(98) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            spo2 = (96..100).random()
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("SPO2 Monitor") })
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_heart_rate),
                    contentDescription = "SPO2 Icon",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("$spo2%", fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Monitoring oxygen levels...", fontSize = 18.sp, color = MaterialTheme.colors.primary)
            }
        }
    }
}
