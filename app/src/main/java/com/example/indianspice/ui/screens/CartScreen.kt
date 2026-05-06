package com.example.indianspice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.indianspice.model.CartItem
import com.example.indianspice.ui.components.parseHex
import com.example.indianspice.ui.theme.SpiceRed

@Composable
fun CartScreen(cartViewModel: CartViewModel, onBack: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val subtotal = cartViewModel.total
    val tax = subtotal * 0.13
    val grandTotal = subtotal + tax

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth().padding(20.dp),
                Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Surface(shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(44.dp).clickable(onClick = onBack)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.ArrowBack, "Back") }
                }
                Text("My Cart 🛒",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold)
                Spacer(Modifier.size(44.dp))
            }

            if (cartViewModel.items.isEmpty()) {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🫙", fontSize = 80.sp)
                        Spacer(Modifier.height(16.dp))
                        Text("Your cart is empty",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold)
                        Text("Add some spices!",
                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            } else {
                LazyColumn(Modifier.weight(1f),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    itemsIndexed(cartViewModel.items,
                        key = { _, item ->
                            "${item.product.id}-${item.selectedWeight}"
                        }) { idx, item ->
                        SpiceCartRow(item,
                            onPlus = { cartViewModel.increaseQuantity(idx) },
                            onMinus = { cartViewModel.decreaseQuantity(idx) },
                            onDelete = { cartViewModel.removeItem(idx) })
                    }
                }

                Surface(shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 12.dp) {
                    Column(Modifier.padding(20.dp)) {
                        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                            Text("Subtotal",
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$%.2f".format(subtotal),
                                fontWeight = FontWeight.SemiBold)
                        }
                        Spacer(Modifier.height(6.dp))
                        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                            Text("Tax (HST 13%)",
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$%.2f".format(tax),
                                fontWeight = FontWeight.SemiBold)
                        }
                        Spacer(Modifier.height(8.dp))
                        Divider()
                        Spacer(Modifier.height(8.dp))
                        Row(Modifier.fillMaxWidth(),
                            Arrangement.SpaceBetween, Alignment.CenterVertically) {
                            Text("Total",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold)
                            Text("$%.2f".format(grandTotal),
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = SpiceRed)
                        }
                        Spacer(Modifier.height(14.dp))
                        Button(onClick = { showDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SpiceRed),
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(vertical = 16.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text("Place Order  🌶️",
                                fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Order Confirmed! 🎉",
                    fontWeight = FontWeight.Bold) },
                text = { Text("Your Indian Spice order of " +
                        "$%.2f is on its way!".format(grandTotal)) },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        cartViewModel.clear()
                        onBack()
                    }) {
                        Text("Great!", color = SpiceRed,
                            fontWeight = FontWeight.Bold)
                    }
                })
        }
    }
}

@Composable
private fun SpiceCartRow(
    item: CartItem,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
    onDelete: () -> Unit
) {
    Surface(shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(72.dp).clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center) {
                Box(Modifier.size(48.dp).clip(CircleShape)
                    .background(parseHex(item.product.colorHex)))
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(item.product.name,
                    fontWeight = FontWeight.SemiBold, maxLines = 1)
                Text("${item.selectedWeight} · " +
                        item.product.origin.split(",").first(),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("$%.2f".format(item.subtotal),
                    fontWeight = FontWeight.Bold, color = SpiceRed)
            }
            Column(horizontalAlignment = Alignment.End) {
                Icon(Icons.Default.Delete, "Remove",
                    modifier = Modifier.size(20.dp).clickable(onClick = onDelete),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = CircleShape,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.size(28.dp).clickable(onClick = onMinus)) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Remove, null,
                                Modifier.size(14.dp)) }
                    }
                    Text("${item.quantity}",
                        Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold)
                    Surface(shape = CircleShape,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.size(28.dp).clickable(onClick = onPlus)) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Add, null,
                                Modifier.size(14.dp)) }
                    }
                }
            }
        }
    }
}