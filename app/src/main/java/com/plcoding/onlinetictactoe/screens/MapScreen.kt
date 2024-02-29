package com.plcoding.onlinetictactoe.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.PatternItem
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.plcoding.onlinetictactoe.R

@Composable
fun MapScreen(enabled: Boolean, viewModel: MapViewModel) {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),

        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            isMyLocationEnabled = enabled,
//            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(LocalContext.current, R.raw. //style_json)
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = true
        )
    ) {
        /*Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x800000FF)) // 0x80 is 50% alpha, 0x0000FF is blue
    )*/
        Circle(
            center = LatLng(1.35, 103.87,),
            radius = viewModel.currentCircleRadius,
            fillColor = Color(0x800000FF),
            strokeColor = Color.Black
        )
        val patternItems = listOf<PatternItem>(Dash(60F), Gap(20F))
        Circle(
            center = LatLng(1.35, 103.87,),
            radius = viewModel.nextCircleRadius,
            fillColor = Color.Transparent,
            strokeColor = Color.Black,
            strokePattern = patternItems
        )
        viewModel.markers.forEach {marker ->
            MapMarker(
                position = marker.position,
                context = LocalContext.current,
                iconResourceId = R.drawable.ic_launcher_background,  //pin,
                name = marker.nameId
            )
        }

    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.ExitToApp,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            val zone = viewModel.currentZoneInt
//            BasicCountdownTimer(seconds = 20, zone = zone, viewModel = viewModel)
            Text(
                text = "Zone $zone",
                fontSize = 10.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold
            )
        }
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}

