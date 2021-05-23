package org.islimakkaya.samples.application.learnthefouroperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_learn_multiplication.*
import org.islimakkaya.samples.application.learnthefouroperations.databinding.ActivityLearnMultiplicationBinding
import kotlin.random.Random

class LearnMultiplicationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearnMultiplicationBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var randomNumsMult = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnMultiplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newGame()

        binding.productOption1.setOnClickListener {
            onOption1Clicked()
        }

        binding.productOption2.setOnClickListener {
            onOption2Clicked()
        }

        binding.productOption3.setOnClickListener {
            onOption3Clicked()
        }
    }

    private fun onCorrectOptionClicked()
    {
        Toast.makeText(this, "Congrats!", Toast.LENGTH_SHORT).show()
    }

    private fun onOption1Clicked()
    {
        if (productOption1.text.equals(randomNumsMult.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onOption2Clicked()
    {
        if (productOption2.text.equals(randomNumsMult.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onOption3Clicked()
    {
        if (productOption3.text.equals(randomNumsMult.toString())) {
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
        binding.multiplier.text = randomNum1.toString()
        binding.multiplicand.text = randomNum2.toString()
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
        binding.productOption1.text = randomNumsMult.toString()
        binding.productOption2.text = randomOptionNum1.toString()
        binding.productOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption2()
    {
        binding.productOption2.text = randomNumsMult.toString()
        binding.productOption1.text = randomOptionNum1.toString()
        binding.productOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption3()
    {
        binding.productOption3.text = randomNumsMult.toString()
        binding.productOption1.text = randomOptionNum1.toString()
        binding.productOption2.text = randomOptionNum2.toString()
    }

    private fun setOptionRandomNums() {
        randomOptionNum1 = Random.nextInt(0, 21)
        randomOptionNum2 = Random.nextInt(0, 21)

        //All options must be different from each other
        while (randomOptionNum1 == randomNumsMult)
            randomOptionNum1 = Random.nextInt(0, 21)
        while (randomOptionNum2 == randomNumsMult || randomOptionNum2 == randomOptionNum1)
            randomOptionNum2 = Random.nextInt(0, 21)
    }

    private fun setRandomNums() {
        randomNum1 = Random.nextInt(1, 11)
        randomNum2 = Random.nextInt(0, 11)
    }

    private fun setRandomNumsDiff() {
        randomNumsMult =  randomNum1 * randomNum2
    }

}