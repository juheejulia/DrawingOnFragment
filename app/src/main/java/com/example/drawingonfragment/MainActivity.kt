package com.example.drawingonfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), CommunicationListener {

    private val firstFragment = FirstFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstFragment.setCommunicationListener(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, firstFragment)
            .commit()
    }

    override fun onColorButtonClicked() {
        val secondFragment = SecondFragment
            .newInstance("color", this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, secondFragment)
            .commit()
    }

    override fun onShapeButtonClicked() {
        val secondFragment = SecondFragment
            .newInstance("shape", this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, secondFragment)
            .commit()
    }

    override fun onBackButtonClicked() {
        val firstFragment = FirstFragment()
        firstFragment.setCommunicationListener(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, firstFragment)
            .commit()
    }
}