package com.seryoga.sturmstorages.util

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.ReplacementSpan

class ColoredUnderlineSpan(private val underlineColor: Int) : ReplacementSpan() {
    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        return paint.measureText(text, start, end).toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val textToDraw = text.subSequence(start, end).toString()
        canvas.drawText(textToDraw, x, y.toFloat(), paint)

        // Save original paint color
        val originalColor = paint.color

        // Set underline color
        paint.color = underlineColor
        val underlineY = y + paint.strokeWidth // slightly below baseline
        canvas.drawLine(
            x,
            underlineY,
            x + paint.measureText(textToDraw),
            underlineY,
            paint
        )

        // Restore original color
        paint.color = originalColor
    }
}