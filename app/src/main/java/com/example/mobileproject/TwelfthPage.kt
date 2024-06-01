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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.mobileproject.ui.theme.MobileProjectTheme
import androidx.compose.ui.draw.clip

class TwelfthPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applications = intent.getParcelableArrayListExtra<FavoriteItem>("applications") ?: arrayListOf()
        setContent {
            MobileProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TwelfthScreen(applications)
                }
            }
        }
    }

    @Composable
    fun TwelfthScreen(applications: List<FavoriteItem>) {
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text("Profile", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Navigate to PurchasesScreen
                    val intent = Intent(context, PurchasesPage::class.java).apply {
                        putParcelableArrayListExtra("applications", ArrayList(applications))
                    }
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7)),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text("Your Purchases", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Handle "Items you Sold" logic here
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7)),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text("Items you Sold", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Handle Log Out logic
                    Toast.makeText(context, "Logging out...", Toast.LENGTH_SHORT).show()
                    (context as ComponentActivity).finishAffinity() // Closes all activities and exits the app
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text("Log Out", color = Color.White)
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
                        val intent = Intent(context, SixthPage::class.java)
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
                        val intent = Intent(context, EleventhPage::class.java)
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
