package com.example.fitwatch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import com.example.fitwatch.presentation.ClientDataViewModel
import com.example.fitwatch.presentation.data.Features
import com.example.fitwatch.presentation.screens.caloriestracking.CaloriesScreen
import com.example.fitwatch.presentation.screens.caloriestracking.CaloriesTrackingScreen
import com.example.fitwatch.presentation.screens.featureslist.FeaturesList
import com.example.fitwatch.presentation.screens.meals.MealsScreen
import com.example.fitwatch.presentation.screens.nutrients.NutrientsScreen
import com.example.fitwatch.presentation.screens.summarylist.SummaryScreen
import com.example.fitwatch.presentation.screens.watertracking.WaterTrackingScreen
import com.google.android.gms.wearable.DataClient

@Composable
fun FitWatchNavHost(
    navController: NavHostController,
    viewModel: ClientDataViewModel,
    dataClient: DataClient,
    onStartHandheldActivity: () -> Unit,
) {
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = Screens.FeatureListScreen.route,
    ) {
        composable(Screens.FeatureListScreen.route) {
            FeaturesList { selectedItem ->
                when (selectedItem) {
                    Features.VIEW_SUMMARY.displayName -> {
                        navController.navigate(Screens.SummaryScreen.route)
                    }

                    Features.NUTRIENTS.displayName -> {
                        navController.navigate(Screens.NutrientsScreen.route)
                    }

                    Features.ADD_CALORIES.displayName -> {
                        navController.navigate(Screens.CaloriesScreen.route)
                    }

                    Features.ADD_WATER.displayName -> {
                        navController.navigate(Screens.WaterTrackingScreen.route)
                    }

                    Features.OPEN_ON_PHONE.displayName -> {
                        onStartHandheldActivity()
                    }
                    Features.SPO2.displayName -> {
                        onStartHandheldActivity()
                    }
                    Features.Heart_rate.displayName -> {
                        onStartHandheldActivity()
                    }
                    Features.Steps_traker.displayName -> {
                        onStartHandheldActivity()
                    }
                }
            }
        }

        composable(Screens.SummaryScreen.route) {
            SummaryScreen(viewModel = viewModel)
        }

        composable(Screens.CaloriesScreen.route) {
            CaloriesScreen(viewModel = viewModel) {
                navController.navigate(Screens.MealsScreen.route)
            }
        }

        composable(Screens.MealsScreen.route) {
            MealsScreen() { selectedMeal ->
                navController.navigate("calories_tracking_screen/$selectedMeal")
            }
        }

        composable(
            Screens.CaloriesTrackingScreen.route,
            arguments = listOf(
                navArgument("selectedMeal") {
                    type = NavType.StringType
                }
            )
        ) {
            val selectedMeal = it.arguments?.getString("selectedMeal")!!
            CaloriesTrackingScreen(viewModel = viewModel, meal = selectedMeal) {cal ->
                viewModel.updateCalories(dataClient = dataClient, calories = cal)
                navController.popBackStack(
                    route = Screens.CaloriesScreen.route,
                    inclusive = false
                )
            }
        }

        composable(Screens.WaterTrackingScreen.route) {
            WaterTrackingScreen(viewModel = viewModel, dataClient = dataClient)
        }

        composable(Screens.NutrientsScreen.route) {
            NutrientsScreen()
        }
    }
}


