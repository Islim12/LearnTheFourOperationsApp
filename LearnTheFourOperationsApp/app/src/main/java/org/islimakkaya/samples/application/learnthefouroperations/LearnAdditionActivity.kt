package org.islimakkaya.samples.application.learnthefouroperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_learn_addition.*
import org.islimakkaya.samples.application.learnthefouroperations.databinding.ActivityLearnAdditionBinding
import kotlin.random.Random

class LearnAdditionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearnAdditionBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var randomNumsSum = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnAdditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newGame()

        binding.buttonOption1.setOnClickListener {
            onOption1Clicked()
        }

        binding.buttonOption2.setOnClickListener {
            onOption2Clicked()
        }

        binding.buttonOption3.setOnClickListener {
            onOption3Clicked()
        }
    }

    private fun onCorrectOptionClicked()
    {
        Toast.makeText(this, "Congrats!", Toast.LENGTH_SHORT).show()
    }

    private fun onOption1Clicked()
    {
        if (buttonOption1.text.equals(randomNumsSum.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onOption2Clicked()
    {
        if (buttonOption2.text.equals(randomNumsSum.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onOption3Clicked()
    {
        if (buttonOption3.text.equals(randomNumsSum.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onWrongOptionClicked()
    {
        Toast.makeText(this, "Wrong Option.", Toast.LENGTH_SHORT).show()
    }

    private fun displayNumbers() {
        binding.textViewNumber1.text = randomNum1.toString()
        binding.textViewNumber2.text = randomNum2.toString()
    }

    private fun newGame()
    {
        setRandomNums()
        displayNumbers()
        setRandomNumsSum()
        setOptionRandomNums()

        playGame()
    }

    private fun playGame()
    {
        when (returnCorrectOptionNum()) {
            1 -> setCorrectOption1()
            2 -> setCorrectOption2()
            else -> setCorrectOption3()
        }
    }

    private fun returnCorrectOptionNum(): Int
    {
        return Random.nextInt(1,4)
    }

    private fun setCorrectOption1()
    {
        binding.buttonOption1.text = randomNumsSum.toString()
        binding.buttonOption2.text = randomOptionNum1.toString()
        binding.buttonOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption2()
    {
        binding.buttonOption2.text = randomNumsSum.toString()
        binding.buttonOption1.text = randomOptionNum1.toString()
        binding.buttonOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption3()
    {
        binding.buttonOption3.text = randomNumsSum.toString()
        binding.buttonOption1.text = randomOptionNum1.toString()
        binding.buttonOption2.text = randomOptionNum2.toString()
    }

    private fun setOptionRandomNums() {
        randomOptionNum1 = Random.nextInt(0, 21)
        randomOptionNum2 = Random.nextInt(0, 21)

        //All options must be different from each other
        while (randomOptionNum1 == randomNumsSum)
            randomOptionNum1 = Random.nextInt(0, 21)
        while (randomOptionNum2 == randomNumsSum || randomOptionNum2 == randomOptionNum1)
            randomOptionNum2 = Random.nextInt(0, 21)
    }

    private fun setRandomNums() {
        randomNum1 = Random.nextInt(0, 11)
        randomNum2 = Random.nextInt(0, 11)
    }

    private fun setRandomNumsSum() {
        randomNumsSum =  randomNum1 + randomNum2
    }

}