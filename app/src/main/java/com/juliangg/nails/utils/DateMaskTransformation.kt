package com.juliangg.nails.utils

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateMaskTransformation  : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateMaskFilter(text)
    }
}

fun dateMaskFilter(text: AnnotatedString): TransformedText {
    Log.i("TAG", "dateMaskFilter: ${text.text}")
    val tex = text.text.replace(":","")
    Log.i("TAG", "dateMaskFilter: $tex")
    val out = when (tex.length) {
        0 -> "0:00"
        1 -> "0:0$tex"
        2 -> "0:$tex"
        3 -> "${tex.subSequence(0,1)}:${tex.subSequence(1,3)}"
        else -> {
            val date = tex.substring(tex.length - 4)
            if (date.startsWith("1")){
                "${date.subSequence(0,2)}:${date.substring(2)}"
            } else {
                "${date.subSequence(1,2)}:${date.substring(2)}"
            }
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            Log.i("TAG", "originalToTransformed: $offset")
            if (offset <= 3) return 3
            return 4
        }

        override fun transformedToOriginal(offset: Int): Int {
            Log.i("TAG", "transformedToOriginal: $offset")
            if (offset <= 4) return 3
            return 4
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}