package com.kurcheuskaya.goldenequatortech.presentation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kurcheuskaya.goldenequatortech.databinding.MovieDetailsFragmentBinding
import com.kurcheuskaya.goldenequatortech.domain.Movie

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: MovieDetailsFragmentBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(MOVIE_ID_KEY) ?: return

        viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                displayMovieDetails(movie)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                // Handle error message display, if needed
            }
        })

        viewModel.fetchMovieDetails(movieId)
    }

    private fun displayMovieDetails(movie: Movie) {
        // Set the movie details to the corresponding views in the layout
        binding.movieTitleTextView.text = movie.title
        binding.movieOverviewTextView.text = movie.overview
        binding.movieReleaseDateTextView.text = movie.releaseDate
        // Set other movie details as needed
    }

    companion object {
        private const val MOVIE_ID_KEY = "movie_id"

        fun newInstance(movieId: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle().apply {
                putInt(MOVIE_ID_KEY, movieId)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
