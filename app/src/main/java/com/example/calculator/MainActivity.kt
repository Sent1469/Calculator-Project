package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

var result = "0"
var numberTwo = "0"
var firstNum = true
var mathFunc = ""
// Locks to not let invalid input into the calculator
var opLock = false;
var ariLock = false;
var decLock = false;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)
        val btn5 = findViewById<Button>(R.id.button5)
        val btn6 = findViewById<Button>(R.id.button6)
        val btn7 = findViewById<Button>(R.id.button7)
        val btn8 = findViewById<Button>(R.id.button8)
        val btn9 = findViewById<Button>(R.id.button9)
        val addBtn = findViewById<Button>(R.id.addBtn)
        val subBtn = findViewById<Button>(R.id.subBtn)
        val multBtn = findViewById<Button>(R.id.multBtn)
        val divBtn = findViewById<Button>(R.id.divBtn)
        val decBtn = findViewById<Button>(R.id.decimalBtn)
        val equalsBtn = findViewById<Button>(R.id.equalsBtn)
        val clearBtn = findViewById<Button>(R.id.clearBtn)
        var calcText = findViewById<TextView>(R.id.textView)

        val intButtons = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        val ariButtons = arrayOf(addBtn, subBtn, multBtn, divBtn)
        val operators = arrayOf("+", "-", "*", "/")
        // Creates all the number and arithmetic buttons and sets up even listeners for them
        for(i in 0..8) {
            intButtons[i].setOnClickListener {
                if(!opLock) {
                    calcText.append((i+1).toString())
                    firstOrSecond((i+1).toString())
                }
            }
        }
        for(i in 0..3) {
            ariButtons[i].setOnClickListener {
                if(!ariLock) {
                    setOperator(operators[i])
                    calcText.append(operators[i])
                    opLock = false;
                    ariLock = true;
                    decLock = false;
                }
            }
        }
        equalsBtn.setOnClickListener {
            result = evaluate(mathFunc, result, numberTwo)
            numberTwo = "0"
            mathFunc = ""
            calcText.text = result
        }
        clearBtn.setOnClickListener {
            calcText.text = ""
            mathFunc = ""
            result = "0"
            numberTwo = "0"
            firstNum = true
            opLock = false;
            ariLock = false;
            decLock = false;
        }
        decBtn.setOnClickListener {
            if(!opLock && !decLock) {
                calcText.append(".")
                firstOrSecond(".")
                decLock = true;
            }
        }
    }
    // Handles evaluating expressions based off of the mathFunc that has been selected
    private fun evaluate(func: String, first: String, second: String) : String {
        opLock = true;
        ariLock = false;
        decLock = false;
        var num = 0.0
        if(func == "+") {
            num = first.toDouble() + second.toDouble()
        } else if(func == "-") {
            num = first.toDouble() - second.toDouble()
        } else if(func == "*") {
            num = first.toDouble() * second.toDouble()
        } else if(func == "/") {
            num = first.toDouble() / second.toDouble()
        } else {
            return first
        }
        // Gets rid of the trailing 0 if the answer is a whole number
        if(num.toString().get(num.toString().indexOf(".") + 1) == '0') {
            return num.toInt().toString()
        } else {
            return num.toString()
        }

    }
    // Determines weather it is the first or second number being inputted
    private fun firstOrSecond(num: String) {
        if (firstNum) {
            result += num
        } else {
            numberTwo += num
        }
    }
    private fun setOperator(operator: String) {
        mathFunc = operator
        firstNum = false
    }
}