package im.independentdev.movision.ui.base

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import im.independentdev.movision.R


@BindingAdapter("visibility")
fun setVisibility(view: View, value: Boolean) {
	view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl", "applyCorner")
fun setImageUrl(imageView: ImageView, url: String?, applyCorner: Boolean) {
	if (url.isNullOrEmpty()) return
	
	Glide
		.with(imageView.context)
		.load(url)
		.placeholder(R.drawable.placeholder)
		.diskCacheStrategy(DiskCacheStrategy.ALL)
		.apply(RequestOptions().transform(CenterCrop(), RoundedCorners(if (applyCorner) 32 else 1)))
		.into(imageView)
}