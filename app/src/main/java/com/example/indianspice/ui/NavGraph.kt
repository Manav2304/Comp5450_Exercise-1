package com.example.indianspice.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.indianspice.data.CartViewModel
import com.example.indianspice.ui.screens.*

@Composable
fun IndianSpiceApp() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

    NavHost(navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingScreen {
                navController.navigate("home") {
                    popUpTo("onboarding") { inclusive = true }
                }
            }
        }
        composable("home") {
            HomeScreen(cartViewModel,
                onProductClick = { navController.navigate("detail/$it") },
                onCartClick = { navController.navigate("cart") })
        }
        composable("detail/{productId}",
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType })) {
            ProductDetailScreen(
                productId = it.arguments?.getInt("productId") ?: 1,
                cartViewModel = cartViewModel,
                onBack = { navController.popBackStack() },
                onCartClick = { navController.navigate("cart") })
        }
        composable("cart") {
            CartScreen(cartViewModel) { navController.popBackStack() }
        }
    }
}