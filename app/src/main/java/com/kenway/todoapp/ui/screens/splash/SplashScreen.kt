package com.kenway.todoapp.ui.screens.splash

import android.window.SplashScreen
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kenway.todoapp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kenway.todoapp.ui.theme.LOGO_HEIGHT
import com.kenway.todoapp.ui.theme.ToDoAppTheme
import com.kenway.todoapp.ui.theme.splashscreenBackground
import com.kenway.todoapp.util.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp


@Composable
fun SplashScreen(
    navigateToListScreen: ()-> Unit
){

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val offsetState by animateDpAsState(
        targetValue = if(startAnimation) 0.dp else 110.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val alphaState by animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation=true
        delay(3000)
        navigateToListScreen()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashscreenBackground),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier=Modifier
                .size(LOGO_HEIGHT)
                .offset(y=offsetState)
                .alpha(alpha = alphaState)
            ,
            painter = painterResource(id = getLogo()),
            contentDescription = stringResource(id = R.string.to_do_logo )
        )
    }
}


@Composable
fun getLogo():Int{
    return  if(isSystemInDarkTheme())
    {
        R.drawable.ic_logo_dark
    }
    else {
        R.drawable.ic_logo_light
    }
}


@Composable
@Preview
private fun SplashScreenPreview(){
    SplashScreen(
        navigateToListScreen = {}
    )
}


