package com.example.drawingonfragment
import android.graphics.Color
import android.graphics.Paint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment(), SurfaceHolder.Callback {
    private var communicationListener: CommunicationListener? = null

    companion object {
        private const val TYPE = "type"

        fun newInstance(type: String, communicationListener: CommunicationListener): SecondFragment {
            val fragment = SecondFragment()
            fragment.setCommunicationListener(communicationListener)
            val args = Bundle()
            args.putString(TYPE, type)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var surfaceView: SurfaceView
    private lateinit var type: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton: Button = view.findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            communicationListener?.onBackButtonClicked()
        }

        surfaceView = view.findViewById(R.id.surfaceView)
        arguments?.let {
            type = it.getString(TYPE).toString()
        }
        surfaceView.holder.addCallback(this)
    }

    private fun generateRandomColor(holder: SurfaceHolder) {
        val canvas = holder.lockCanvas()

        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)

        canvas.drawColor(Color.rgb(red, green, blue))
        holder.unlockCanvasAndPost(canvas)
    }

    private fun generateRandomShape(holder: SurfaceHolder) {
        val canvas = holder.lockCanvas()
        val paint = Paint()
        canvas.drawColor(Color.WHITE)

        val randomColor: Int = Color.BLUE
        paint.color = randomColor

        val radius = 300f
        val centerX = canvas.width / 2f
        val centerY = canvas.height / 2f
        var message = ""
        val randomNumber = (0..2).random()

        if (randomNumber == 0) {
            canvas.drawCircle(centerX, centerY - 200f, radius, paint)
           message = "circle"
        }
        if (randomNumber == 1) {
            canvas.drawRect(centerX - 100f, centerY, centerX + 100f, 100.0f, paint)
            message = "rectangle"
        }
        if (randomNumber == 2) {
            canvas.drawOval(centerX - 200f, centerY, centerX + 200.0f, 200.0f, paint)
            message = "oval"
        }
        paint.textSize = 60f
        canvas.drawText(message, centerX - 100f, centerY + 500f, paint)
        holder.unlockCanvasAndPost(canvas)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if(type == "color") {
            generateRandomColor(holder)
        }
        if(type == "shape") {
            generateRandomShape(holder)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    fun setCommunicationListener(listener: CommunicationListener) {
        this.communicationListener = listener
    }

}