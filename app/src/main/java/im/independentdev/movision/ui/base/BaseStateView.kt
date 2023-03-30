package im.independentdev.movision.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import im.independentdev.movision.R


class BaseStateView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {
	
	private var loadingView: View? = null
	private var emptyView: View? = null
	private var errorView: View? = null
	
	/** EmptyView View **/
	private var imageResIdOfEmptyView: Int? = null
	private var textOfEmptyView: String? = null
	private var imageViewOfEmptyView: ImageView? = null
	private var textViewOfEmptyView: TextView? = null
	
	/** ErrorView View **/
	private var imageResIdOfErrorView: Int? = null
	private var textOfErrorView: String? = null
	private var buttonTextOfErrorView: String? = null
	private var imageViewOfErrorView: ImageView? = null
	private var textViewOfErrorView: TextView? = null
	private var buttonOfErrorView: MaterialButton? = null
	
	private var baseStateViewListener: BaseStateViewListener? = null
	
	init {
		val inflater = LayoutInflater.from(getContext())
		
		attrs.let {
			val a = context.obtainStyledAttributes(attrs, R.styleable.BaseStateView)
			
			/** LoadingView  **/
			val loadingViewResId = a.getResourceId(R.styleable.BaseStateView_loadingView, R.layout.layout_loading)
			if (loadingViewResId != -1) {
				val inflatedLoadingView = inflater.inflate(loadingViewResId, this, false)
				this.loadingView = inflatedLoadingView
			}
			
			/** EmptyView  **/
			val emptyViewResId = a.getResourceId(R.styleable.BaseStateView_emptyView, R.layout.layout_empty)
			if (emptyViewResId != -1) {
				val inflatedEmptyView = inflater.inflate(emptyViewResId, this, false)
				this.emptyView = inflatedEmptyView
				
				/** Default empty layout **/
				if (emptyViewResId == R.layout.layout_empty) {
					/** Set Empty State   **/
					this.imageResIdOfEmptyView = a.getResourceId(R.styleable.BaseStateView_imageOfEmptyView, R.drawable.ic_mood_bad_black_24dp)
					this.textOfEmptyView = a.getString(R.styleable.BaseStateView_textOfEmptyView) ?: "Something went wrong."
					
					/** Set views in empty layout **/
					this.imageViewOfEmptyView = emptyView?.findViewById(R.id.image)
					this.textViewOfEmptyView = emptyView?.findViewById(R.id.text)
					
					initEmptyViewFields()
				}
			}
			
			/** ErrorView  **/
			val errorViewResId = a.getResourceId(R.styleable.BaseStateView_errorView, R.layout.layout_error)
			if (errorViewResId != -1) {
				val inflatedErrorView = inflater.inflate(errorViewResId, this, false)
				this.errorView = inflatedErrorView
				
				/** Default error layout **/
				if (errorViewResId == R.layout.layout_error) {
					/** Set error State **/
					this.imageResIdOfErrorView = a.getResourceId(R.styleable.BaseStateView_imageOfErrorView, R.drawable.ic_error_outline_black_24dp)
					this.textOfErrorView = a.getString(R.styleable.BaseStateView_textOfErrorView) ?: "here is error view."
					this.buttonTextOfErrorView = a.getString(R.styleable.BaseStateView_buttonTextOfErrorView) ?: "Retry"
					
					/** Set views in error layout **/
					this.imageViewOfErrorView = errorView?.findViewById(R.id.image)
					this.textViewOfErrorView = errorView?.findViewById(R.id.text)
					this.buttonOfErrorView = errorView?.findViewById(R.id.button)
					
					initErrorViewFields(a.getBoolean(R.styleable.BaseStateView_buttonVisibilityOfErrorView, true))
				}
			}
			
			a.recycle()
		}
	}
	
	/**
	 * Set Custom values for Empty State
	 */
	private fun initEmptyViewFields() {
		imageViewOfEmptyView?.setImageResource(imageResIdOfEmptyView!!)
		textViewOfEmptyView?.text = textOfEmptyView
	}
	
	/**
	 * Set Custom values for Error State
	 */
	private fun initErrorViewFields(buttonVisibility: Boolean) {
		imageViewOfErrorView?.setImageResource(imageResIdOfErrorView!!)
		textViewOfErrorView?.text = textOfErrorView
		buttonOfErrorView?.text = buttonTextOfErrorView
		
		buttonOfErrorView?.isVisible = buttonVisibility
		
		/**
		 * Set clickListener in Error view
		 */
		buttonOfErrorView?.setOnClickListener { baseStateViewListener?.onBaseButtonClick() }
	}
	
	/**
	 * Set Loading state active and display loading view, remove other views
	 */
	fun showLoading() {
		if (loadingView?.parent == null) this.addView(loadingView, loadingView?.layoutParams)
		if (emptyView?.parent == this) this.removeView(emptyView)
		if (errorView?.parent == this) this.removeView(errorView)
		setOtherChildVisibility(false, loadingView)
	}
	
	/**
	 * Display content view and remove other views
	 */
	fun showContent() {
		if (loadingView?.parent == this) this.removeView(loadingView)
		setOtherChildVisibility(true, null)
	}
	
	/**
	 * Display Empty view
	 * for example no result found state in search
	 */
	fun showEmpty() {
		if (loadingView?.parent == this) this.removeView(loadingView)
		if (emptyView?.parent == null) this.addView(emptyView, emptyView?.layoutParams)
		setOtherChildVisibility(false, emptyView)
	}
	
	/**
	 * Display Error view
	 */
	fun showError() {
		if (loadingView?.parent == this) this.removeView(loadingView)
		if (errorView?.parent == null) this.addView(errorView, errorView?.layoutParams)
		setOtherChildVisibility(false, errorView)
	}
	
	/**
	 * Reset all the other views visibilty other than given view
	 */
	
	private fun setOtherChildVisibility(state: Boolean, view: View?) {
		for (i in 0 until this.childCount)
			if (getChildAt(i) != view)
				getChildAt(i).isVisible = state
	}
	
	fun setErrorText(message: String?) {
		textViewOfErrorView?.text = message
	}
	
}

interface BaseStateViewListener {
	fun onBaseButtonClick()
}