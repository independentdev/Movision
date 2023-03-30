package im.independentdev.movision.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.viewpager.widget.PagerAdapter
import im.independentdev.movision.R
import im.independentdev.movision.data.model.ui.CastViewItem
import im.independentdev.movision.databinding.ItemCastBinding


/**
 * Adapter for Cast view pager in [.MovieDetailFragment]
 */
class CreditsAdapter(context: Context) : PagerAdapter() {
	private var castItem = emptyList<CastViewItem>()
	
	fun setItems(item: List<CastViewItem>) {
		this.castItem = item
	}
	
	private var inflater: LayoutInflater = LayoutInflater.from(context)
	
	override fun isViewFromObject(view: View, `object`: Any): Boolean {
		return view == `object`
	}
	
	override fun getCount(): Int {
		return (castItem.size)
	}
	
	override fun instantiateItem(container: ViewGroup, position: Int): Any {
		val binding = DataBindingUtil.inflate<ItemCastBinding>(inflater, R.layout.item_cast, container, false)
		binding.setVariable(BR.cast, castItem[position])
		container.addView(binding.root)
		
		return binding.root
	}
	
	override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
		container.removeView(`object` as View)
	}
	
	override fun getPageWidth(position: Int): Float {
		return 0.29f
	}
	
}