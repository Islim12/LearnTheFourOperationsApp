package com.islimakkaya.learnthefouroperations.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.islimakkaya.learnthefouroperations.R
import com.islimakkaya.learnthefouroperations.databinding.FragmentSubtractionGameBinding
import kotlinx.android.synthetic.main.fragment_subtraction_game.*
import kotlin.random.Random

class SubtractionGameFragment : Fragment() {
    private lateinit var design: FragmentSubtractionGameBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var randomNum3 = 0
    private var randomNumsDiff = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 0
    private var score = 200
    private var remainedQuestion = 15

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_subtraction_game, container, false)
        //TODO design.additionGameFragment = this

        displayNewGame()

        design.differenceOption1.setOnClickListener {
            onOption1Clicked()
        }

        design.differenceOption2.setOnClickListener {
            onOption2Clicked()
        }

        design.differenceOption3.setOnClickListener {
            onOption3Clicked()
        }

        return design.root
    }

    private fun decision()
    {
        if (remainedQuestion in 1..5)
            hardNewGame()
        else if (remainedQuestion == 0) {
            design.learnSubtractionActivityMessage.text = "Your Score: 200 / $score"
            /* TODO endGame() */
        }
        else displayNewGame()
    }

    private fun waitForNewQuestion() {
        object: CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                design.learnSubtractionActivityMessage.text = "Correct Answer! \uD83C\uDF89"
            }

            override fun onFinish() {
                design.learnSubtractionActivityMessage.text = ""
                decision()
            }
        }.start()
    }

    private fun onCorrectOptionClicked() = waitForNewQuestion()

    private fun onWrongOptionClicked()
    {
        score -= if (remainedQuestion in 1..5) 20 else 10
    }

    private fun onOption1Clicked() = onOptionClicked(design.differenceOption1)

    private fun onOption2Clicked() = onOptionClicked(design.differenceOption2)

    private fun onOption3Clicked() = onOptionClicked(design.differenceOption3)

    private fun onOptionClicked(button: Button)
    {
        if (button.text.equals(randomNumsDiff.toString())) {
            onCorrectOptionClicked()
            remainedQuestion--
        }
        else
            onWrongOptionClicked()
    }

    private fun setOperationNumbers() {
        design.minuend.text = randomNum1.toString()
        design.subtrahend.text = randomNum2.toString()
        design.subtrahend2.text = randomNum3.toString()
    }

    private fun hardNewGame()
    {
        design.subtrahend2.isVisible = true
        design.minusSign2.isVisible = true
        displayNewGame()
    }

    private fun displayNewGame()
    {
        setRandomNumbers()
        setOperationNumbers()
        setOperationNumbersDiff()
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

    private fun setCorrectOption1() = setCorrectOption(design.differenceOption1, design.differenceOption2, design.differenceOption3)

    private fun setCorrectOption2() = setCorrectOption(design.differenceOption2, design.differenceOption1, design.differenceOption3)

    private fun setCorrectOption3() =  setCorrectOption(design.differenceOption3, design.differenceOption1, design.differenceOption2)

    private fun setCorrectOption(button1: Button, button2: Button, button3: Button)
    {
        button1.text = randomNumsDiff.toString()
        button2.text = randomOptionNum1.toString()
        button3.text = randomOptionNum2.toString()
    }

    private fun setOptionNumbers() {
        randomOptionNum1 = Random.nextInt(0, 21)
        randomOptionNum2 = Random.nextInt(0, 21)

        //All options must be different from each other
        while (randomOptionNum1 == randomNumsDiff)
            randomOptionNum1 = Random.nextInt(0, 21)
        while (randomOptionNum2 == randomNumsDiff || randomOptionNum2 == randomOptionNum1)
            randomOptionNum2 = Random.nextInt(0, 21)
    }

    private fun setRandomNumbers() {
        randomNum1 = Random.nextInt(0, 21)
        randomNum2 = Random.nextInt(0, 21)
        randomNum3 = Random.nextInt(1, 21)
        //First number must be bigger than the second number
        while (randomNum1 <= randomNum2)
            randomNum1 = Random.nextInt(0, 21)

        var diff = randomNum1 - randomNum2

        while (diff < randomNum3)
            randomNum3 = Random.nextInt(1, 21)
    }

    private fun setOperationNumbersDiff() {
        if (remainedQuestion <= 5)
            randomNumsDiff =  randomNum1 - randomNum2 - randomNum3
        else
            randomNumsDiff =  randomNum1 - randomNum2
    }

}