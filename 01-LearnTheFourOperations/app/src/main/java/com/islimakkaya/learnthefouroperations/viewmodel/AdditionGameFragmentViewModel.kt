package com.islimakkaya.learnthefouroperations.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.islimakkaya.learnthefouroperations.databinding.FragmentAdditionGameBinding
import kotlin.random.Random

class AdditionGameFragmentViewModel(var mContext: Context): ViewModel() {
    private lateinit var binding: FragmentAdditionGameBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var randomNumsSum = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 0

    private fun onCorrectOptionClicked()
    {
        Toast.makeText(mContext, "Congrats!", Toast.LENGTH_SHORT).show()
    }

    fun onOption1Clicked()
    {

        if (binding.buttonSumOption1.text.equals(randomNumsSum.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    fun onOption2Clicked()
    {
        if (binding.buttonSumOption2.text.equals(randomNumsSum.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    fun onOption3Clicked()
    {
        if (binding.buttonSumOption3.text.equals(randomNumsSum.toString())) {
            onCorrectOptionClicked()
            newGame()
        }
        else
            onWrongOptionClicked()
    }

    private fun onWrongOptionClicked()
    {
        Toast.makeText(mContext, "Wrong Option.", Toast.LENGTH_SHORT).show()
    }

    private fun displayNumbers() {
        binding.addend1.text = randomNum1.toString()
        binding.addend2.text = randomNum2.toString()
    }

    fun newGame()
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
        binding.buttonSumOption1.text = randomNumsSum.toString()
        binding.buttonSumOption2.text = randomOptionNum1.toString()
        binding.buttonSumOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption2()
    {
        binding.buttonSumOption2.text = randomNumsSum.toString()
        binding.buttonSumOption1.text = randomOptionNum1.toString()
        binding.buttonSumOption3.text = randomOptionNum2.toString()
    }

    private fun setCorrectOption3()
    {
        binding.buttonSumOption3.text = randomNumsSum.toString()
        binding.buttonSumOption1.text = randomOptionNum1.toString()
        binding.buttonSumOption2.text = randomOptionNum2.toString()
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