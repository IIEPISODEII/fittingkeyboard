package com.sb.fittingKeyboard.service.emoji

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.emoji.widget.EmojiButton
import androidx.recyclerview.widget.RecyclerView
import com.sb.fittingKeyboard.R

class EmojiRecyclerLiveDataAdapter(private var emojis: MutableList<String>) :
    RecyclerView.Adapter<EmojiRecyclerLiveDataAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emoji = view.findViewById<EmojiButton>(R.id.emoji)

        fun bindEmoji(emojis: MutableList<String>, listener: OnItemClickListener?, position: Int) {
            emoji.text = emojis.elementAt(position)
            emoji.setOnClickListener {
                if (listener == null) return@setOnClickListener
                else listener.onItemClick(it, position)
            }
        }
    }

    var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.mListener = listener
    }

    //Create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val emojiButton =
            LayoutInflater.from(parent.context).inflate(R.layout.emoji_button, parent, false)
        return ViewHolder(emojiButton)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position >= emojis.size) return
        holder.bindEmoji(emojis, mListener, position)
    }

    // recyclerview에 표시할 전체 리스트 사이즈
    override fun getItemCount() = emojis.size

    /**
     * @param v- 클릭한 뷰
     * @param pos - 클릭한 뷰의 위치
     */
    interface OnItemClickListener {
        fun onItemClick(v: View, pos: Int)
    }

    fun renewList(new: MutableList<String>) {
        this.emojis = new
    }
}