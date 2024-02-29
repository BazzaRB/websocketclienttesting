package com.plcoding.onlinetictactoe.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavBar(selectedActivity: Int, modifier: Modifier, navController: NavController) {
    val listOfActivities = listOf<ImageVector>(Icons.Default.AccountCircle, Icons.Default.Info, Icons.Default.Place, Icons.Default.Email, Icons.Default.Build)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .height(60.dp)
                .padding(bottom = 10.dp)
                .clip(shape = RoundedCornerShape(percent = 100))
                .background(color = Color.White)
        ){}
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .padding(bottom = 10.dp)
                .clip(shape = RoundedCornerShape(percent = 100))
            //.then(modifier)

        ) {
            for ((i, activity) in listOfActivities.withIndex()) {
                if (i == selectedActivity.toInt()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(85.dp)
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(percent = 25))
                            .background(color = Color.Black)
                            .clickable { navController.navigate(Screen.MapScreen.route ) {
                                // Specify the destination to pop up to
                                popUpTo(Screen.MapScreen.route) {
                                    // Include the "home" destination in the popUpTo
                                    inclusive = true
                                }
                                // Use a singleTop to ensure it's not recreated if it's already at the top of the stack
                                launchSingleTop = true
                            } }
                    ) {
                        Icon(
                            imageVector = activity,
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp),
                            tint = Color.White

                        )
                    }
                }
                else {
                    Icon(
                        imageVector = activity,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(30.dp)
                            .clickable { navController.navigate(Screen.SettingsScreen.route) }

                    )
                }
            }
        }
    }

}