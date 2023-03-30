package im.independentdev.movision.ui.base

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class WrapContentViewPager : ViewPager {
	constructor(context: Context) : super(context)
	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
	
	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		var heightMeasureSpecVar = heightMeasureSpec
		val mode = MeasureSpec.getMode(heightMeasureSpecVar)
		if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpecVar)
			var height = 0
			for (i in 0 until childCount) {
				val child = getChildAt(i)
				child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
				val childMeasuredHeight = child.measuredHeight
				if (childMeasuredHeight > height) {
					height = childMeasuredHeight
				}
			}
			heightMeasureSpecVar = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpecVar)
	}
}