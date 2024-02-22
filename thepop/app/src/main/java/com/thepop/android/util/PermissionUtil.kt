package com.thepop.android.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.thepop.android.ui.community.WriteActivity


class PermissionUtil {
    companion object{
        private const val READ_MEDIA_IMAGES = 123

        fun checkGalleryPermission(context: Context?): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED
            } else true
        }

        fun requestGalleryPermission(context: Context?) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(
                    context as AppCompatActivity,
                    arrayOf<String>(Manifest.permission.READ_MEDIA_IMAGES),
                    READ_MEDIA_IMAGES
                )
            }
        }

        fun openGallery(writeActivity: WriteActivity) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            writeActivity.startActivityForResult(intent, READ_MEDIA_IMAGES)

        }
    }
}