package com.acdev.wallpapervisions.HomeScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val pagerState = rememberPagerState()
    val pagerOffset = pagerState.currentPageOffsetFraction
    val colorMatrix = remember {
        ColorMatrix()
    }
    val imageSize by animateFloatAsState(
        targetValue = if (pagerOffset != 0.0f) 0.90f else 1f, label = ""
    )
    val images = remember {
        mutableStateListOf(
            "https://e0.pxfuel.com/wallpapers/804/834/desktop-wallpaper-game-phone-mobile-gamer.jpg",
            "https://e1.pxfuel.com/desktop-wallpaper/52/550/desktop-wallpaper-gamer-phone-gaming-phone.jpg",
            "https://e1.pxfuel.com/desktop-wallpaper/417/470/desktop-wallpaper-android-gamer-video-games-android-phones.jpg",
            "https://e0.pxfuel.com/wallpapers/438/323/desktop-wallpaper-relaxing-phone-i-m-doing-this-weekly-now-if-you-want-to-gamer.jpg",
            "https://e1.pxfuel.com/desktop-wallpaper/571/159/desktop-wallpaper-japan-reddit-mobile-kecbio-phone-japan-gamer.jpg",
            "https://e1.pxfuel.com/desktop-wallpaper/244/559/desktop-wallpaper-mobile-gaming-gaming-mobile-gallery-gamers-phone.jpg",
            "https://e1.pxfuel.com/desktop-wallpaper/611/951/desktop-wallpaper-gamer-phone-gaming-for-mobile.jpg",
            "https://e1.pxfuel.com/desktop-wallpaper/89/143/desktop-wallpaper-gamer-phone-on-dog-game-phone.jpg",
            "https://e1.pxfuel.com/desktop-wallpaper/350/192/desktop-wallpaper-gamers-posted-by-ryan-simpson-gamers-phone.jpg"
        )
    }
    Scaffold(modifier = Modifier.padding(vertical = 48.dp)) { padding ->
        HorizontalPager(pageCount = images.size, state = pagerState) { index ->

            LaunchedEffect(key1 = imageSize) {
                if (pagerOffset != 0.0f) {
                    colorMatrix.setToSaturation(0f)
                } else {
                    colorMatrix.setToSaturation(1f)
                }
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(images[index])
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .graphicsLayer {
                        scaleX = imageSize
                        scaleY = imageSize
                    }
                    .clip(
                        RoundedCornerShape(16.dp)
                    ),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(colorMatrix)
            )

        }
    }
}