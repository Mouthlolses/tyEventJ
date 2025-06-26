package com.revenuee.tyeventj.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.revenuee.tyeventj.ui.navigation.rememberCaririPlusNavController
import com.revenuee.tyeventj.ui.theme.CaririPlusTheme


@Preview
@Composable
fun CaririPlusApp(){
    CaririPlusTheme {
        val caririPlusNavController = rememberCaririPlusNavController()
    }
}