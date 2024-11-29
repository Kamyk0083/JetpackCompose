package com.example.jetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                AppScreen { message ->
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

data class Activity(
    val icon: @Composable () -> Unit,
    val title: String,
    val count: Int
)

@Composable
fun AppScreen(onButtonClick: (String) -> Unit) {
    val activities = listOf(
        Activity(icon = { Icon(Icons.Filled.Face, contentDescription = "Committed changes") }, "Committed changes", 22),
        Activity(icon = { Icon(Icons.Filled.Notifications, contentDescription = "Comment count") }, "Comment count", 15),
        Activity(icon = { Icon(Icons.Filled.CheckCircle, contentDescription = "Merged pull requests") }, "Merged pull requests", 8),
        Activity(icon = { Icon(Icons.Filled.Info, contentDescription = "Closed pull requests") }, "Closed pull requests", 3)
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            ProfileSection()
            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
            ActivitiesSection(activities)
            Spacer(modifier = Modifier.weight(1f)) // Pushes content above
            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
            Button(
                onClick = { onButtonClick("Well done!") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)), // Fioletowy
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Display message", color = Color.White)
            }
        }
    }
}

@Composable
fun ProfileSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = "Profile Icon",
            modifier = Modifier.size(64.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("Grześ Grzegorzewski", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Git statistics", color = Color.Gray)
        }
    }
}

@Composable
fun ActivitiesSection(activities: List<Activity>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Recent Activities", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        for (activity in activities) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                activity.icon()
                Spacer(modifier = Modifier.width(16.dp))
                Text(activity.title, modifier = Modifier.weight(1f))
                Text(activity.count.toString(), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    JetpackComposeTheme {
        AppScreen {}
    }
}
