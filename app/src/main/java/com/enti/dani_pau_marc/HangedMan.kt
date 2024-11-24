package com.enti.dani_pau_marc

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout

class HangedMan : AppCompatActivity() {
    // Componentes UI
    private lateinit var hangedManImage: ImageView
    private lateinit var hangedManWord: TextView
    private lateinit var keyboardTop: LinearLayout
    private lateinit var keyboardMiddle: LinearLayout
    private lateinit var keyboardBottom: LinearLayout
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var themeSwitch: Switch

    // Cambio de tema
    private var nightMode : Boolean = false
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor


    // Lógica del juego
    private var wordToGuess: String = "TEST"
    private var currentWordState: CharArray = wordToGuess.map { if (it == ' ') ' ' else '_' }.toCharArray()
    private var incorrectGuesses: Int = 0

    private val MAX_INCORRECT_GUESSES: Int = 6

    // Imagenes
    private val images = listOf(
        R.drawable.hanged_man_00,
        R.drawable.hanged_man_01,
        R.drawable.hanged_man_02,
        R.drawable.hanged_man_03,
        R.drawable.hanged_man_04,
        R.drawable.hanged_man_05,
        R.drawable.hanged_man_06
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        InitUI()

        InitKeyboard(keyboardTop)
        InitKeyboard(keyboardMiddle)
        InitKeyboard(keyboardBottom)
        UpdateUI()
    }

    private fun SwitchTheme()
    {
        supportActionBar?.hide();
        themeSwitch = findViewById(R.id.themeSwitch);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);

        if (nightMode)
        {
            themeSwitch.isChecked = true;
        }

        themeSwitch.setOnClickListener {
            nightMode = !nightMode
            sharedPreferences.edit().putBoolean("night", nightMode).apply()

            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun InitUI()
    {
        hangedManImage = findViewById(R.id.hangedManImage)
        hangedManWord = findViewById(R.id.hangedManWord)
        keyboardTop = findViewById(R.id.keyboardTop)
        keyboardMiddle = findViewById(R.id.keyboardMiddle)
        keyboardBottom = findViewById(R.id.keyboardBottom)
        constraintLayout = findViewById(R.id.hangedManMain)
    }

    private fun InitKeyboard(layout: LinearLayout)
    {
        for (i in 0 until layout.childCount)
        {
            (layout.getChildAt(i) as? Button)?.let { button ->
                button.setOnClickListener { HandleGuess(button.text[0], button) }
            }
        }
    }
    private fun HandleGuess(letter: Char, button: Button)
    {
        button.isEnabled = false
        if (UpdateWordState(letter)) {
            UpdateUI()
            if (currentWordState.none { it == '_' })
                ShowEndMessage(true)
        }
        else
        {
            HandleIncorrectGuess()
        }
    }
    private fun UpdateWordState(letter: Char): Boolean
    {
        var found = false
        for (i in wordToGuess.indices)
        {
            if (wordToGuess[i].equals(letter, true))
            {
                currentWordState[i] = wordToGuess[i]
                found = true
            }
        }
        return found
    }

    private fun HandleIncorrectGuess()
    {
        incorrectGuesses++
        UpdateUI()
        if (incorrectGuesses == MAX_INCORRECT_GUESSES)
            ShowEndMessage(false)
    }

    private fun UpdateUI()
    {
        hangedManWord.text = String(currentWordState)
        hangedManImage.setImageResource(images[incorrectGuesses])
    }

    private fun ShowEndMessage(hasWon: Boolean)
    {
        if (hasWon)
            hangedManWord.text = getString(R.string.win_text)
        else
            hangedManWord.text = getString(R.string.lose_text)

        DisableKeyboard(keyboardTop)
        DisableKeyboard(keyboardMiddle)
        DisableKeyboard(keyboardBottom)
    }

    private fun DisableKeyboard(layout: LinearLayout)
    {
        for (i in 0 until layout.childCount)
        {
            layout.getChildAt(i).isEnabled = false
        }
    }

    public fun SetWordToGuess(text: String)
    {
        wordToGuess = text;
    }

}