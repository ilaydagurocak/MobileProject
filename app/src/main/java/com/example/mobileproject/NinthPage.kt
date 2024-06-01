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

class NinthPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NinthScreen()
                }
            }
        }
    }

    @Composable
    fun NinthScreen() {
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

            // Store List
            when (selectedCity) {
                "New York" -> StoreList(
                    imageRes1 = R.drawable.image1,
                    title1 = "NEW YORK TIMES STORE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.image2,
                    title2 = "NEW YORK CENTRAL STORE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.image3,
                    title3 = "NEW YORK EMPIRE STORE",
                    contactInfo3 = "456789123",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
                "Los Angeles" -> StoreList(
                    imageRes1 = R.drawable.image4,
                    title1 = "LOS ANGELES SUNSET STORE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.image5,
                    title2 = "LOS ANGELES HOLLYWOOD STORE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.image6,
                    title3 = "LOS ANGELES BEACH STORE",
                    contactInfo3 = "456789123",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
                "Chicago" -> StoreList(
                    imageRes1 = R.drawable.image7,
                    title1 = "CHICAGO SKYLINE STORE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.image8,
                    title2 = "CHICAGO RIVER STORE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.image9,
                    title3 = "CHICAGO LAKE STORE",
                    contactInfo3 = "456789123",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
                "Houston" -> StoreList(
                    imageRes1 = R.drawable.image10,
                    title1 = "HOUSTON SPACE STORE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.image11,
                    title2 = "HOUSTON ENERGY STORE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.image12,
                    title3 = "HOUSTON BAYOU STORE",
                    contactInfo3 = "456789123",
                    favoriteItems = favoriteItems,
                    applications = applications
                )
                "Phoenix" -> StoreList(
                    imageRes1 = R.drawable.image13,
                    title1 = "PHOENIX DESERT STORE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.image14,
                    title2 = "PHOENIX MOUNTAIN STORE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.image15,
                    title3 = "PHOENIX SUN STORE",
                    contactInfo3 = "456789123",
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
    fun StoreList(
        imageRes1: Int, title1: String, contactInfo1: String,
        imageRes2: Int, title2: String, contactInfo2: String,
        imageRes3: Int, title3: String, contactInfo3: String,
        favoriteItems: SnapshotStateList<FavoriteItem>,
        applications: SnapshotStateList<FavoriteItem>
    ) {
        Column {
            StoreItem(imageRes = imageRes1, title = title1, contactInfo = contactInfo1, favoriteItems = favoriteItems, applications = applications)
            Spacer(modifier = Modifier.height(16.dp))
            StoreItem(imageRes = imageRes2, title = title2, contactInfo = contactInfo2, favoriteItems = favoriteItems, applications = applications)
            Spacer(modifier = Modifier.height(16.dp))
            StoreItem(imageRes = imageRes3, title = title3, contactInfo = contactInfo3, favoriteItems = favoriteItems, applications = applications)
        }
    }

    @Composable
    fun StoreItem(imageRes: Int, title: String, contactInfo: String, favoriteItems: SnapshotStateList<FavoriteItem>, applications: SnapshotStateList<FavoriteItem>) {
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
