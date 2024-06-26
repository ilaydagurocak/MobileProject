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

class PurchasesPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applications = intent.getParcelableArrayListExtra<FavoriteItem>("applications") ?: arrayListOf()
        setContent {
            MobileProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PurchasesScreen(applications)
                }
            }
        }
    }

    @Composable
    fun PurchasesScreen(applications: List<FavoriteItem>) {
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text("Your Purchases", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                if (applications.isEmpty()) {
                    Text("No purchases yet", fontSize = 16.sp, color = Color.Gray)
                } else {
                    applications.forEach { item ->
                        PurchaseItemView(item)
                        Spacer(modifier = Modifier.height(16.dp))
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
                        val intent = Intent(context, FavoritesPage::class.java).apply {
                            putParcelableArrayListExtra("favorites", ArrayList(applications)) // Pass applications as favorites if needed
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

    @Composable
    fun PurchaseItemView(item: FavoriteItem) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.description,
                    fontSize = 14.sp
                )
            }
        }
    }
}

