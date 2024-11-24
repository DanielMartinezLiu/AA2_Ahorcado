package models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.enti.dani_pau_marc.R

class LevelAdapter(private val levels: List<Level>, private val listener:OnButtonClickedListener):RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {
    interface OnButtonClickedListener{
        fun onButtonClick(level: Level)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelAdapter.LevelViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycle_layout, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelAdapter.LevelViewHolder, position: Int) {
        val level=levels[position]
        holder.bind(level)
        holder.levelButton.setOnClickListener{
            listener.onButtonClick(level)
        }
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    class LevelViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val word: TextView =itemView.findViewById(R.id.word)
        private val letter: TextView =itemView.findViewById(R.id.number_of_letters)
        private val difficulty: ImageView =itemView.findViewById(R.id.difficulty_image)
        val levelButton: Button =itemView.findViewById(R.id.select_level_button)
        fun bind(level: Level) {
            val context = itemView.context
            val numOfLetters = level.word.length

            word.text = context.getString(R.string.word_recycle_view) + " " + level.word
            letter.text = context.getString(R.string.letter_recycle_view) + " " + numOfLetters
            if(numOfLetters <= 4)
                difficulty.setImageResource(R.drawable.easy)
            else if(numOfLetters <= 7)
                difficulty.setImageResource(R.drawable.medium)
            else
                difficulty.setImageResource(R.drawable.hard)
        }

    }

}