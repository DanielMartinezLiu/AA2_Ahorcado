package com.enti.dani_pau_marc

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Level
import models.LevelAdapter

class SelectorLevelActivity : AppCompatActivity(), LevelAdapter.OnButtonClickedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selector_level)
        val levels = listOf(
            Level(getString(R.string.ekko)),
            Level(getString(R.string.arcane)),
            Level(getString(R.string.heimerdinger)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko)),
            Level(getString(R.string.ekko))
        )

        val recyclerView: RecyclerView = findViewById(R.id.scroll)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LevelAdapter(levels, this)
    }

    override fun onButtonClick(level: Level) {
        val intent = Intent(this, HangedMan::class.java)
        startActivity(intent)
    }
}