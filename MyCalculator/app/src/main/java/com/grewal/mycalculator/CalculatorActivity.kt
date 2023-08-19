package com.grewal.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity: AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = StringBuilder()
    private var currentOperator: Char? = null
    private var intermediateResult: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        display = findViewById(R.id.display)

        val buttons = listOf("seven", "eight", "nine", "add", "four", "five", "six", "sub",
            "one", "two", "three", "mul", "zero", "dec", "div", "equal", "clear")

        for (buttonText in buttons) {
            val button: Button = findViewById(resources.getIdentifier(buttonText, "id", packageName))

            button.setOnClickListener { onButtonClick(button.text.toString()) }
        }
    }

    private fun onButtonClick(buttonText: String) {
        when (buttonText) {
            "+", "-", "/", "*" -> handleOperator(buttonText[0])
            "=" -> calculateResult()
            "." -> handleDecimal()
            "clear"->clearCalculator()
            else -> appendDigit(buttonText)
        }
    }

    private fun appendDigit(digit: String) {
        currentInput.append(digit)
        display.text = currentInput.toString()
    }
    private fun handleOperator(operator: Char) {
        if (currentOperator != null && intermediateResult != null) {

            val secondOperand = currentInput.toString().toDoubleOrNull() ?: intermediateResult!!


            intermediateResult = calculate(intermediateResult!!, currentOperator!!, secondOperand)


            currentOperator = operator
        } else {

            intermediateResult = currentInput.toString().toDoubleOrNull() ?: 0.0
            currentOperator = operator
        }


        currentInput.clear()
    }


    private fun calculateResult() {
        if (currentOperator != null && intermediateResult != null) {
            val secondOperand = currentInput.toString().toDoubleOrNull() ?: 0.0


            val result = calculate(intermediateResult!!, currentOperator!!, secondOperand)

            display.text = result.toString()
            currentInput.clear();
        }
    }

    private fun calculate(firstOperand: Double, operator: Char, secondOperand: Double): Double {
        return when (operator) {
            '+' -> firstOperand + secondOperand
            '-' -> firstOperand - secondOperand
            '*' -> firstOperand * secondOperand
            '/' -> {
                if (secondOperand != 0.0) {
                    firstOperand / secondOperand
                } else {
                    throw IllegalArgumentException("Division by zero")
                }
            }
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }

    private fun handleDecimal() {
        if (!currentInput.contains(".")) {
            if (currentInput.isEmpty()) {
                currentInput.append("0")
            }
            currentInput.append(".")
            display.text = currentInput.toString()
        }
    }

    private fun clearCalculator() {
        currentInput.clear()
        currentOperator = null
        intermediateResult = null
        display.text = ""
    }
}