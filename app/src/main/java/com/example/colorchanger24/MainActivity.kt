package com.example.colorchanger24

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView
import android.widget.SeekBar
import android.widget.Switch
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    var tvColor: TextView? = null
    var mycolor = intArrayOf(255, 127, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..2) {
            var myID = "sb_$i"

            var resID = resources.getIdentifier(myID, "id", packageName)
            val mySeekBar = findViewById(resID) as SeekBar
            mySeekBar.setOnSeekBarChangeListener(myListener)
            mySeekBar.setProgress(mycolor[i], false)

            myID = "tv_$i"
            resID = resources.getIdentifier(myID, "id", packageName)
            val myTextView = findViewById(resID) as TextView
            myTextView.setText(""+mycolor[i])
        }

        tvColor = findViewById<TextView>(R.id.tvColor)
        tvColor?.setBackgroundColor(Color.argb(
            255, mycolor[0], mycolor[1], mycolor[2] ))

        val reset = findViewById<Button>(R.id.reset_button);
        reset.setOnClickListener{
            mycolor[0] = 255
            mycolor[1] = 255
            mycolor[2] = 255

            for (i in 0..2) {
                var myID = "sb_$i"
                var resID = resources.getIdentifier(myID, "id", packageName)
                val mySeekBar = findViewById(resID) as SeekBar
                mySeekBar.setProgress(255, true)
            }

            tvColor?.setBackgroundColor(
                Color.argb(
                    255,
                    mycolor[0], mycolor[1], mycolor[2]
                )
            )
        }

        val switch0 = findViewById<Switch>(R.id.switch0);
        switch0.setOnClickListener{
            mycolor[0] = 0
            tvColor?.setBackgroundColor(
                Color.argb(
                    255,
                    mycolor[0], mycolor[1], mycolor[2]
                )
            )
        }
        val switch1 = findViewById<Switch>(R.id.switch1);
        switch1.setOnClickListener{
            mycolor[1] = 0
            tvColor?.setBackgroundColor(
                Color.argb(
                    255,
                    mycolor[0], mycolor[1], mycolor[2]
                )
            )
        }
        val switch2 = findViewById<Switch>(R.id.switch2);
        switch2.setOnClickListener{
            mycolor[2] = 0
            tvColor?.setBackgroundColor(
                Color.argb(
                    255,
                    mycolor[0], mycolor[1], mycolor[2]
                )
            )
        }

    }

    var myListener: SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            val myLayout = seekBar.parent as ConstraintLayout
            val myIndex = myLayout.indexOfChild(seekBar)
            var myTextView = myLayout.getChildAt(myIndex + 1) as TextView

            var preDouble = i * 100 / 255
            var double = preDouble.toDouble().roundToInt() / 100.0
            myTextView.text = ("" + double)
            mycolor[seekBar.tag.toString().toInt()]= i
            tvColor?.setBackgroundColor(
                Color.argb(
                    255,
                    mycolor[0], mycolor[1], mycolor[2]
                )
            )
        }
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }



}
