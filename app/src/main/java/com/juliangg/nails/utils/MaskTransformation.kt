package com.juliangg.nails.utils

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

    val out: String
    when (text.text.length) {
        0 -> out = "$0.00"
        1 -> out = "$0.0${text.text}"
        2 -> out = "$0.${text.text}"
        else -> {
            out = "$${
                text.text.subSequence(
                    0,
                    text.text.length - 2
                )
            }.${
                text.text.subSequence(
                    startIndex = text.text.length - 2,
                    endIndex = text.text.length
                )
            }"
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return 5
            return offset + 2
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 5) return 3
            return offset - 2
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}