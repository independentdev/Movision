package im.independentdev.movision.ui.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import im.independentdev.movision.util.SingleLiveEventKt
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


abstract class BaseViewModel(
	val baseViewStateLiveData: SingleLiveEventKt<BaseViewState> = SingleLiveEventKt()
) : ViewModel() {
	private val compositeDisposable: CompositeDisposable = CompositeDisposable()
	fun Disposable.track() = compositeDisposable.add(this)
	open fun handleIntent(extras: Bundle) {}
	
	inline fun <T : Any> Observable<T>.sendRequest(
		crossinline successHandler: (T) -> Unit
	) {
		updateUIState(showLoading = true)
		subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				{ data ->
					successHandler(data)
					updateUIState(showContent = true)
				},
				{ throwable ->
					updateUIState(showError = true, throwable = throwable)
				}).track()
	}
	
	fun updateUIState(
		showLoading: Boolean = false,
		showContent: Boolean = false,
		showError: Boolean = false,
		showEmpty: Boolean = false,
		throwable: Throwable? = null
	) {
		baseViewStateLiveData.value = BaseViewState(showLoading, showContent, showError, showEmpty, throwable)
	}
	
	override fun onCleared() = compositeDisposable.dispose()
	
}