package com.bauer_jakob.intershareapp.presentation.components

import android.util.Half.toFloat
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseLoading(
){
    val pulseColor = MaterialTheme.colorScheme.primary
    val centreColor = MaterialTheme.colorScheme.secondary
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val durationMillis:Int = 1800
    val minPulseSize:Float = 50f
    val pulseCount = 3
    val infiniteTransition = rememberInfiniteTransition()

    val sizeList = mutableListOf<Float>()
    val alphaList = mutableListOf<Float>()

    for (i in 1..pulseCount+1) {
        val size by infiniteTransition.animateFloat(
            initialValue = minPulseSize,
            targetValue = screenWidth.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset((durationMillis / pulseCount) * i)
            )
        )
        sizeList.add(size)

        val alpha by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset((durationMillis / pulseCount) * i)
            )
        )
        alphaList.add(alpha)
    }

    Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()) {
        for (i in 0..pulseCount){
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(sizeList[i].dp)
                    .align(Alignment.Center)
                    .alpha(alphaList[i]),
                colors = CardDefaults.cardColors(pulseColor),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {}
        }
        Card(modifier = Modifier
            .size(minPulseSize.dp)
            .align(Alignment.Center),
            shape = CircleShape,
            colors = CardDefaults.cardColors(centreColor)){}
    }
}
