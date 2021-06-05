package com.islimakkaya.learnthefouroperations.fragment

import android.animation.ObjectAnimator
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
import kotlinx.android.synthetic.main.fragment_addition_game.*
import kotlinx.android.synthetic.main.fragment_division_game.*
import kotlin.random.Random

class DivisionGameFragment : Fragment() {
    private lateinit var design: FragmentDivisionGameBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var numsQuotient = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 1
    private var wrongAnswer = 0
    private var remainedQuestion = 15
    private var clickedWrongAnswer = 0

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

    private fun endGame()
    {
        //TODO endGameSound()
        val correctAnswer = 15 - wrongAnswer
        design.learnDivisionActivityMessage.text = "Correct: $correctAnswer✔️\n Wrong: $wrongAnswer ❌"
        textAnimation()
        disableButton(design.quotientOption1)
        disableButton(design.quotientOption2)
        disableButton(design.quotientOption3)
    }

    private fun disableButton(button: Button) {
        button.isClickable = false
    }

    private fun textAnimation() {
        val anim = ObjectAnimator.ofFloat(learnDivisionActivityMessage, "translationX", -500.0f, 0.0f)
                .apply {
                    duration = 2000
                }
        anim.start()
    }

    private fun decision()
    {
        design.divisionRemainedQuestion.text = "15 / $remainedQuestion"
        when (remainedQuestion) {
            in 1..5 -> hardNewGame()
            0 -> {
                endGame()
            }
            else -> displayNewGame()
        }
    }

    private fun onOption1Clicked() = onOptionClicked(design.quotientOption1)

    private fun onOption2Clicked() = onOptionClicked(design.quotientOption2)

    private fun onOption3Clicked() = onOptionClicked(design.quotientOption3)

    private fun onOptionClicked(button: Button)
    {
        if (button.text.equals(numsQuotient.toString())) {
            onCorrectOptionClicked()
            remainedQuestion--
            clickedWrongAnswer = 0
        }
        else {
            clickedWrongAnswer++
            onWrongOptionClicked()
            if(clickedWrongAnswer == 1)
                wrongAnswer += 1
        }
    }

    private fun onCorrectOptionClicked() = waitForNewQuestion()

    private fun onWrongOptionClicked()
    {
        design.learnDivisionActivityMessage.text = "Wrong answer, try again! ☺️"
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