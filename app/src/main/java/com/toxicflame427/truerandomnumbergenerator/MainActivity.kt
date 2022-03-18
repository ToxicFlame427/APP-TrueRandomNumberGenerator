package com.toxicflame427.truerandomnumbergenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.toxicflame427.truerandomnumbergenerator.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val random = Random(123456789)
    private var generatedNumber : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        applyEvents()
    }

    private fun applyEvents(){
        binding.allNumbersCheck.setOnClickListener {
            binding.allNumbersCheck.isChecked = true
            binding.onlyEvensCheck.isChecked = false
            binding.onlyOddsCheck.isChecked = false
        }

        binding.onlyEvensCheck.setOnClickListener {
            binding.allNumbersCheck.isChecked = false
            binding.onlyEvensCheck.isChecked = true
            binding.onlyOddsCheck.isChecked = false
        }

        binding.onlyOddsCheck.setOnClickListener {
            binding.allNumbersCheck.isChecked = false
            binding.onlyEvensCheck.isChecked = false
            binding.onlyOddsCheck.isChecked = true
        }

        binding.generateButton.setOnClickListener {
            if(binding.minValue.text.toString() == "" || binding.maxValue.text.toString() == ""){
                Toast.makeText(this, "Please enter a min and a max value", Toast.LENGTH_LONG).show()
            } else if(binding.minValue.text.toString().toInt() > binding.maxValue.text.toString().toInt()){
                Toast.makeText(this, "Min value cannot be larger than max value", Toast.LENGTH_LONG).show()
            } else {
                generatedNumber = generateRandomNumber(binding.minValue.text.toString().toInt(), binding.maxValue.text.toString().toInt() + 1)
                when {
                    binding.allNumbersCheck.isChecked -> {
                        binding.generatedNumber.text = generatedNumber.toString()
                    }
                    binding.onlyEvensCheck.isChecked -> {
                        checkNumVariant("even")
                    }
                    else -> {
                        checkNumVariant("odd")
                    }
                }
            }
        }
    }

    private fun checkNumVariant(variant : String){
        when (variant) {
            "even" -> {
                //Check if the number generated is even if there is no remainder
                if(generatedNumber % 2 == 0){
                    binding.generatedNumber.text = generatedNumber.toString()
                } else {
                    generatedNumber = generateRandomNumber(binding.minValue.text.toString().toInt(), binding.maxValue.text.toString().toInt())
                    checkNumVariant("even")
                }
            }
            "odd" -> {
                //Check if the number generated was odd if the remainder is 1
                if(generatedNumber % 2 == 1){
                    binding.generatedNumber.text = generatedNumber.toString()
                } else {
                    generatedNumber = generateRandomNumber(binding.minValue.text.toString().toInt(), binding.maxValue.text.toString().toInt())
                    checkNumVariant("odd")
                }
            }
        }
    }

    private fun generateRandomNumber(start : Int, stop : Int) : Int{
        return random.nextInt(start, stop)
    }
}