package com.revenuee.tyeventj.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController


object MainDestinations {

    const val HOME_ROUTE = "home"

}


@Composable
fun rememberCaririPlusNavController(
    navController: NavController = rememberNavController()
): CaririPlusNavController = remember(navController) {
    CaririPlusNavController(navController)
}


@Stable
class CaririPlusNavController(
    val navController: NavController
) {


    fun userUpPress() {
        navController.navigateUp()
    }


    fun navigateBottomBarRoute(route: String) {
        if (route != navController.currentDestination?.route) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true

                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToDetails(detailId: Long, origin: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.HOME_ROUTE}/$detailId?origin=$origin")
        }
    }

    private fun NavBackStackEntry.lifecycleIsResumed(): Boolean {
        val life: Lifecycle = this.lifecycle
        return life.currentState == Lifecycle.State.RESUMED
    }

    private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)


    private tailrec fun findStartDestination(graph: NavDestination): NavDestination{
        return if( graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
    }
}


