package com.plcoding.onlinetictactoe.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class Marker(initialPosition: LatLng, nameId: String) {
    var position by mutableStateOf(initialPosition)
    //private val _position = MutableStateFlow(initialPosition)
    //val position = _position.asStateFlow()

    val nameId: String = nameId

}

class MapViewModel : ViewModel(){
    var markers = mutableListOf<Marker> (
        Marker(initialPosition = LatLng(1.35, 103.87), nameId = "Harald Rex"),
        Marker(initialPosition = LatLng(1.34, 103.87), nameId = "Sonja Rex")
    )
    var currentCircleRadius by mutableDoubleStateOf(500.0)
    var targetedCircleRadius by mutableDoubleStateOf(250.0)

    var nextCircleRadius by mutableDoubleStateOf(250.0)

    var currentZoneInt by mutableIntStateOf(1)
    var startOfSession = System.currentTimeMillis()
    val sessionLength = 300

    var uiTimerSeconds by mutableIntStateOf((sessionLength * 0.25).toInt())
    fun startNextZone() {

    }
}