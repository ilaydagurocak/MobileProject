package com.example.mobileproject

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

class AddHousePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddHouseScreen()
                }
            }
        }
    }

    @Composable
    fun AddHouseScreen() {
        var houseName by remember { mutableStateOf("") }
        var houseFeatures by remember { mutableStateOf("") }
        var housePhoto by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text("Add House", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = houseName,
                onValueChange = { houseName = it },
                label = { Text("House Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = houseFeatures,
                onValueChange = { houseFeatures = it },
                label = { Text("House Features") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = housePhoto,
                onValueChange = { housePhoto = it },
                label = { Text("Photo URL") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Handle add house logic
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7)),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text("Submit", color = Color.Black)
            }
        }
    }
}
