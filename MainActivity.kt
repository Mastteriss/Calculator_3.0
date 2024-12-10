package com.example.calculator_30

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextInput: EditText
    private lateinit var editTextOutput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextInput = findViewById(R.id.editTextInput)
        editTextOutput = findViewById(R.id.editTextOutput)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        val buttonIds = listOf(
            R.id.button1, R.id.button2, R.id.button3, R.id.buttonPlus,
            R.id.button4, R.id.button5, R.id.button6, R.id.buttonMinus,
            R.id.button7, R.id.button8, R.id.button9, R.id.buttonMultiply,
            R.id.button0, R.id.buttonClear, R.id.buttonEquals, R.id.buttonDivide
        )

        for (buttonId in buttonIds) {
            val button: Button = findViewById(buttonId)
            button.setOnClickListener { onButtonClick(button.text.toString()) }
        }
    }

    private fun onButtonClick(value: String) {
        when (value) {
            "=" -> calculateResult()
            "C" -> clearInput()
            else -> editTextInput.append(value)
        }
    }

    private fun calculateResult() {
        val input = editTextInput.text.toString()
        val result = try {
            val parts = input.split(" ")


            if (parts.size != 3) {
                throw IllegalArgumentException("Неверный ввод")
            }

            val operand1 = parts[0].toDouble()
            val operand2 = parts[2].toDouble()

            when (parts[1]) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                "/" -> {
                    if (operand2 != 0.0) {
                        operand1 / operand2
                    } else {
                        throw ArithmeticException("Деление на ноль")
                    }
                }
                else -> throw IllegalArgumentException("Неверный оператор")
            }
        } catch (e: Exception) {

            "Ошибка"
        }


        editTextOutput.setText(result.toString())
    }

    private fun clearInput() {
        editTextInput.text.clear()
        editTextOutput.text.clear()
    }
}