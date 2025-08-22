package com.example.karthikportfolio.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.karthikportfolio.model.homePageViewModel
import com.example.karthikportfolio.utils.BottomScreenList
import com.example.karthikportfolio.utils.DrawerScreenList
import com.example.karthikportfolio.utils.NavigationHost
import com.example.karthikportfolio.utils.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.karthikportfolio.R

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
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 100.dp), color = Color.White
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
        BodyContent(
            title, scope, drawerState, navController, route = currentRoute ?: "", { title = it })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(
    title: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavController,
    route: String,
    setTitle: (String) -> Unit
) {
    Scaffold(
        bottomBar = { BottomBar(route, navController, setTitle) }, topBar = {
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
    val backgroundColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    else Color.Transparent

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onDrawerItemClicked() },
        color = backgroundColor,
        shape = RoundedCornerShape(8.dp),
        tonalElevation = if (selected) 2.dp else 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.dTitle,
                tint = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.dTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Monospace,
                color = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun BottomBar(
    route: String, navController: NavController, setTitle: (String) -> Unit
) {
    NavigationBar(
        Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp)),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        BottomScreenList.forEach { item ->
            NavigationBarItem(
                selected = route == item.bRoute, onClick = {
                navController.navigate(item.bRoute)
                setTitle(item.bTitle)
            }, icon = {
                Icon(
                    painter = painterResource(item.icon), contentDescription = item.bTitle
                )
            }, label = { Text(item.bTitle) }, colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.White,
                selectedTextColor = Color.LightGray,
                unselectedTextColor = Color.White
            )
            )
        }
    }
}