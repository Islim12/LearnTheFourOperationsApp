package org.islimakkaya.samples.application.learnthefouroperations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_learn_addition.*
import kotlinx.android.synthetic.main.activity_learn_addition.buttonSumOption3
import kotlinx.android.synthetic.main.activity_learn_division.*
import org.islimakkaya.samples.application.learnthefouroperations.databinding.ActivityLearnDivisionBinding
import org.islimakkaya.samples.application.learnthefouroperations.util.NumberUtil
import kotlin.random.Random

class LearnDivisionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearnDivisionBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var numsQuotient = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnDivisionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newGame()

        binding.quotientOption1.setOnClickListener {
            onOption1Clicked()
        }

        binding.quotientOption2.setOnClickListener {
            onOption2Clicked()
        }

        binding.quotientOption3.setOnClickListener {
            onOption3Clicked()
        }
    }

    private fun onCorrectOptionClicked()
    {
        Toast.makeText(this, "Congrats!", Toast.LENGTH_SHORT).show()
    }

    private fun onOption1Clicked()
    {
        if (quotientOption1.text.equals(numsQuotient.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onOption2Clicked()
    {
        if (quotientOption2.text.equals(numsQuotient.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onOption3Clicked()
    {
        if (quotientOption3.text.equals(numsQuotient.toString())) {
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
        binding.dividend.text = randomNum1.toString()
        binding.divisor.text = randomNum2.toString()
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
        binding.quotientOption1.text = numsQuotient.toString()
        binding.quotientOption2.text = randomOptionNum1.toString()
        binding.quotientOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption2()
    {
        binding.quotientOption2.text = numsQuotient.toString()
        binding.quotientOption1.text = randomOptionNum1.toString()
        binding.quotientOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption3()
    {
        binding.quotientOption3.text = numsQuotient.toString()
        binding.quotientOption1.text = randomOptionNum1.toString()
        binding.quotientOption2.text = randomOptionNum2.toString()
    }

    private fun setOptionRandomNums() {
        randomOptionNum1 = Random.nextInt(0, 21)
        randomOptionNum2 = Random.nextInt(0, 21)

        //All options must be different from each other
        while (randomOptionNum1 == numsQuotient)
            randomOptionNum1 = Random.nextInt(0, 21)
        while (randomOptionNum2 == numsQuotient || randomOptionNum2 == randomOptionNum1)
            randomOptionNum2 = Random.nextInt(0, 21)
    }

    private fun setRandomNums() {
        randomNum1 = Random.nextInt(0, 21)
        randomNum2 = Random.nextInt(1, 11)
        while (!NumberUtil.isFactor(randomNum1, randomNum2))
            randomNum2 = Random.nextInt(1, 11)
    }

    private fun setRandomNumsSum() {
        numsQuotient =  randomNum1 / randomNum2
    }

}