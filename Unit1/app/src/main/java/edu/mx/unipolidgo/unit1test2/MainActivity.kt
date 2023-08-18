package edu.mx.unipolidgo.unit1test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButton = findViewById<Button>(R.id.RED)
        val text = findViewById<TextView>(R.id.textView5)

        val ButtonTwo = findViewById<Button>(R.id.button5)
        val textChange = findViewById<TextView>(R.id.textView4)

        val ButtonThree = findViewById<Button>(R.id.button6)
        val textChangeGreen = findViewById<TextView>(R.id.textView3)

        myButton.setOnClickListener {
            text.setBackgroundColor(ContextCompat.getColor(this, R.color.Red))


        }

        ButtonTwo.setOnClickListener {
            textChange.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
        }

        ButtonThree.setOnClickListener {
            textChangeGreen.setBackgroundColor(ContextCompat.getColor(this, R.color.Green))
        }

        text.setOnClickListener {
            text.setBackgroundColor(ContextCompat.getColor(this, R.color.Green2))
        }

        textChange.setOnClickListener {
            textChange.setBackgroundColor(ContextCompat.getColor(this, R.color.Green3))
        }

        textChangeGreen.setOnClickListener {
            textChangeGreen.setBackgroundColor(ContextCompat.getColor(this, R.color.Green2))
        }

    }
}