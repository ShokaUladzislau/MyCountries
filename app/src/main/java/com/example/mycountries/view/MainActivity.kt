package com.example.mycountries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycountries.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_view, CountriesFragment())
                .commit()
        }
    }
}