package com.example.fitwatch.presentation.screens.steptracker

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
fun StepTrackerScreen() {
    var steps by remember { mutableStateOf(500) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            steps += (5..15).random()
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Step Tracker") })
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "Step Icon",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("$steps Steps", fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Keep moving!", fontSize = 18.sp, color = MaterialTheme.colors.primary)
            }
        }
    }
}
