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
import androidx.compose.runtime.snapshots.SnapshotStateList

class SeventhPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SeventhScreen()
                }
            }
        }
    }

    @Composable
    fun SeventhScreen() {
        val context = LocalContext.current

        var locationExpanded by remember { mutableStateOf(false) }
        val cities = listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix")
        var selectedCity by remember { mutableStateOf(cities[0]) }

        var notificationsExpanded by remember { mutableStateOf(false) }
        val notifications = listOf("Notification 1", "Notification 2")

        // Favorites and applications state
        val favoriteItems = remember { mutableStateListOf<FavoriteItem>() }
        val applications = remember { mutableStateListOf<FavoriteItem>() }

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
                                .clickable { notificationsExpanded = true }
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
                                val intent = Intent(context, TwelfthPage::class.java).apply {
                                    putParcelableArrayListExtra("applications", ArrayList(applications))
                                }
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

            // Filter Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {}, shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7))) {
                    Text("Price", color = Color.Black)
                }
                Button(onClick = {}, shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7))) {
                    Text("Security", color = Color.Black)
                }
                Button(onClick = {}, shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7))) {
                    Text("Extra", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Houses List
            when (selectedCity) {
                "New York" -> HouseList(
                    imageRes1 = R.drawable.house1,
                    title1 = "NEW YORK LALISA HOUSE",
                    contactInfo1 = "34256781",
                    imageRes2 = R.drawable.house2,
                    title2 = "NEW YORK STARLIGHT HOUSE",
                    contactInfo2 = "35698376",
                    imageRes3 = R.drawable.house3,
                    title3 = "NEW YORK SOFTVIBE HOUSE",
                    contactInfo3 = "08367932",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
                "Los Angeles" -> HouseList(
                    imageRes1 = R.drawable.losangeles1,
                    title1 = "LOS ANGELES MOON HOUSE",
                    contactInfo1 = "34256781",
                    imageRes2 = R.drawable.losangeles2,
                    title2 = "LOS ANGELES WARM HOUSE",
                    contactInfo2 = "35698376",
                    imageRes3 = R.drawable.losangeles3,
                    title3 = "LOS ANGELES HAPPY HOUSE",
                    contactInfo3 = "08367932",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
                "Chicago" -> HouseList(
                    imageRes1 = R.drawable.chicago1,
                    title1 = "CHICAGO DIOR HOUSE",
                    contactInfo1 = "34256781",
                    imageRes2 = R.drawable.chicago2,
                    title2 = "CHICAGO CELINE HOUSE",
                    contactInfo2 = "35698376",
                    imageRes3 = R.drawable.chicago3,
                    title3 = "CHICAGO GUESS HOUSE",
                    contactInfo3 = "08367932",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
                "Houston" -> HouseList(
                    imageRes1 = R.drawable.houston1,
                    title1 = "HOUSTON GREEN HOUSE",
                    contactInfo1 = "34256781",
                    imageRes2 = R.drawable.houston2,
                    title2 = "HOUSTON WHITE HOUSE",
                    contactInfo2 = "35698376",
                    imageRes3 = R.drawable.houston3,
                    title3 = "HOUSTON BLUE HOUSE",
                    contactInfo3 = "08367932",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
                "Phoenix" -> HouseList(
                    imageRes1 = R.drawable.phoenix1,
                    title1 = "PHOENIX BIG HOUSE",
                    contactInfo1 = "34256781",
                    imageRes2 = R.drawable.phoenix2,
                    title2 = "PHOENIX SMALL HOUSE",
                    contactInfo2 = "35698376",
                    imageRes3 = R.drawable.phoenix3,
                    title3 = "PHOENIX SKY HOUSE",
                    contactInfo3 = "08367932",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
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
                        val intent = Intent(context, FavoritesPage::class.java).apply {
                            putParcelableArrayListExtra("favorites", ArrayList(favoriteItems))
                        }
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

    @Composable
    fun HouseList(
        imageRes1: Int, title1: String, contactInfo1: String,
        imageRes2: Int, title2: String, contactInfo2: String,
        imageRes3: Int, title3: String, contactInfo3: String,
        favoriteItems: SnapshotStateList<FavoriteItem>,
        applications: SnapshotStateList<FavoriteItem>
    ) {
        Column {
            HouseItem(imageRes = imageRes1, title = title1, contactInfo = contactInfo1, favoriteItems = favoriteItems, applications = applications)
            Spacer(modifier = Modifier.height(16.dp))
            HouseItem(imageRes = imageRes2, title = title2, contactInfo = contactInfo2, favoriteItems = favoriteItems, applications = applications)
            Spacer(modifier = Modifier.height(16.dp))
            HouseItem(imageRes = imageRes3, title = title3, contactInfo = contactInfo3, favoriteItems = favoriteItems, applications = applications)
        }
    }

    @Composable
    fun HouseItem(imageRes: Int, title: String, contactInfo: String, favoriteItems: SnapshotStateList<FavoriteItem>, applications: SnapshotStateList<FavoriteItem>) {
        var isFavorite by remember { mutableStateOf(favoriteItems.any { it.name == title }) }
        var applicationSent by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Contact info: $contactInfo",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = {
                            applications.add(FavoriteItem(name = title, description = contactInfo, imageRes = imageRes))
                            applicationSent = true
                        },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7))
                    ) {
                        Text(if (applicationSent) "Application Sent" else "PRE-Application", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = if (isFavorite) R.drawable.favorites_filled else R.drawable.favorites),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                isFavorite = if (isFavorite) {
                                    favoriteItems.removeAll { it.name == title }
                                    false
                                } else {
                                    favoriteItems.add(FavoriteItem(name = title, description = contactInfo, imageRes = imageRes))
                                    true
                                }
                            },
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}
