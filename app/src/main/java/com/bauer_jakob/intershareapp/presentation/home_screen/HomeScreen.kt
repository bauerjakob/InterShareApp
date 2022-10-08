package com.bauer_jakob.intershareapp.presentation.home_screen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.bauer_jakob.intershareapp.presentation.components.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            PulseLoading()
            Spacer(Modifier.height(20.dp))
            Card(modifier = Modifier.padding(20.dp, 10.dp)) {
                Button(onClick = {}) {
                    Text("Send")
                }
            }
        }


        if (state.isLoading) {
        }
    }
}