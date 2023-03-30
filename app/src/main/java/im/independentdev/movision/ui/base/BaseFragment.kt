package im.independentdev.movision.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import im.independentdev.movision.R
import im.independentdev.movision.core.orFalse
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
	
	@get:LayoutRes
	protected abstract val layoutResourceId: Int
	lateinit var binding: DB
	protected abstract val viewModel: VM
	protected abstract val classTypeOfViewModel: Class<VM>
	private var hasRequestSend = false
	protected var baseView: BaseStateView? = null
	
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		arguments?.let {
			viewModel.handleIntent(it)
		}
		
		// Observe baseViewModel's state
		viewModel.baseViewStateLiveData.observe(viewLifecycleOwner, ::onStateChanged)
		
		// call for child fragment init function
		init()
	}
	
	open fun init() {}
	
	private fun onStateChanged(stateView: BaseViewState?) {
		when {
			stateView?.showLoading.orFalse() -> baseView?.showLoading()
			stateView?.showContent.orFalse() -> baseView?.showContent()
			stateView?.showEmpty.orFalse() -> baseView?.showEmpty()
			stateView?.showError.orFalse() -> {
				requireContext().let { context ->
					val errorMsg = stateView?.throwable?.getErrorMessage(context)
					baseView?.showError()
					baseView?.setErrorText(errorMsg)
				}
			}
		}
	}
	
	private fun Throwable.getErrorMessage(context: Context): String {
		return when (this) {
			is HttpException -> context.getString(R.string.error_unknown)
			is SocketTimeoutException -> context.getString(R.string.error_connection_timeout)
			is IOException -> context.getString(R.string.error_connection_not_found)
			else -> context.getString(R.string.error_unknown)
		}
	}
}