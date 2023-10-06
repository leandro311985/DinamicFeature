package com.example.dinamicfeature

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class RoundButton(context: Context, attrs: AttributeSet) : View(context, attrs) {

  private var isButtonPressed = false
  private val normalColor = Color.parseColor("#e6e6e6ff")
  private val pressedColor = Color.parseColor("#FF931E")
  private val paint = Paint()


  init {
    // Defina o comportamento do botão ao ser clicado
    setOnClickListener {
      isButtonPressed = !isButtonPressed
      invalidate()
    }
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    // Defina a cor com base no estado do botão
    paint.color = if (isButtonPressed) pressedColor else normalColor

    // Desenhe o círculo do botão
    val centerX = width / 2.toFloat()
    val centerY = height / 2.toFloat()
    val radius = Math.min(centerX, centerY)
    canvas?.drawCircle(centerX, centerY, radius, paint)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    when (event?.action) {
      MotionEvent.ACTION_DOWN -> {
        isButtonPressed = true
        invalidate()
        return true
      }

      MotionEvent.ACTION_UP -> {
        isButtonPressed = false
        invalidate()
        performClick()
        return true
      }
    }
    return super.onTouchEvent(event)
  }

  override fun performClick(): Boolean {
    super.performClick()
    return true
  }

}



