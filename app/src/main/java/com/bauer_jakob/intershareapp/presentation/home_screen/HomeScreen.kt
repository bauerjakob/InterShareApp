package com.bauer_jakob.intershareapp.presentation.home_screen
import ToggleButton
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import com.bauer_jakob.intershareapp.presentation.components.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Contacts", "Settings")

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Row {
                        Text("Inter");
                        Text("Share", fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    AssistChip(
                        onClick = { },
                        label = {
                            Text(text = "Connected")
                        },
                        leadingIcon = {
                            Icon(Icons.Rounded.Check, contentDescription = "Hello World")
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                    );
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Localized description"
                        )
                    };
                })
        },
        content = { paddingValues: PaddingValues ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
                horizontalAlignment = Alignment.Start,
                content = {
                    val screenWidth = LocalConfiguration.current.screenWidthDp
                    Layout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                alpha =
                                    1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 0.2f)
                                translationY = 0.7f * scrollState.value
                            },
                        content =  { PulseLoading() }
                    ) { measurable, constraints ->
                        val content = measurable[0].measure(constraints)
                        var contentWidth = content.width
                        var contentOffset = -(content.height / 1.85).toInt()
                        var contentHeight = content.height + contentOffset
                        layout(
                            contentWidth,
                            contentHeight
                        ) { //Change these per your needs
                            content.placeRelative(IntOffset(0, contentOffset))
                        }
                    }

                    Text("Share",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp)
                        )
                    FlowRow(
                        modifier = Modifier
                            .padding(horizontal = 17.dp, vertical = 10.dp)
                            .graphicsLayer { translationY },
                        crossAxisSpacing = 10.dp,
                        mainAxisSpacing = 15.dp,
                        mainAxisAlignment = MainAxisAlignment.Start,
                        crossAxisAlignment = FlowCrossAxisAlignment.Start) {
                        ExtendedFloatingActionButton(onClick = { }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("File")
                            }
                        }

                        ExtendedFloatingActionButton(onClick = { }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("Photo")
                            }
                        }
                        ExtendedFloatingActionButton(onClick = { }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("Video")
                            }
                        }
                        ExtendedFloatingActionButton(onClick = { }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("Clipboard")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Receive",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp)
                    )
                    Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
                        ToggleButton()
                    }

                    Card(
                        Modifier
                            .height(350.dp)
                            .padding(15.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Near devices",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                        )
                    }

                    Card(
                        Modifier
                            .height(350.dp)
                            .padding(15.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Near devices",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                        )
                    }

                }
            )


            if (state.isLoading) {
            }
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }

    );
}