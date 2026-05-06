package com.example.indianspice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indianspice.data.CartViewModel
import com.example.indianspice.data.ProductRepository
import com.example.indianspice.ui.components.ProductCard
import com.example.indianspice.ui.theme.SpiceRed
import com.example.indianspice.ui.theme.SpiceYellow

@Composable
fun HomeScreen(
    cartViewModel: CartViewModel,
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("All") }
    val products = remember(selectedCategory) {
        if (selectedCategory == "All") ProductRepository.products
        else ProductRepository.products.filter { it.category == selectedCategory }
    }

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 20.dp, end = 20.dp,
                top = 16.dp, bottom = 96.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar
            item(span = { GridItemSpan(2) }) {
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { /* Could open a drawer */ }) {
                            Icon(Icons.Default.Menu, "Menu", tint = SpiceRed)
                        }
                        Column {
                            Text("Hello, Foodie 👋",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("Indian Spice",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = SpiceRed)
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Surface(shape = CircleShape,
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.size(44.dp)) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Search, "Search") }
                        }
                        Surface(shape = CircleShape, color = SpiceRed,
                            modifier = Modifier.size(44.dp)
                                .clickable(onClick = onCartClick)) {
                            Box(contentAlignment = Alignment.Center) {
                                BadgedBox(badge = {
                                    if (cartViewModel.itemCount > 0)
                                        Badge(containerColor = Color.White,
                                            contentColor = SpiceRed) {
                                            Text("${cartViewModel.itemCount}",
                                                fontSize = 10.sp) }
                                }) {
                                    Icon(Icons.Default.ShoppingBag,
                                        "Cart", tint = Color.White)
                                }
                            }
                        }
                    }
                }
            }

            // Hero banner
            item(span = { GridItemSpan(2) }) {
                Surface(shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.fillMaxWidth().height(170.dp)) {
                    Box(Modifier.fillMaxSize().background(
                        Brush.linearGradient(listOf(
                            Color(0xFF6B1010), Color(0xFFD62828))))) {
                        Row(Modifier.fillMaxSize().padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f)) {
                                Surface(shape = RoundedCornerShape(50),
                                    color = Color.White.copy(alpha = 0.2f)) {
                                    Text("⭐ Best Seller",
                                        Modifier.padding(
                                            horizontal = 12.dp, vertical = 4.dp),
                                        color = SpiceYellow,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold)
                                }
                                Spacer(Modifier.height(8.dp))
                                Text("Kashmiri\nSaffron",
                                    color = Color.White,
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    lineHeight = 28.sp)
                                Spacer(Modifier.height(10.dp))
                                Surface(shape = RoundedCornerShape(50),
                                    color = Color.White,
                                    modifier = Modifier.clickable { onProductClick(1) }) {
                                    Text("Shop Now",
                                        Modifier.padding(
                                            horizontal = 16.dp, vertical = 6.dp),
                                        color = SpiceRed,
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold)
                                }
                            }
                            Text("🌺", fontSize = 80.sp,
                                modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }

            // Category chips
            item(span = { GridItemSpan(2) }) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(ProductRepository.categories) { cat ->
                        val sel = cat == selectedCategory
                        Surface(shape = RoundedCornerShape(50),
                            color = if (sel) SpiceRed
                            else MaterialTheme.colorScheme.surface,
                            modifier = Modifier.clickable { selectedCategory = cat }) {
                            Text(cat,
                                Modifier.padding(
                                    horizontal = 18.dp, vertical = 10.dp),
                                color = if (sel) Color.White
                                else MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            // Section header
            item(span = { GridItemSpan(2) }) {
                Row(Modifier.fillMaxWidth(),
                    Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Text("Our Collection",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold)
                    Text("${products.size} items",
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            // Product grid
            items(products, key = { it.id }) { product ->
                ProductCard(
                    product = product,
                    onClick = { onProductClick(product.id) },
                    onQuickAdd = { cartViewModel.addToCart(product, "100g") }
                )
            }
        }
    }
}