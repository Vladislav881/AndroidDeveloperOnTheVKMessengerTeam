package com.example.androiddeveloperonthevkmessengerteam.custom_view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.androiddeveloperonthevkmessengerteam.R
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class ClockView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val hourPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 8f
        isAntiAlias = true
    }

    private val minutePaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 6f
        isAntiAlias = true
    }

    private val secondPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 4f
        isAntiAlias = true
    }

    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f

    private var backgroundImage: Bitmap? = null

    init {
        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ClockView,
            0, 0
        )

        try {

            background = attributes.getDrawable(R.styleable.ClockView_backgroundImage)

        } finally {
            attributes.recycle()
        }

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                postInvalidate()
            }
        }, 0, 1000)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val size = if (width < height) width else height
        setMeasuredDimension(size, size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        radius = (w / 2f) * 0.8f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY) % 12
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        drawHand(canvas, hour / 12f * 360f, 0.5f * radius, hourPaint)
        drawHand(canvas, minute / 60f * 360f, 0.7f * radius, minutePaint)
        drawHand(canvas, second / 60f * 360f, 0.8f * radius, secondPaint)
    }

    private fun drawHand(canvas: Canvas, angle: Float, length: Float, paint: Paint) {
        val x = centerX + length * sin(Math.toRadians(angle.toDouble())).toFloat()
        val y = centerY - length * cos(Math.toRadians(angle.toDouble())).toFloat()
        canvas.drawLine(centerX, centerY, x, y, paint)
    }
}
