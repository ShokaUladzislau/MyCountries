package com.example.mycountries.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycountries.R
import com.example.mycountries.model.Country
import com.example.mycountries.util.getProgressDrawable
import com.example.mycountries.util.loadImage

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_country, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val countryImage: ImageView = view.findViewById(R.id.flag)
        private val countryName: TextView = view.findViewById(R.id.name)
        private val countryCapital: TextView = view.findViewById(R.id.capital)

        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName.text = country.countryName
            countryCapital.text = country.capital
            countryImage.loadImage(country.flag, progressDrawable)
        }
    }
}