package com.sb.fittingkeyboard.service.keyboardtype.emoji

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.emoji.widget.EmojiButton
import androidx.recyclerview.widget.RecyclerView
import com.sb.fittingKeyboard.R

class EmojiRecyclerAdapter(private var emojis: MutableList<String>): RecyclerView.Adapter<EmojiRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val emoji = view.findViewById<EmojiButton>(R.id.emjbtn_emoji)

        fun bindEmoji(emojis: MutableList<String>, listener: OnItemClickListener?, position: Int) {
            emoji.text = emojis.elementAt(position)
            emoji.setOnClickListener {
                if (listener == null) return@setOnClickListener
                else listener.onItemClick(it, position)
            }
        }
    }

    var mListener : OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.mListener = listener
    }

    //Create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val emojiButton = LayoutInflater.from(parent.context).inflate(R.layout.item_emoji, parent, false)
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
}