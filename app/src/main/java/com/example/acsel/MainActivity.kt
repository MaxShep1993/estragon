package com.example.acsel

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var sManager:SensorManager
    lateinit var text:TextView
    private var handler = Handler()
    var run=true
    private lateinit var imag:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text=findViewById(R.id.textView)
        imag=findViewById(R.id.imageView)
        val tvSensor=findViewById<TextView>(R.id.tvSensor)
        sManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor= sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        var a=0


        val sListener=object :SensorEventListener{
            override fun onSensorChanged(sEvent: SensorEvent?) {
                val value = sEvent?.values
                val x= value?.get(0)?.roundToInt()
                val sData="X:$x"

                tvSensor.text=sData
                val b= x?.plus(a)
                text.text=b.toString()
                imag.rotation= -b!!.toFloat()
                if(x!! >3){
                    a+=10

                }
                if(x!! <-3){
                    a+=-10


                }

            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }
            val a=50

        }
        sManager.registerListener(sListener,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }


}

