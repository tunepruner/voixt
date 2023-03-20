package com.tunepruner.voixt.editor.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.tunepruner.voixt.Navigation
import com.tunepruner.voixt.R
import com.tunepruner.voixt.Screen
import com.tunepruner.voixt.ui.theme.VoixtTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VoixtTheme {
                val navController = rememberNavController()

                var navState by remember {
                    mutableStateOf<Pair<Screen?, Screen?>>(null to Screen.HomeScreen)
                }

                val setState: (Screen) -> Unit = { screen -> navState = navState.second to screen }
                Navigation(navController = navController, navState = navState, setNavStateTo = setState)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        VoixtTheme {
//            MainScreen(false)
        }
    }

//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    fun MainScreen(withSelection: Boolean) {
//        val drawerState = rememberDrawerState(DrawerValue.Closed)
//        val scope = rememberCoroutineScope()
//
//        ModalNavigationDrawer(
//            drawerState = drawerState,
//            drawerContent = {
//                ModalDrawerSheet(
////                    drawerShape = …,
////                drawerTonalElevation = …,
////                drawerContainerColor = …,
////                drawerContentColor = …,
//                    content = {
//                        Column(modifier = Modifier.padding(40.dp)) {
//                            Text(
//                                stringResource(id = R.string.app_name),
//                                fontSize = 50.sp,
////                                modifier = Modifier.padding(start = 20.dp)
//                            )
//                            Spacer(modifier = Modifier.size(30.dp))
//                            Button(onClick = {}, content = {
//                                Icon(
//                                    Icons.Default.Home, "Settings button"
//                                )
//                                Text("Home")
//                            })
//                            Button(onClick = {}, content = {
//                                Icon(
//                                    Icons.Default.List, "Settings button"
//                                )
//                                Text("Saved voixts")
//                            })
//                            Button(onClick = {}, content = {
//                                Icon(
//                                    Icons.Default.Settings, "Settings button"
//                                )
//                                Text("Settings")
//                            })
//                        }
//                    }
//                )
//            },
//            gesturesEnabled = true,
////         scrimColor = MaterialTheme.colorScheme.scrim,
//            content = {
//                Scaffold(
//                    content = {
//                        Surface(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(it),
//                            color = MaterialTheme.colorScheme.background,
//                        ) {
//                            Column {
//                                TopBar(drawerState)
//                                EditScreen(true)
//                            }
//                        }
//                    }
//                )
//            }
//        )
//    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(drawerState: DrawerState) {
        val scope = rememberCoroutineScope()
        var isExpanded by remember { mutableStateOf(false) }
        val expandedHeight = 250.dp
        val collapsedHeight = 100.dp
        val expandedTitleHeight = 100.sp
        val collapsedTitleHeight = 20.sp

        LargeTopAppBar(
            title = {

            },
            navigationIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                        .padding(start = 10.dp),
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowRight, contentDescription = "Menu",
                    )
                    Text(
                        stringResource(id = R.string.app_name),
                        fontSize = if (isExpanded) expandedTitleHeight else collapsedTitleHeight
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        isExpanded = !isExpanded
                    }
                ) {
                    Icon(
                        if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand/Collapse"
                    )
                }

//                MyDropdownMenu(isExpanded, scope)
            },
            modifier = Modifier.height(if (isExpanded) expandedHeight else collapsedHeight)
        )
    }

    @Composable
    private fun MyDropdownMenu(isExpanded: Boolean, scope: CoroutineScope) {
        var isExpanded1 = isExpanded
        DropdownMenu(
            expanded = isExpanded1,
            onDismissRequest = { isExpanded1 = false },
        ) {
            DropdownMenuItem(
                text = { Text("One") },
                onClick = {
                    scope.launch { println("testing 12345") }
                })
            DropdownMenuItem(
                text = { Text("Two") },
                onClick = {
                    scope.launch { println("testing 12345") }
                })
            DropdownMenuItem(
                text = { Text("Three") },
                onClick = {
                    scope.launch { println("testing 12345") }
                })
        }
    }

}