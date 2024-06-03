package com.example.mobileproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mobileproject.ui.theme.MobileProjectTheme
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

data class House(
    val name: String = "",
    val features: String = "",
    val imageUrl: String = "",
    val location: String = "",
    val userId: String = "",
    var documentId: String = "", // Changed to var
    var applicationSent: Boolean = false // Add this field to track application status
)

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
        val firestore = FirebaseFirestore.getInstance()

        var locationExpanded by remember { mutableStateOf(false) }
        val cities = listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix")
        var selectedCity by remember { mutableStateOf(cities[0]) }
        val houses = remember { mutableStateListOf<House>() }

        // Fetch houses from Firestore based on selected city
        LaunchedEffect(selectedCity) {
            firestore.collection("houses")
                .whereEqualTo("location", selectedCity)
                .get()
                .addOnSuccessListener { documents ->
                    houses.clear()
                    for (document in documents) {
                        val house = document.toObject(House::class.java)
                        house.documentId = document.id // Save document ID
                        houses.add(house)
                    }
                }
                .addOnFailureListener { _ ->
                    Toast.makeText(context, "Failed to fetch houses", Toast.LENGTH_SHORT).show()
                }
        }

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
                                .clickable { /* Handle notifications */ }
                        )
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
            HouseList(houses)

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
                        val intent = Intent(context, EleventhPage::class.java)
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
    fun HouseList(houses: List<House>) {
        Column {
            houses.forEach { house ->
                HouseItem(house)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    @Composable
    fun HouseItem(house: House) {
        var isFavorite by remember { mutableStateOf(false) }
        var applicationSent by remember { mutableStateOf(house.applicationSent) }
        val firestore = FirebaseFirestore.getInstance()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.White)
        ) {
            Image(
                painter = rememberAsyncImagePainter(house.imageUrl),
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
                    text = house.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Features: ${house.features}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Contact info: ${house.userId}", // Change to actual contact info if available
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = {
                            applicationSent = true
                            house.applicationSent = true
                            // Update application status in Firestore
                            firestore.collection("houses").document(house.documentId)
                                .update("applicationSent", true)
                                .addOnSuccessListener {
                                    scope.launch {
                                        Toast.makeText(context, "Application sent", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                .addOnFailureListener {
                                    scope.launch {
                                        Toast.makeText(context, "Failed to update application status", Toast.LENGTH_SHORT).show()
                                    }
                                }
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
                                isFavorite = !isFavorite
                            },
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}
