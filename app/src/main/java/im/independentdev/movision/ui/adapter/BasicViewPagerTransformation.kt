package im.independentdev.movision.ui.adapter

import android.view.View
import androidx.viewpager.widget.ViewPager

class BasicViewPagerTransformation : ViewPager.PageTransformer {
	override fun transformPage(page: View, position: Float) {
		val absPosition = Math.abs(position)
		if (absPosition >= 1) {
			page.scaleY = 0.85f
		} else {
			page.scaleY = (0.85f - 1) * absPosition + 1
		}
	}
}