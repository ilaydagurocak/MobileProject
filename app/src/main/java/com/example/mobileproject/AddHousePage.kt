package com.example.mobileproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.mobileproject.ui.theme.MobileProjectTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.*

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
        val context = LocalContext.current
        var houseName by remember { mutableStateOf("") }
        var houseFeatures by remember { mutableStateOf("") }
        var houseLocation by remember { mutableStateOf("") }
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri = uri
        }
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()
        val storage = FirebaseStorage.getInstance()
        val locations = listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix")
        var expanded by remember { mutableStateOf(false) }

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

            Box {
                TextField(
                    value = houseLocation,
                    onValueChange = { houseLocation = it },
                    label = { Text("Location") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    enabled = false
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            onClick = {
                                houseLocation = location
                                expanded = false
                            },
                            text = { Text(text = location) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { launcher.launch("image/*") },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color.Gray),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text("Select Image", color = Color.Black)
            }

            imageUri?.let { uri ->
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val currentUser = auth.currentUser
                    if (currentUser != null && imageUri != null) {
                        val storageRef = storage.reference.child("house_images/${UUID.randomUUID()}")
                        val uploadTask = storageRef.putFile(imageUri!!)

                        uploadTask.addOnSuccessListener {
                            storageRef.downloadUrl.addOnSuccessListener { uri ->
                                val house = hashMapOf(
                                    "name" to houseName,
                                    "features" to houseFeatures,
                                    "location" to houseLocation,
                                    "imageUrl" to uri.toString(),
                                    "userId" to currentUser.uid
                                )

                                firestore.collection("houses")
                                    .add(house)
                                    .addOnSuccessListener { documentReference ->
                                        Toast.makeText(context, "House added successfully", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(context, EleventhPage::class.java)
                                        context.startActivity(intent)
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(context, "Failed to add house", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }.addOnFailureListener {
                            Toast.makeText(context, "Image upload failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color(0xFFA5D6A7)),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text("Submit", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val intent = Intent(context, EleventhPage::class.java)
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color.Gray),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text("Back", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        firestore.collection("houses")
                            .whereEqualTo("userId", currentUser.uid)
                            .get()
                            .addOnSuccessListener { documents ->
                                for (document in documents) {
                                    val houseId = document.id
                                    val imageUrl = document.getString("imageUrl") ?: ""
                                    firestore.collection("houses").document(houseId)
                                        .delete()
                                        .addOnSuccessListener {
                                            val imageRef = storage.getReferenceFromUrl(imageUrl)
                                            imageRef.delete()
                                                .addOnSuccessListener {
                                                    Toast.makeText(context, "House and image deleted successfully", Toast.LENGTH_SHORT).show()
                                                }
                                                .addOnFailureListener {
                                                    Toast.makeText(context, "Failed to delete image", Toast.LENGTH_SHORT).show()
                                                }
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(context, "Failed to delete house", Toast.LENGTH_SHORT).show()
                                        }
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "Failed to get houses", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text("Delete House", color = Color.White)
            }
        }
    }
}
