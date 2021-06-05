package com.islimakkaya.learnthefouroperations.fragment

import android.animation.ObjectAnimator
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
import com.islimakkaya.learnthefouroperations.databinding.FragmentAdditionGameBinding
import com.islimakkaya.learnthefouroperations.databinding.FragmentMultiplicationGameBinding
import com.islimakkaya.learnthefouroperations.entity.Scores
import kotlinx.android.synthetic.main.fragment_about_us_page.*
import kotlinx.android.synthetic.main.fragment_addition_game.*
import kotlinx.android.synthetic.main.fragment_multiplication_game.*
import kotlin.random.Random

class MultiplicationGameFragment : Fragment() {
    private lateinit var design: FragmentMultiplicationGameBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var randomNum3 = 0
    private var randomNumsMult = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 0
    private var wrongAnswer = 0
    private var remainedQuestion = 15
    private var clickedWrongAnswer = 0

      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          design = DataBindingUtil.inflate(inflater, R.layout.fragment_multiplication_game, container, false)
         //TODO Fragment data binding for functions
          design.multiplicationGameFragment = this

          decision()

          design.productOption1.setOnClickListener {
              onOption1Clicked()
          }

          design.productOption2.setOnClickListener {
              onOption2Clicked()
          }

          design.productOption3.setOnClickListener {
              onOption3Clicked()
          }
          return design.root
    }

    private fun decision()
    {
        design.multiplicationRemainedQuestion.text = "15 / $remainedQuestion"
        when (remainedQuestion) {
            in 1..5 -> hardNewGame()
            0 -> {
                endGame()
            }
            else -> displayNewGame()
        }
    }

    private fun endGame()
    {
        //TODO endGameSound()
        val correctAnswer = 15 - wrongAnswer
        design.learnMultiplicationActivityMessage.text = "Correct: $correctAnswer✔️\n Wrong: $wrongAnswer ❌"
        textAnimation()
        disableButton(design.productOption1)
        disableButton(design.productOption2)
        disableButton(design.productOption3)
    }

    private fun disableButton(button: Button) {
        button.isClickable = false
    }

    private fun textAnimation() {
        val anim = ObjectAnimator.ofFloat(learnMultiplicationActivityMessage, "translationX", -500.0f, 0.0f)
                .apply {
                    duration = 2000
                }
        anim.start()
    }

    private fun waitForNewQuestion() {
        object: CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                design.learnMultiplicationActivityMessage.text = "Correct Answer! \uD83C\uDF89"
            }

            override fun onFinish() {
                design.learnMultiplicationActivityMessage.text = ""
                decision()
            }
        }.start()
    }

    private fun onCorrectOptionClicked() = waitForNewQuestion()

    private fun onOption1Clicked() = onOptionClicked(design.productOption1)

    private fun onOption2Clicked() = onOptionClicked(design.productOption2)

    private fun onOption3Clicked() = onOptionClicked(design.productOption3)

    private fun onOptionClicked(button: Button)
    {
        if (button.text.equals(randomNumsMult.toString())) {
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

    private fun onWrongOptionClicked()
    {
        design.learnMultiplicationActivityMessage.text = "Wrong answer, try again! ☺️"
    }


    private fun setOperationNumbers() {
        design.multiplier.text = randomNum1.toString()
        design.multiplicand.text = randomNum2.toString()
        design.multiplicand2.text = randomNum3.toString()
    }

    private fun hardNewGame()
    {
        design.multiplicand2.isVisible = true
        design.multiplicationSign2.isVisible = true
        displayNewGame()
    }

    private fun displayNewGame()
    {
        setRandomNumbers()
        setOperationNumbers()
        setOperationNumbersDifference()
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

    private fun setCorrectOption1() = setCorrectOption(design.productOption1, design.productOption2, design.productOption3)

    private fun setCorrectOption2() = setCorrectOption(design.productOption2, design.productOption1, design.productOption3)

    private fun setCorrectOption3() =  setCorrectOption(design.productOption3, design.productOption1, design.productOption2)

    private fun setCorrectOption(button1: Button, button2: Button, button3: Button)
    {
        button1.text = randomNumsMult.toString()
        button2.text = randomOptionNum1.toString()
        button3.text = randomOptionNum2.toString()
    }

    private fun setOptionNumbers() {
        randomOptionNum1 = Random.nextInt(0, 21)
        randomOptionNum2 = Random.nextInt(0, 31)

        //All options must be different from each other
        while (randomOptionNum1 == randomNumsMult)
            randomOptionNum1 = Random.nextInt(0, 21)
        while (randomOptionNum2 == randomNumsMult || randomOptionNum2 == randomOptionNum1)
            randomOptionNum2 = Random.nextInt(0, 31)
    }

    private fun setRandomNumbers() {
        randomNum1 = Random.nextInt(1, 6)
        randomNum2 = Random.nextInt(1, 6)
        randomNum3 = Random.nextInt(0, 6)
    }

    private fun setOperationNumbersDifference() {
        if (remainedQuestion <= 5)
            randomNumsMult =  randomNum1 * randomNum2 * randomNum3
        else
            randomNumsMult =  randomNum1 * randomNum2
    }

}