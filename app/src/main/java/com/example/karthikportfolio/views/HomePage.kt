package com.example.karthikportfolio.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.karthikportfolio.model.homePageViewModel
import com.example.karthikportfolio.utils.DrawerScreenList
import com.example.karthikportfolio.utils.NavigationHost
import com.example.karthikportfolio.utils.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val viewModel: homePageViewModel = viewModel()
    val currentScreen = viewModel.currentScreen.value
    var title by remember { mutableStateOf(currentScreen.title) }

    ModalNavigationDrawer(
        drawerState = drawerState, drawerContent = {
            // You can replace this with your actual navigation content
            Surface( // This gives it a solid background
                modifier = Modifier.fillMaxHeight(), color = Color.White
            ) {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(DrawerScreenList) {
                        DrawerItem(
                            selected = (currentRoute == it.dRoute),
                            item = it,
                            onDrawerItemClicked = {
                                scope.launch { drawerState.close() }
                                navController.navigate(it.dRoute)
                                title = it.dTitle
                            })
                    }
                }
            }
        }) {
        BodyContent(title, scope, drawerState, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(
    title: String, scope: CoroutineScope, drawerState: DrawerState, navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ), navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu, contentDescription = "Menu"
                        )
                    }
                })
        }, modifier = Modifier
            .fillMaxSize()
            .padding()
    ) { innerPadding ->

        NavigationHost(navController = navController, innerPadding)
    }
}

@Composable
fun DrawerItem(
    selected: Boolean, item: Screen.DrawerScreen, onDrawerItemClicked: () -> Unit
) {
    val backgroundColor: Color = if (selected) Color.LightGray else Color.White
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(backgroundColor)
            .fillMaxWidth()
            .clickable {
                onDrawerItemClicked()
            }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(item.dTitle, fontSize = 18.sp, fontFamily = FontFamily.Monospace)
    }
}