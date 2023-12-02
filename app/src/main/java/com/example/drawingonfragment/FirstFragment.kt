package com.example.drawingonfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FirstFragment : Fragment() {

    private var communicationListener: CommunicationListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorButton: Button = view.findViewById(R.id.btn_color)
        colorButton.setOnClickListener {
            communicationListener?.onColorButtonClicked()
        }

        val shapeButton: Button = view.findViewById(R.id.btn_shape)
        shapeButton.setOnClickListener {
            communicationListener?.onShapeButtonClicked()
        }
    }

    fun setCommunicationListener(listener: CommunicationListener) {
        this.communicationListener = listener
    }
}