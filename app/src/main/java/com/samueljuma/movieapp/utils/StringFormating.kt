package com.samueljuma.movieapp.utils

import java.text.SimpleDateFormat


fun formatToDateString(systemTime: Long): String {
    return "Saved On: "+ SimpleDateFormat("MM/dd/yy")
        .format(systemTime).toString()
}
