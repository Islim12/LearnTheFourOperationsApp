package com.islimakkaya.learnthefouroperations.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.islimakkaya.learnthefouroperations.R
import com.islimakkaya.learnthefouroperations.databinding.FragmentDivisionGameBinding
import com.islimakkaya.learnthefouroperations.util.NumberUtil
import kotlinx.android.synthetic.main.fragment_division_game.*
import kotlin.random.Random

class DivisionGameFragment : Fragment() {
    private lateinit var design: FragmentDivisionGameBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var numsQuotient = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 1
    private var score = 200
    private var remainedQuestion = 15

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_division_game, container, false)
       //TODO design.divisionGameFragment = this

        decision()

        design.quotientOption1.setOnClickListener {
            onOption1Clicked()
        }

        design.quotientOption2.setOnClickListener {
            onOption2Clicked()
        }

        design.quotientOption3.setOnClickListener {
            onOption3Clicked()
        }

        return design.root
    }

    private fun waitForNewQuestion() {
        object: CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                design.learnDivisionActivityMessage.text = "Correct Answer! \uD83C\uDF89"
            }

            override fun onFinish() {
                design.learnDivisionActivityMessage.text = ""
                decision()
            }
        }.start()
    }

    private fun decision()
    {
        if (remainedQuestion in 1..5)
            hardNewGame()
        else if (remainedQuestion == 0) {
            design.learnDivisionActivityMessage.text = "Your Score: 200 / $score"
            /* TODO endGame() */
        }
        else displayNewGame()
    }

    private fun onOption1Clicked() = onOptionClicked(design.quotientOption1)

    private fun onOption2Clicked() = onOptionClicked(design.quotientOption2)

    private fun onOption3Clicked() = onOptionClicked(design.quotientOption3)

    private fun onOptionClicked(button: Button)
    {
        if (button.text.equals(numsQuotient.toString())) {
            onCorrectOptionClicked()
            remainedQuestion--
        }
        else
            onWrongOptionClicked()
    }

    private fun onCorrectOptionClicked() = waitForNewQuestion()

    private fun onWrongOptionClicked()
    {
        score -= if (remainedQuestion in 1..5) 20 else 10
    }

    private fun setOperationNumbers() {
        design.dividend.text = randomNum1.toString()
        design.divisor.text = randomNum2.toString()
    }

    private fun hardNewGame()
    {
        randomNum1 = Random.nextInt(20, 31)
        randomNum2 = Random.nextInt(1, 11)
        while (!NumberUtil.isFactor(randomNum1, randomNum2))
            randomNum2 = Random.nextInt(1, 11)
        setOperationNumbers()
        setRandomNumbersQuotient()
        setOptionNumbers()
        setCorrectOption()
    }

    private fun displayNewGame()
    {
        setRandomNumbers()
        setOperationNumbers()
        setRandomNumbersQuotient()
        setOptionNumbers()
        setCorrectOption()
    }

    private fun setCorrectOption()
    {
        when (getCorrectOptionNum()) {
            1 -> setCorrectOption1()
            2 -> setCorrectOption2()
            else -> setCorrectOption3()
        }
    }

    private fun getCorrectOptionNum() = Random.nextInt(1,4)

    private fun setCorrectOption1() = setCorrectOption(design.quotientOption1, design.quotientOption2, design.quotientOption3)

    private fun setCorrectOption2() = setCorrectOption(design.quotientOption2, design.quotientOption1, design.quotientOption3)

    private fun setCorrectOption3() =  setCorrectOption(design.quotientOption3, design.quotientOption1, design.quotientOption2)

    private fun setCorrectOption(button1: Button, button2: Button, button3: Button)
    {
        button1.text = numsQuotient.toString()
        button2.text = randomOptionNum1.toString()
        button3.text = randomOptionNum2.toString()
    }

    private fun setOptionNumbers() {
        randomOptionNum1 = Random.nextInt(0, 21)
        randomOptionNum2 = Random.nextInt(0, 21)

        //All options must be different from each other
        while (randomOptionNum1 == numsQuotient)
            randomOptionNum1 = Random.nextInt(0, 21)
        while (randomOptionNum2 == numsQuotient || randomOptionNum2 == randomOptionNum1)
            randomOptionNum2 = Random.nextInt(0, 21)
    }

    private fun setRandomNumbers() {
        randomNum1 = Random.nextInt(0, 21)
        randomNum2 = Random.nextInt(1, 11)
        while (!NumberUtil.isFactor(randomNum1, randomNum2))
            randomNum2 = Random.nextInt(1, 11)
    }

    private fun setRandomNumbersQuotient() {
        numsQuotient =  randomNum1 / randomNum2
    }
}