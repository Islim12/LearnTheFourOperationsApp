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
import kotlinx.android.synthetic.main.fragment_addition_game.*
import kotlin.random.Random


class AdditionGameFragment : Fragment() {
    //private var viewModel: AdditionGameFragmentViewModel by viewModels()
    private lateinit var design: FragmentAdditionGameBinding
    private var randomNum1 = 0
    private var randomNum2 = 0
    private var randomNum3 = 0
    private var randomNumsSum = 0
    private var randomOptionNum1 = 0
    private var randomOptionNum2 = 0
    private var wrongAnswer = 0
    private var remainedQuestion = 15
    private var clickedWrongAnswer = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_addition_game, container, false)
        //TODO design.additionGameFragment = this
        //viewModel = AdditionGameFragmentViewModel(requireContext())

        decision()

        design.buttonSumOption1.setOnClickListener {
           onOption1Clicked()
        }

        design.buttonSumOption2.setOnClickListener {
            onOption2Clicked()
        }

        design.buttonSumOption3.setOnClickListener {
            onOption3Clicked()
        }
        return design.root
    }

    private fun decision()
    {
        design.additionRemainedQuestion.text = "15 / $remainedQuestion"
        if (remainedQuestion in 1..5)
            hardNewGame()
        else if (remainedQuestion == 0) {
           endGame()
        }
        else displayNewGame()
    }

    private fun endGame()
    {
        //TODO endGameSound()
        val correctAnswer = 15 - wrongAnswer
        design.learnAdditionActivityMessage.text = "Correct: $correctAnswer✔️\n Wrong: $wrongAnswer ❌"
        textAnimation()
        disableButton(design.buttonSumOption1)
        disableButton(design.buttonSumOption2)
        disableButton(design.buttonSumOption3)
    }

     private fun disableButton(button: Button) {
         button.isClickable = false
     }

    private fun textAnimation() {
        val anim = ObjectAnimator.ofFloat(learnAdditionActivityMessage, "translationX", -500.0f, 0.0f)
                .apply {
                    duration = 2000
                }
        anim.start()
    }

    private fun waitForNewQuestion() {
        object: CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                design.learnAdditionActivityMessage.text = "Correct Answer! \uD83C\uDF89"
            }

            override fun onFinish() {
                design.learnAdditionActivityMessage.text = ""
                decision()
            }
        }.start()
    }

    private fun onCorrectOptionClicked() = waitForNewQuestion()

    private fun onOption1Clicked() = onOptionClicked(design.buttonSumOption1)

    private fun onOption2Clicked() = onOptionClicked(design.buttonSumOption2)

    private fun onOption3Clicked() = onOptionClicked(design.buttonSumOption3)

    private fun onOptionClicked(button: Button)
    {
        if (button.text.equals(randomNumsSum.toString())) {
            onCorrectOptionClicked()
            remainedQuestion--
            clickedWrongAnswer = 0
        }
        else {
            clickedWrongAnswer++
            onWrongOptionClicked()
            if(clickedWrongAnswer == 1)
                wrongAnswer += 1
            //score -= if (remainedQuestion in 1..5) 20 else 10
        }
    }

    private fun onWrongOptionClicked()
    {
        design.learnAdditionActivityMessage.text = "Wrong answer, try again! ☺️"
    }

    private fun setOperationNumbers()
    {
        design.addend1.text = randomNum1.toString()
        design.addend2.text = randomNum2.toString()
        design.addend3.text = randomNum3.toString()
    }

    private fun displayNewGame()
    {
        setRandomNumbers()
        setOperationNumbers()
        setOperationNumbersSum()
        setOptionNumbers()
        setCorrectOption()
    }

    private fun hardNewGame()
    {
        design.addend3.isVisible = true
        design.plusSign2.isVisible = true
        displayNewGame()
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

    private fun setCorrectOption1() = setCorrectOption(design.buttonSumOption1, design.buttonSumOption2, design.buttonSumOption3)

    private fun setCorrectOption2() = setCorrectOption(design.buttonSumOption2, design.buttonSumOption1, design.buttonSumOption3)

    private fun setCorrectOption3() =  setCorrectOption(design.buttonSumOption3, design.buttonSumOption1, design.buttonSumOption2)

    private fun setCorrectOption(button1: Button, button2: Button, button3: Button)
    {
        button1.text = randomNumsSum.toString()
        button2.text = randomOptionNum1.toString()
        button3.text = randomOptionNum2.toString()
    }

    private fun setOptionNumbers() {
        randomOptionNum1 = Random.nextInt(0, 21)
        randomOptionNum2 = Random.nextInt(0, 21)

        //All options must be different from each other
        while (randomOptionNum1 == randomNumsSum)
            randomOptionNum1 = Random.nextInt(0, 21)
        while (randomOptionNum2 == randomNumsSum || randomOptionNum2 == randomOptionNum1)
            randomOptionNum2 = Random.nextInt(0, 21)
    }

    private fun setRandomNumbers() {
        randomNum1 = Random.nextInt(0, 11)
        randomNum2 = Random.nextInt(0, 11)
        randomNum3 = Random.nextInt(0, 11)
    }

    private fun setOperationNumbersSum() {
        if (remainedQuestion <= 5)
            randomNumsSum =  randomNum1 + randomNum2 + randomNum3
        else
            randomNumsSum =  randomNum1 + randomNum2
    }


}