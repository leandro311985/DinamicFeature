package com.example.dinamicfeature.commons.custumView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dinamicfeature.R
import com.example.dinamicfeature.databinding.ToolbarLayoutBinding

class ToolbarApp @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


  private var title: String? = null
  private var favorite: Boolean? = null

  private val binding = ToolbarLayoutBinding.inflate(LayoutInflater.from(context))

  init {
    setLayout(attrs)
  }


  private fun setLayout(attrs: AttributeSet?) {
    attrs?.let { attributeSet ->
      val attibutes = context.obtainStyledAttributes(attributeSet, R.styleable.ToolbarApp)
      val titleId = attibutes.getResourceId(R.styleable.ToolbarApp_toolbar_title, 0)
      setBackgroundResource(R.color.purple_500)
      if (titleId != 0){
        title = context.getString(titleId)
      }
      attibutes.recycle()
    }
  }
}