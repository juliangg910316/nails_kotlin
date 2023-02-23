package com.juliangg.nails.utils

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

    val out = when (text.text.length) {
        0 -> "0:00"
        1 -> "0:0${text.text}"
        2 -> "0:${text.text}"
        3 -> "${text.text.subSequence(0,1)}:${text.text.subSequence(1,3)}"
        else -> {
            val date = text.text.substring(text.text.length - 4)
            if (date.startsWith("1")){
                "${date.subSequence(0,2)}:${date.substring(2)}"
            } else {
                "${date.subSequence(1,2)}:${date.substring(2)}"
            }
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return 4
            return 5
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return 3
            return 4
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}