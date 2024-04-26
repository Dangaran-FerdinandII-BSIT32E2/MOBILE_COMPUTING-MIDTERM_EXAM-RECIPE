package com.example.recipebook

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class RecipeBook() { //for routes
    Start,
    RecipeList
}

@Composable
fun RecipeApp( //the main App
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = RecipeBook.Start.name,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable(route = RecipeBook.Start.name) {
            RecipeStartScreen(
                onNextButtonClicked = {
                    navController.navigate(RecipeBook.RecipeList.name)
                }
            )
        } //route for RecipeStartScreen
        composable(route = RecipeBook.RecipeList.name) {
            RecipeListApp(
            )//the list of repices..
        }
    }
}

