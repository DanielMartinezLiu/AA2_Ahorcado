package com.enti.aa2_ahorcado

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.GridLayout;
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    // Componentes UI
    private lateinit var hangedManImage: ImageView
    private lateinit var hangedManWord: TextView
    private lateinit var keyboard: GridLayout

    // Lógica del juego
    private var wordToGuess: String = "ME QUIERO MORIR"
    private var currentWordState = wordToGuess.map { if (it == ' ') ' ' else '_' }.toCharArray()
    private var incorrectGuesses: Int = 0
    private val maxIncorrectGuesses = 6

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
        InitKeyboard()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hangedManMain)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        UpdateUI()
    }
    private fun InitUI()
    {
        hangedManImage = findViewById(R.id.hangedManImage)
        hangedManWord = findViewById(R.id.hangedManWord)
        keyboard = findViewById(R.id.keyboard)
    }
    private fun InitKeyboard()
    {
        for (i in 0 until keyboard.childCount)
        {
            (keyboard.getChildAt(i) as? Button)?.let { button ->
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
        if (incorrectGuesses == maxIncorrectGuesses)
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
            hangedManWord.text = "You Win!"
        else
            hangedManWord.text = "You Lose!"
        DisableKeyboard()
    }

    private fun DisableKeyboard()
    {
        for (i in 0 until keyboard.childCount)
        {
            keyboard.getChildAt(i).isEnabled = false
        }
    }
}
