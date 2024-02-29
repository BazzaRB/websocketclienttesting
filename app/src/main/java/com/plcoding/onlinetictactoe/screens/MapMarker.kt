package com.plcoding.onlinetictactoe.screens

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.plcoding.onlinetictactoe.R


@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    @DrawableRes iconResourceId: Int,
    name: String
) {
    var icon by remember { mutableStateOf (
        bitmapDescriptorFromVector(
        context, iconResourceId
    ) ) }
    var visible by remember { mutableStateOf(true) }
    var found_indicator by remember { mutableStateOf(false)}

    MarkerInfoWindow(
        state = MarkerState(position = position),
        visible = visible,
        icon = icon,
        onInfoWindowClose =  {icon = bitmapDescriptorFromVector(
            context, R.drawable.ic_launcher_background     //pin
        )
        },
        onClick = {
            icon = bitmapDescriptorFromVector(
                context, R.drawable.ic_launcher_background  //pininvisible
            )
            false},
        onInfoWindowLongClick = {
            found_indicator = !found_indicator
        }
    ) { marker ->
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                )
            ,
        ) {
            Text(text = name)
            if (!found_indicator) {
                Text(text = "Long click to find")
            }
            else {
                Text(text = "Found!")
            }
        }
    }
}

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}