package com.enti.dani_pau_marc

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Level
import models.LevelAdapter

class SelectorLevelActivity : AppCompatActivity(), LevelAdapter.OnButtonClickedListener {
    private lateinit var themeSwitch: SwitchCompat

    // Cambio de tema
    private var nightMode : Boolean = false
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selector_level)

        SwitchTheme();

        val levels = listOf(
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
        )

        val recyclerView: RecyclerView = findViewById(R.id.scroll)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LevelAdapter(levels, this)
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
    override fun onButtonClick(level: Level) {
        val intent = Intent(this, HangedMan::class.java)
        intent.putExtra("level_word", level.word)
        startActivity(intent)
    }
}