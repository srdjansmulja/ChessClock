package com.example.chessclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var spinnerOne: Spinner
    lateinit var spinnerTwo: Spinner
    lateinit var playerOne: EditText
    lateinit var playerTwo: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerOne = findViewById(R.id.spinnerOne)
        spinnerTwo = findViewById(R.id.spinnerTwo)
        playerOne = findViewById(R.id.etPlayerOne)
        playerTwo = findViewById(R.id.etPlayerTwo)

        val button: Button = findViewById(R.id.buttonStart)
        button.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java)
            intent.putExtra("playerOneTime", spinnerOne.selectedItem.toString())
            intent.putExtra("playerTwoTime", spinnerTwo.selectedItem.toString())
            intent.putExtra("playerOneName", playerOne.text.toString())
            intent.putExtra("playerTwoName", playerTwo.text.toString())
            startActivity(intent)
        }


        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.minutes,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOne.adapter = adapter
        spinnerOne.onItemSelectedListener = this
        spinnerTwo.adapter = adapter
        spinnerTwo.onItemSelectedListener = this
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        //textView.text = text
    }
}