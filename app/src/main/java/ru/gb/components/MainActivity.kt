package ru.gb.components

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.gb.components.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phone = binding.phone
        val name = binding.name
        val genderRadioGroup = binding.genderRadioGroup
        val messageSwitch = binding.messageSwitch
        val authorizationCheck = binding.authorizationCheck
        val newsCheck = binding.newsCheck
        val scoreCountTextView = binding.scoreCountTextView
        val scoreProgressIndicator = binding.scoreProgressIndicator
        val saveButton = binding.saveButton

        messageSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                authorizationCheck.isChecked = false
                authorizationCheck.isEnabled = false
                newsCheck.isChecked = false
                newsCheck.isEnabled = false
            } else {
                authorizationCheck.isEnabled = true
                newsCheck.isEnabled = true
            }
        }

        val randomScore = Random.nextInt(101)
        val randomScoreText = "$randomScore/100"
        scoreCountTextView.text = randomScoreText
        scoreProgressIndicator.progress = randomScore

        saveButton.setOnClickListener {
            if (!phone.text.isNullOrEmpty()
                && phone.text?.let { it1 -> Patterns.PHONE.matcher(it1).matches() } == true
                && !name.text.isNullOrEmpty()
                && name.text!!.length < 41
                && genderRadioGroup.checkedRadioButtonId != -1
                && (!messageSwitch.isChecked ||
                        (messageSwitch.isChecked
                        && (authorizationCheck.isChecked
                                || newsCheck.isChecked)))) {
                Snackbar.make(it, R.string.saveMessage, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}