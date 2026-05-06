package com.example.indianspice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(onGetStarted: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1A0500),
                    Color(0xFF6B1010),
                    Color(0xFFD62828),
                    Color(0xFFF77F00)
                )
            )
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("🌶️  INDIAN SPICE",
                color = Color.White.copy(alpha = 0.95f),
                style = MaterialTheme.typography.labelLarge,
                letterSpacing = 4.sp,
                fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(40.dp))
            Box(modifier = Modifier.fillMaxWidth().height(260.dp),
                contentAlignment = Alignment.Center) {
                Text("🫙", fontSize = 140.sp)
            }
            Spacer(Modifier.height(32.dp))
            Text("EXPLORE THE\nWORLD OF\nFLAVOUR",
                color = Color.White,
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Black,
                lineHeight = 50.sp)
            Spacer(Modifier.height(16.dp))
            Text("Premium spices & herbs sourced\ndirectly from their origin.",
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(40.dp))
            Button(
                onClick = onGetStarted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFD62828)
                ),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier.height(52.dp).fillMaxWidth(0.65f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Explore Now",
                    fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
        }
    }
}