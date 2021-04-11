package org.islimakkaya.samples.application.learnthefouroperations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAdditionActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, LearnAdditionActivity::class.java)
            startActivity(intent)
        }

        buttonSubtractionActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, LearnSubtractionActivity::class.java)
            startActivity(intent)
        }

        buttonMultiplicationActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, LearnMultiplicationActivity::class.java)
            startActivity(intent)
        }

        buttonDivisionActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, LearnDivisionActivity::class.java)
            startActivity(intent)
        }

        buttonChallengeActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, DoChallengeActivity::class.java)
            startActivity(intent)
        }
    }
}