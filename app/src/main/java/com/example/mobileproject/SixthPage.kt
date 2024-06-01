package com.example.mobileproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.mobileproject.ui.theme.MobileProjectTheme
import androidx.compose.ui.draw.clip

class SixthPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SixthScreen()
                }
            }
        }
    }

    @Composable
    fun SixthScreen() {
        val context = LocalContext.current

        var locationExpanded by remember { mutableStateOf(false) }
        val cities = listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix")
        var selectedCity by remember { mutableStateOf(cities[0]) }

        var notificationsExpanded by remember { mutableStateOf(false) }
        val notifications = listOf("Notification 1", "Notification 2")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Header Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFDFF5E1))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.clickable { locationExpanded = true }) {
                    Text("Location", fontSize = 12.sp, color = Color.Gray)
                    Text(selectedCity, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    DropdownMenu(
                        expanded = locationExpanded,
                        onDismissRequest = { locationExpanded = false }
                    ) {
                        cities.forEach { city ->
                            DropdownMenuItem(
                                text = { Text(text = city) },
                                onClick = {
                                    selectedCity = city
                                    locationExpanded = false
                                }
                            )
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.notifications),
                            contentDescription = "Notifications",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    notificationsExpanded = true
                                }
                        )
                        DropdownMenu(
                            expanded = notificationsExpanded,
                            onDismissRequest = { notificationsExpanded = false }
                        ) {
                            if (notifications.isNotEmpty()) {
                                notifications.forEach { notification ->
                                    DropdownMenuItem(
                                        text = { Text(text = notification) },
                                        onClick = { notificationsExpanded = false }
                                    )
                                }
                            } else {
                                DropdownMenuItem(
                                    text = { Text("No notifications") },
                                    onClick = { notificationsExpanded = false }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                val intent = Intent(context, TwelfthPage::class.java)
                                context.startActivity(intent)
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Rent Collection Section
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text("Rent Collection", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) // Adjust height to make it larger
                        .background(Color(0xFFA5D6A7), shape = RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            val intent = Intent(context, SeventhPage::class.java)
                            context.startActivity(intent)
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.house),
                            contentDescription = "House",
                            modifier = Modifier.size(48.dp)
                        )
                        Text("House", fontSize = 14.sp)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            val intent = Intent(context, EightPage::class.java)
                            context.startActivity(intent)
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.office),
                            contentDescription = "Office",
                            modifier = Modifier.size(48.dp)
                        )
                        Text("Office", fontSize = 14.sp)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            val intent = Intent(context, NinthPage::class.java)
                            context.startActivity(intent)
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.store),
                            contentDescription = "Store",
                            modifier = Modifier.size(48.dp)
                        )
                        Text("Store", fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Maintenance Request Section
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text("Maintenance Request", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) // Adjust height to make it larger
                        .background(Color(0xFFA5D6A7), shape = RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            val intent = Intent(context, MaintenanceRequestPage::class.java)
                            context.startActivity(intent)
                        }) {
                        Image(
                            painter = painterResource(id = R.drawable.tamir),
                            contentDescription = "Repair",
                            modifier = Modifier.size(48.dp)
                        )
                        Text("Repair", fontSize = 14.sp)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            val intent = Intent(context, MaintenanceRequestPage::class.java)
                            context.startActivity(intent)
                        }) {
                        Image(
                            painter = painterResource(id = R.drawable.pestextermination),
                            contentDescription = "Pest Extermination",
                            modifier = Modifier.size(48.dp)
                        )
                        Text("Pest Extermination", fontSize = 14.sp)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            val intent = Intent(context, MaintenanceRequestPage::class.java)
                            context.startActivity(intent)
                        }) {
                        Image(
                            painter = painterResource(id = R.drawable.cleaning),
                            contentDescription = "Cleaning",
                            modifier = Modifier.size(48.dp)
                        )
                        Text("Cleaning", fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Navigation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, TenthPage::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Home",
                        modifier = Modifier.size(50.dp)
                    )
                    Text("Home", fontSize = 12.sp)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, FavoritesPage::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.favorites),
                        contentDescription = "Favorites",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Favorites", fontSize = 12.sp)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, AddHousePage::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Add", fontSize = 12.sp)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, SixthPage::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Menu", fontSize = 12.sp)
                }
            }
        }
    }
}
