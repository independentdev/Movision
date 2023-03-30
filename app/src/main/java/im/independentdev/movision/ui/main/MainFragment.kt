package im.independentdev.movision.ui.main

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.viewpager.widget.ViewPager
import dagger.hilt.android.AndroidEntryPoint
import im.independentdev.movision.R
import im.independentdev.movision.data.model.ui.MovieViewItem
import im.independentdev.movision.databinding.FragmentMainBinding
import im.independentdev.movision.ui.adapter.BasicViewPagerTransformation
import im.independentdev.movision.ui.adapter.MoviePagerAdapter
import im.independentdev.movision.ui.base.BaseFragment


/**
 * [BaseFragment] subclass.
 * It is root view of the navigation and
 * It is to display Now Playing, Popular and Upcoming movies
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
	
	override val layoutResourceId: Int = R.layout.fragment_main
	override val classTypeOfViewModel: Class<MainViewModel> = MainViewModel::class.java
	override val viewModel: MainViewModel by viewModels()
	
	override fun init() {
		super.init()
		binding.vm = viewModel
		baseView = binding.baseView
		viewModel.liveMoviesViewState.observe(viewLifecycleOwner) { setViewState(it) }
		viewModel.getMovies()
	}
	
	private fun setViewState(fragmentViewState: MainViewModel.MoviesViewState) {
		
		// Set Now Playing Movies
		if (fragmentViewState.nowPlayingMovies.movies.isEmpty()) {
			binding.viewPagerNowPlayingMovies.visibility = View.GONE
		} else {
			binding.viewPagerNowPlayingMovies.visibility = View.VISIBLE
			setupLargeItemViewPagerAdapter(fragmentViewState.nowPlayingMovies.movies)
		}
		
		// Set Popular Movies
		if (fragmentViewState.popularMovies.movies.isEmpty()) {
			binding.layoutPopularMovies.visibility = View.GONE
		} else {
			binding.layoutPopularMovies.visibility = View.VISIBLE
			setupSmallItemViewPagerAdapter(fragmentViewState.popularMovies.movies, binding.layoutPopularMovies.viewPager)
		}
		
		// Set Upcoming Movies
		if (fragmentViewState.upcomingMovies.movies.isEmpty()) {
			binding.layoutUpcomingMovies.visibility = View.GONE
		} else {
			binding.layoutUpcomingMovies.visibility = View.VISIBLE
			setupSmallItemViewPagerAdapter(fragmentViewState.upcomingMovies.movies, binding.layoutUpcomingMovies.viewPager)
		}
	}
	
	/**
	 * Set adapter to display Upcoming and Popular Movies
	 */
	private fun setupSmallItemViewPagerAdapter(movies: List<MovieViewItem>, viewPager: ViewPager) {
		MoviePagerAdapter(requireContext(), MoviePagerAdapter.ITEMTYPE.SMALL).apply {
			setItem(movies)
			onMovieItemClick = ::onMovieItemClick
			viewPager.adapter = this
		}
	}
	
	/**
	 * Set adapter to display Now Playing Movies
	 */
	private fun setupLargeItemViewPagerAdapter(movies: List<MovieViewItem>) {
		MoviePagerAdapter(requireContext(), MoviePagerAdapter.ITEMTYPE.LARGE).apply {
			setItem(movies)
			onMovieItemClick = ::onMovieItemClick
			binding.viewPagerNowPlayingMovies.adapter = this
		}
		binding.viewPagerNowPlayingMovies.apply {
			pageMargin = 60
			setPageTransformer(false, BasicViewPagerTransformation())
			currentItem = movies.size / 2
		}
	}
	
	private fun onMovieItemClick(movieItem: MovieViewItem) {
		findNavController(this@MainFragment.requireView()).navigate(MainFragmentDirections.actionMainFragmentToMovieDetailFragment(movieId = movieItem.id))
	}
}