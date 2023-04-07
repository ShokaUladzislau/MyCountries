package com.example.mycountries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mycountries.R
import com.example.mycountries.di.DaggerAPIComponent
import com.example.mycountries.viewmodel.CountriesViewModel
import javax.inject.Inject


class CountriesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: CountriesViewModel
    lateinit var countriesAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAPIComponent.create().inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[CountriesViewModel::class.java]
        countriesAdapter = CountryListAdapter(arrayListOf())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.refresh()

        val view: View = inflater.inflate(R.layout.fragment_countries, container, false)

        view.findViewById<RecyclerView>(R.id.countriesList)?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).setOnRefreshListener {
            view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()

        return view
    }


    private fun observeViewModel() {

        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                view?.findViewById<RecyclerView>(R.id.countriesList)?.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                view?.findViewById<TextView>(R.id.list_error)?.visibility =
                    if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                view?.findViewById<ProgressBar>(R.id.loading_view)?.visibility =
                    if (it) View.VISIBLE else View.GONE
                if (it) {
                    view?.findViewById<TextView>(R.id.list_error)?.visibility = View.GONE
                    view?.findViewById<RecyclerView>(R.id.countriesList)?.visibility = View.GONE
                }
            }
        })
    }
}