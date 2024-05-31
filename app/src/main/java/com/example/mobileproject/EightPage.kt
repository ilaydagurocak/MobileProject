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

class EightPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EightScreen()
                }
            }
        }
    }

    @Composable
    fun EightScreen() {
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
                        modifier = Modifier.size(24.dp)
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

            // Office List
            when (selectedCity) {
                "New York" -> OfficeList(
                    imageRes1 = R.drawable.newyorkoffice1,
                    title1 = "NEW YORK TIMES OFFICE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.newyorkoffice2,
                    title2 = "NEW YORK CENTRAL OFFICE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.newyorkoffice3,
                    title3 = "NEW YORK EMPIRE OFFICE",
                    contactInfo3 = "456789123"
                )
                "Los Angeles" -> OfficeList(
                    imageRes1 = R.drawable.losangelesoffice1,
                    title1 = "LOS ANGELES SUNSET OFFICE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.losangelesoffice2,
                    title2 = "LOS ANGELES HOLLYWOOD OFFICE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.losangelesoffice3,
                    title3 = "LOS ANGELES BEACH OFFICE",
                    contactInfo3 = "456789123"
                )
                "Chicago" -> OfficeList(
                    imageRes1 = R.drawable.chicagooffice1,
                    title1 = "CHICAGO SKYLINE OFFICE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.chicagooffice2,
                    title2 = "CHICAGO RIVER OFFICE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.chicagooffice3,
                    title3 = "CHICAGO LAKE OFFICE",
                    contactInfo3 = "456789123"
                )
                "Houston" -> OfficeList(
                    imageRes1 = R.drawable.houstonoffice1,
                    title1 = "HOUSTON SPACE OFFICE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.houstonoffice2,
                    title2 = "HOUSTON ENERGY OFFICE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.houstonoffice3,
                    title3 = "HOUSTON BAYOU OFFICE",
                    contactInfo3 = "456789123"
                )
                "Phoenix" -> OfficeList(
                    imageRes1 = R.drawable.phoenixoffice1,
                    title1 = "PHOENIX DESERT OFFICE",
                    contactInfo1 = "123456789",
                    imageRes2 = R.drawable.phoenixoffice2,
                    title2 = "PHOENIX MOUNTAIN OFFICE",
                    contactInfo2 = "987654321",
                    imageRes3 = R.drawable.phoenixoffice3,
                    title3 = "PHOENIX SUN OFFICE",
                    contactInfo3 = "456789123"
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
    fun OfficeList(
        imageRes1: Int, title1: String, contactInfo1: String,
        imageRes2: Int, title2: String, contactInfo2: String,
        imageRes3: Int, title3: String, contactInfo3: String
    ) {
        Column {
            OfficeItem(imageRes = imageRes1, title = title1, contactInfo = contactInfo1)
            Spacer(modifier = Modifier.height(16.dp))
            OfficeItem(imageRes = imageRes2, title = title2, contactInfo = contactInfo2)
            Spacer(modifier = Modifier.height(16.dp))
            OfficeItem(imageRes = imageRes3, title = title3, contactInfo = contactInfo3)
        }
    }

    @Composable
    fun OfficeItem(imageRes: Int, title: String, contactInfo: String) {
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
                Button(
                    onClick = { /* Handle PRE-Application */ },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7))
                ) {
                    Text("PRE-Application", color = Color.Black)
                }
            }
        }
    }
}
