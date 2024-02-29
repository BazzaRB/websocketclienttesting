package com.plcoding.onlinetictactoe.screens

sealed class Screen(val route: String) {
    object MapScreen : Screen("map_screen")
    object SettingsScreen : Screen("settings_screen")

}
