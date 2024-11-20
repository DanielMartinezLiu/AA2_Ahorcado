package com.enti.aa2_ahorcado

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var imageView: ImageView? = findViewById(R.id.hangedManImage);
        var textView: TextView = findViewById(R.id.hangedManWord);

        textView?.let {
            CreateEmptyWord("cock", it)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hangedManMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

fun CreateEmptyWord(word: String, textView: TextView)
{
    var emptyWord: String? = "";
    for (i in 1..word.length)
    {
        emptyWord += "_";
    }
    textView.text = emptyWord;
}