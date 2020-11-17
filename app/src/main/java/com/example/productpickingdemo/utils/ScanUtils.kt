package com.example.productpickingdemo.utils

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import com.example.productpickingdemo.R

object ScanUtils {

    fun scanPositive(context: Context) {
        val url =
            Uri.parse("android.resource://" + context.packageName.toString() + "/" + R.raw.positive_scan_result)

        val r = RingtoneManager.getRingtone(context, url)
        r.play()
    }
    fun scanNegative(context: Context) {
        val url =
            Uri.parse("android.resource://" + context.packageName.toString() + "/" + R.raw.error_scan)

        val r = RingtoneManager.getRingtone(context, url)
        r.play()
    }
    fun scanCorrectColumn(context: Context) {
        val url =
            Uri.parse("android.resource://" + context.packageName.toString() + "/" + R.raw.correct_column_men)

        val r = RingtoneManager.getRingtone(context, url)
        r.play()
    }

    fun scanCorrectRow(context: Context) {
        val url =
            Uri.parse("android.resource://" + context.packageName.toString() + "/" + R.raw.correct_row_men)

        val r = RingtoneManager.getRingtone(context, url)
        r.play()
    }

    fun scanCorrectShelf(context: Context) {
        val url =
            Uri.parse("android.resource://" + context.packageName.toString() + "/" + R.raw.correct_shelf_men)

        val r = RingtoneManager.getRingtone(context, url)
        r.play()
    }
}