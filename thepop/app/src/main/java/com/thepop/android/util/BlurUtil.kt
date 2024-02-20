package com.thepop.android.util

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.*

object BlurUtil {

    fun blur(context: Context, bitmap: Bitmap, radius: Float): Bitmap {
        val rsContext: RenderScript = RenderScript.create(context)
        val input = Allocation.createFromBitmap(rsContext, bitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
        val output = Allocation.createTyped(rsContext, input.type)
        val script = ScriptIntrinsicBlur.create(rsContext, Element.U8_4(rsContext))
        script.setRadius(radius)
        script.setInput(input)
        script.forEach(output)
        output.copyTo(bitmap)
        rsContext.finish()
        return bitmap
    }
}