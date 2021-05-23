package org.islimakkaya.samples.application.learnthefouroperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_learn_subtraction.*
import org.islimakkaya.samples.application.learnthefouroperations.databinding.ActivityLearnSubtractionBinding
import kotlin.random.Random

class LearnSubtractionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearnSubtractionBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var randomNumsDiff = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnSubtractionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newGame()

        binding.differenceOption1.setOnClickListener {
            onOption1Clicked()
        }

        binding.differenceOption2.setOnClickListener {
            onOption2Clicked()
        }

        binding.differenceOption3.setOnClickListener {
            onOption3Clicked()
        }
    }

    private fun onCorrectOptionClicked()
    {
        Toast.makeText(this, "Congrats!", Toast.LENGTH_SHORT).show()
    }

    private fun onOption1Clicked()
    {
        if (differenceOption1.text.equals(randomNumsDiff.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onOption2Clicked()
    {
        if (differenceOption2.text.equals(randomNumsDiff.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onOption3Clicked()
    {
        if (differenceOption3.text.equals(randomNumsDiff.toString())) {
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
        binding.minuend.text = randomNum1.toString()
        binding.subtrahend.text = randomNum2.toString()
    }

    private fun newGame()
    {
        setRandomNums()
        displayNumbers()
        setRandomNumsDiff()
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
        binding.differenceOption1.text = randomNumsDiff.toString()
        binding.differenceOption2.text = randomOptionNum1.toString()
        binding.differenceOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption2()
    {
        binding.differenceOption2.text = randomNumsDiff.toString()
        binding.differenceOption1.text = randomOptionNum1.toString()
        binding.differenceOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption3()
    {
        binding.differenceOption3.text = randomNumsDiff.toString()
        binding.differenceOption1.text = randomOptionNum1.toString()
        binding.differenceOption2.text = randomOptionNum2.toString()
    }

    private fun setOptionRandomNums() {
        randomOptionNum1 = Random.nextInt(0, 21)
        randomOptionNum2 = Random.nextInt(0, 21)

        //All options must be different from each other
        while (randomOptionNum1 == randomNumsDiff)
            randomOptionNum1 = Random.nextInt(0, 21)
        while (randomOptionNum2 == randomNumsDiff || randomOptionNum2 == randomOptionNum1)
            randomOptionNum2 = Random.nextInt(0, 21)
    }

    private fun setRandomNums() {
        randomNum1 = Random.nextInt(0, 21)
        randomNum2 = Random.nextInt(0, 21)
        //First number must be bigger than the second number
        while (randomNum1 < randomNum2)
            randomNum1 = Random.nextInt(0, 21)
    }

    private fun setRandomNumsDiff() {
        randomNumsDiff =  randomNum1 - randomNum2
    }

}