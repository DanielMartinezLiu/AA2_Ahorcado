package com.enti.aa2_ahorcado

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    val ANIM_START_DURATION: Int = 2500
    val ANIM_END_DURATION: Int = 5000

    // Componentes UI
    private lateinit var hangedManImage: ImageView
    private lateinit var hangedManWord: TextView
    private lateinit var keyboardTop: LinearLayout
    private lateinit var keyboardMiddle: LinearLayout
    private lateinit var keyboardBottom: LinearLayout
    private lateinit var constraintLayout: ConstraintLayout

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

        InitAnimationDrawable()

        InitKeyboard(keyboardTop)
        InitKeyboard(keyboardMiddle)
        InitKeyboard(keyboardBottom)

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
        keyboardTop = findViewById(R.id.keyboardTop)
        keyboardMiddle = findViewById(R.id.keyboardMiddle)
        keyboardBottom = findViewById(R.id.keyboardBottom)
        constraintLayout = findViewById(R.id.hangedManMain)
    }
    private fun InitAnimationDrawable() {
        val background = constraintLayout.background
        if (background is AnimationDrawable) {
            val animDrawable: AnimationDrawable = background
            animDrawable.setEnterFadeDuration(ANIM_START_DURATION)
            animDrawable.setExitFadeDuration(ANIM_END_DURATION)
            animDrawable.start()
        }
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
}
