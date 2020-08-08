package com.example.triviaapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.model.TriviaItem
import kotlinx.android.synthetic.main.lay_history_item.view.*

class HistoryAdapter(private val context: Context) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var alTriviaItem = ArrayList<TriviaItem>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAns1: AppCompatTextView = itemView.tvAns1
        val tvAns2: AppCompatTextView = itemView.tvAns2
        val tvAns3: AppCompatTextView = itemView.tvAns3
        val tvGameCounter: AppCompatTextView = itemView.tvGameCounter
        val tvDateTime: AppCompatTextView = itemView.tvDateTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.lay_history_item, parent, false)
        )
    }

    override fun getItemCount() = alTriviaItem.size

    fun addData(alTriviaItem: ArrayList<TriviaItem>) {
        this.alTriviaItem = alTriviaItem
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = alTriviaItem[position]
        holder.tvAns1.text = item.name
        holder.tvAns2.text = item.bestCricketer
        holder.tvAns3.text = item.colors
        holder.tvGameCounter.text =
            context.getString(R.string.game_suffix).plus(alTriviaItem.size - position)
        holder.tvDateTime.text = item.time
    }
}