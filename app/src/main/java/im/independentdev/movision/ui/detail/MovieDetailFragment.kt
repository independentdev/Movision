package im.independentdev.movision.ui.detail

import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import im.independentdev.movision.R
import im.independentdev.movision.data.model.ui.CastViewItem
import im.independentdev.movision.databinding.FragmentMovieDetailBinding
import im.independentdev.movision.ui.adapter.CreditsAdapter
import im.independentdev.movision.ui.base.BaseFragment

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {
	
	override val layoutResourceId: Int = R.layout.fragment_movie_detail
	override val classTypeOfViewModel: Class<MovieDetailViewModel> = MovieDetailViewModel::class.java
	override val viewModel: MovieDetailViewModel by viewModels()
	
	override fun init() {
		super.init()
		baseView = binding.baseView
		viewModel.liveDataViewState.observe(viewLifecycleOwner) { setViewState(it) }
		viewModel.getMovieData()
	}
	
	private fun setViewState(fragmentViewState: MovieDetailViewModel.MovieDetailFragmentViewState) {
		// bind viewState
		binding.viewState = fragmentViewState
		binding.executePendingBindings()
		
		// Set Credits
		if (fragmentViewState.getCredits().isEmpty()) {
			binding.layoutCredits.visibility = View.GONE
		} else {
			binding.layoutCredits.visibility = View.VISIBLE
			setupCreditsViewPager(fragmentViewState.getCredits())
		}
		// set Genre
		binding.chipGroupGenres.apply {
			for (genre in fragmentViewState.getMovieDetailItems().genres) {
				val chip = Chip(context)
				chip.text = genre.name
				addView(chip)
			}
		}
	}
	
	private fun setupCreditsViewPager(items: List<CastViewItem>) {
		CreditsAdapter(requireContext()).apply {
			setItems(items)
			binding.layoutCredits.viewPager.adapter = this
		}
	}
	
}