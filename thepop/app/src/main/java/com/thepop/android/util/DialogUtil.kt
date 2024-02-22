package com.thepop.android.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.FragmentActivity
import com.thepop.android.databinding.DialogMoreBinding

class DialogUtil(private val context: FragmentActivity) {

    private lateinit var binding: DialogMoreBinding
    private val dialog = Dialog(context)

    fun show(callback: (Boolean) -> Unit) {
        binding = DialogMoreBinding.inflate(context.layoutInflater)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.dialogText.setOnClickListener {
            dialog.dismiss()
            callback.invoke(true)
        }

        binding.dialogCancel.setOnClickListener {
            dialog.dismiss()
            callback.invoke(false)
        }
    }
}