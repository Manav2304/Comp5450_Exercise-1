package com.example.indianspice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indianspice.data.CartViewModel
import com.example.indianspice.data.ProductRepository
import com.example.indianspice.ui.components.parseHex
import com.example.indianspice.ui.theme.GoldStar
import com.example.indianspice.ui.theme.SpiceRed

@Composable
fun ProductDetailScreen(
    productId: Int,
    cartViewModel: CartViewModel,
    onBack: () -> Unit,
    onCartClick: () -> Unit
) {
    val product = ProductRepository.getById(productId) ?: return
    var selectedWeight by remember { mutableStateOf("100g") }
    var added by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 120.dp)) {

            // Top bar
            Row(Modifier.fillMaxWidth().padding(20.dp),
                Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Surface(shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(44.dp).clickable(onClick = onBack)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.ArrowBack, "Back") }
                }
                Text(product.category,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold)
                Surface(shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(44.dp).clickable(onClick = onCartClick)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.ShoppingBag, "Cart") }
                }
            }

            // Hero visual
            Box(Modifier.fillMaxWidth().height(260.dp),
                contentAlignment = Alignment.Center) {
                Box(Modifier.size(200.dp).clip(CircleShape)
                    .background(parseHex(product.colorHex).copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center) {
                    Box(Modifier.size(140.dp).clip(CircleShape)
                        .background(parseHex(product.colorHex).copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center) {
                        Box(Modifier.size(90.dp).clip(CircleShape)
                            .background(parseHex(product.colorHex)))
                    }
                }
                Box(Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 6.dp)) {
                    Text("🌍 ${product.origin}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF444444))
                }
            }

            Column(Modifier.padding(horizontal = 24.dp)) {
                Text(product.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, null,
                        tint = GoldStar, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("${product.rating}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.width(6.dp))
                    Text("(${product.reviewCount} reviews)",
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(Modifier.height(16.dp))
                Text(product.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(24.dp))
                Text("Select Weight:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(10.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(product.availableWeights) { w ->
                        val sel = w == selectedWeight
                        Surface(shape = RoundedCornerShape(50),
                            color = if (sel) SpiceRed
                            else MaterialTheme.colorScheme.surface,
                            modifier = Modifier.clickable {
                                selectedWeight = w; added = false }) {
                            Text(w,
                                Modifier.padding(
                                    horizontal = 20.dp, vertical = 10.dp),
                                fontWeight = FontWeight.Bold,
                                color = if (sel) Color.White
                                else MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            }
        }

        // Bottom bar
        Surface(shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 12.dp,
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
            Row(Modifier.fillMaxWidth().padding(20.dp),
                Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Column {
                    Text("Price per pack",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall)
                    Text("$%.2f".format(product.price),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold)
                }
                Button(
                    onClick = {
                        cartViewModel.addToCart(product, selectedWeight)
                        added = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SpiceRed),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(
                        horizontal = 28.dp, vertical = 14.dp)) {
                    Icon(Icons.Default.ShoppingBag, null, Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(if (added) "Added ✓" else "Add to Cart",
                        fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            }
        }
    }
}