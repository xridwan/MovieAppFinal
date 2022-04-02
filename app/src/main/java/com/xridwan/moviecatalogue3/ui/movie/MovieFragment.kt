package com.xridwan.moviecatalogue3.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.xridwan.moviecatalogue3.R
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.databinding.FragmentMovieBinding
import com.xridwan.moviecatalogue3.ui.detail.DetailActivity
import com.xridwan.moviecatalogue3.ui.detail.DetailViewModel.Companion.MOVIE
import com.xridwan.moviecatalogue3.utils.Ext.onToast
import com.xridwan.moviecatalogue3.utils.SortUtils
import com.xridwan.moviecatalogue3.viewmodel.ViewModelFactory
import com.xridwan.moviecatalogue3.vo.Resource
import com.xridwan.moviecatalogue3.vo.Status

class MovieFragment : Fragment(), MovieAdapter.MovieFragmentCallback {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()
            viewModel.getMovies(SortUtils.HIGHEST).observe(viewLifecycleOwner, movieObserver)

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    movieAdapter.submitList(movies.data)
                    movieAdapter.setOnItemClickCallback(this)
                }
                Status.ERROR -> {
                    binding?.progressBar?.visibility = View.GONE
                    onToast("Terjadi kesalahan")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_highest -> sort = SortUtils.HIGHEST
            R.id.action_lowest -> sort = SortUtils.LOWEST
        }
        viewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id)
        intent.putExtra(DetailActivity.EXTRA_CONTENT, MOVIE)
        context?.startActivity(intent)
    }

    override fun onShareClick(movie: MovieEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan film ini sekarang")
                .setText(resources.getString(R.string.share_movie, movie.title))
                .startChooser()
        }
    }
}