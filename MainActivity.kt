package com.jatrasathi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF087F5B)
private val Pale = Color(0xFFE8F5EF)
private val Ink = Color(0xFF17332A)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { JatraSathiApp() }
    }
}

@Composable
fun JatraSathiApp() {
    var page by remember { mutableStateOf("home") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var pickup by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var vehicle by remember { mutableStateOf("CNG") }
    var quote by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = lightColorScheme(primary = Green, secondary = Color(0xFFF4A340), background = Color(0xFFF7FAF8))
    ) {
        Surface(Modifier.fillMaxSize(), color = Color(0xFFF7FAF8)) {
            if (page == "profile") {
                Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { page = "home" }) { Icon(Icons.Default.ArrowBack, "ফিরে যান") }
                        Text("আমার প্রোফাইল", fontSize = 23.sp, fontWeight = FontWeight.Bold, color = Ink)
                    }
                    Spacer(Modifier.height(26.dp))
                    Box(Modifier.align(Alignment.CenterHorizontally).size(100.dp).background(Pale, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Person, null, tint = Green, modifier = Modifier.size(54.dp))
                    }
                    Spacer(Modifier.height(24.dp))
                    OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("আপনার পুরো নাম") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("মোবাইল নম্বর") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                    Spacer(Modifier.height(18.dp))
                    Button(onClick = { page = "home" }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) { Text("সংরক্ষণ করুন") }
                    Text("প্রোফাইল ছবি যুক্ত করার সুবিধা পরবর্তী সংস্করণে যোগ করতে হবে।", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(top = 14.dp))
                }
            } else {
                Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                        Box(Modifier.size(52.dp).background(Green, RoundedCornerShape(16.dp)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Place, null, tint = Color.White, modifier = Modifier.size(31.dp))
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text("যাত্রাসাথী", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold, color = Green)
                            Text("আপনার যাত্রা, আপনার সাথী", fontSize = 12.sp, color = Color.Gray)
                        }
                        IconButton(onClick = { page = "profile" }) {
                            Icon(Icons.Default.Person, "প্রোফাইল", tint = Green, modifier = Modifier.size(29.dp))
                        }
                    }
                    Spacer(Modifier.height(24.dp))
                    Text("কোথায় যেতে চান?", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Ink)
                    Text("আপনার যাত্রার তথ্য দিন", color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
                    Spacer(Modifier.height(18.dp))
                    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(20.dp)) {
                        Column(Modifier.padding(16.dp)) {
                            OutlinedTextField(value = pickup, onValueChange = { pickup = it; quote = false }, label = { Text("যাত্রা শুরু") }, leadingIcon = { Icon(Icons.Default.Place, null, tint = Green) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                            Spacer(Modifier.height(12.dp))
                            OutlinedTextField(value = destination, onValueChange = { destination = it; quote = false }, label = { Text("গন্তব্য") }, leadingIcon = { Icon(Icons.Default.Place, null, tint = Color(0xFFE49A2A)) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                        }
                    }
                    Spacer(Modifier.height(22.dp))
                    Text("যানবাহন নির্বাচন করুন", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Ink)
                    Spacer(Modifier.height(12.dp))
                    val vehicles = listOf(
                        Triple("CNG", "CNG", "৳৳"),
                        Triple("বাইক", "বাইক", "৳"),
                        Triple("রিকশা", "রিকশা", "৳"),
                        Triple("প্রাইভেট কার", "কার", "৳৳৳")
                    )
                    vehicles.chunked(2).forEach { rowItems ->
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            rowItems.forEach { item ->
                                val selected = vehicle == item.first
                                Card(
                                    modifier = Modifier.weight(1f).clickable { vehicle = item.first; quote = false },
                                    shape = RoundedCornerShape(18.dp),
                                    colors = CardDefaults.cardColors(containerColor = if (selected) Pale else Color.White),
                                    border = if (selected) androidx.compose.foundation.BorderStroke(2.dp, Green) else null
                                ) {
                                    Column(Modifier.fillMaxWidth().padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(when(item.first) { "CNG" -> "🛺"; "বাইক" -> "🏍️"; "রিকশা" -> "🚲"; else -> "🚗" }, fontSize = 32.sp)
                                        Spacer(Modifier.height(5.dp))
                                        Text(item.second, fontWeight = FontWeight.SemiBold, color = Ink)
                                        Text(item.third, fontSize = 12.sp, color = Green)
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                    }
                    Button(
                        onClick = { quote = true },
                        modifier = Modifier.fillMaxWidth().height(54.dp),
                        shape = RoundedCornerShape(15.dp)
                    ) { Text("আনুমানিক ভাড়া দেখুন", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
                    if (quote) {
                        Spacer(Modifier.height(12.dp))
                        Card(colors = CardDefaults.cardColors(containerColor = Pale), shape = RoundedCornerShape(16.dp)) {
                            Column(Modifier.padding(16.dp)) {
                                Text("ভাড়ার ডেমো", fontWeight = FontWeight.Bold, color = Green, fontSize = 18.sp)
                                Text("যানবাহন: $vehicle")
                                Text("শুরু: ${pickup.ifBlank { "উল্লেখ করা হয়নি" }}")
                                Text("গন্তব্য: ${destination.ifBlank { "উল্লেখ করা হয়নি" }}")
                                Text("বাস্তব দূরত্ব ও ভাড়া নির্ণয়ের জন্য ম্যাপ/সার্ভার যুক্ত করতে হবে।", fontSize = 12.sp, color = Color.DarkGray)
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    Text("নিরাপদ ও সহজ যাত্রার জন্য", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
    }
}
