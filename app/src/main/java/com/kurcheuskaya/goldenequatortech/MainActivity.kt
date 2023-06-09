package com.kurcheuskaya.goldenequatortech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kurcheuskaya.goldenequatortech.presentation.MovieListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, MovieListFragment())
                .commit()
        }
    }
}