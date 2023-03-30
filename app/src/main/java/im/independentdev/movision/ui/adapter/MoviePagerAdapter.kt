package im.independentdev.movision.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import im.independentdev.movision.R
import im.independentdev.movision.data.model.ui.MovieViewItem
import im.independentdev.movision.databinding.ItemMainSliderBinding
import im.independentdev.movision.databinding.ItemMovieBinding
import androidx.databinding.library.baseAdapters.BR


/**
 * Adapter for Movie list view pagers in [.MainFragment]
 * @param itemType to define item view as this adapter
 * supports 2 different item layout for small and large items
 */
class MoviePagerAdapter(context: Context, private val itemType: ITEMTYPE) : PagerAdapter() {
	private var movieItem = emptyList<MovieViewItem>()
	
	fun setItem(movieItem: List<MovieViewItem>) {
		this.movieItem = movieItem
	}
	
	private var inflater: LayoutInflater = LayoutInflater.from(context)
	
	override fun isViewFromObject(view: View, `object`: Any): Boolean {
		return view == `object`
	}
	
	override fun getCount(): Int {
		return (movieItem.size)
	}
	
	override fun instantiateItem(container: ViewGroup, position: Int): Any {
		val binding = if (itemType == ITEMTYPE.SMALL) DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, container, false)
		else DataBindingUtil.inflate<ItemMainSliderBinding>(inflater, R.layout.item_main_slider, container, false)
		binding.setVariable(BR.movie, movieItem[position])
		container.addView(binding.root)
		
		binding.root.setOnClickListener { onMovieItemClick?.invoke(movieItem[position]) }
		
		return binding.root
	}
	
	override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
		container.removeView(`object` as View)
	}
	
	override fun getPageWidth(position: Int): Float {
		return if (itemType == ITEMTYPE.SMALL) 0.29f else 1.0f
	}
	
	enum class ITEMTYPE {
		SMALL,
		LARGE
	}
	
	/** Item Click Functions **/
	var onMovieItemClick: ((MovieViewItem) -> Unit)? = null
	
}