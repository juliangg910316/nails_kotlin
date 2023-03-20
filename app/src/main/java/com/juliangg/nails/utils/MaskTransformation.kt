package com.juliangg.nails.utils

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MaskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }
}

fun maskFilter(text: AnnotatedString): TransformedText {
    Log.i("TAG", "maskFilter: ${text.text}")
    val input = text.text.replace(".","")
    Log.i("TAG", "maskFilter: $input")
    val out: String = when (input.length) {
        0 -> "$0.00"
        1 -> "$0.0$input"
        2 -> "$0.$input"
        else -> {
            val pay: Int = Integer.valueOf(input.substring( 0, input.length - 2))
            "$${pay}.${
                input.subSequence(
                    startIndex = input.length - 2,
                    endIndex = input.length
                )
            }"
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            Log.i("TAG", "originalToTransformed: $offset")
            if (offset <= 6) return 5
            return offset - 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            Log.i("TAG", "transformedToOriginal: $offset")
            if (offset <= 4) return 4
            return offset + 1
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}