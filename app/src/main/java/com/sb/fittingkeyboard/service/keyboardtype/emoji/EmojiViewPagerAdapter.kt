package com.sb.fittingkeyboard.service.keyboardtype.emoji

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sb.fittingKeyboard.R

class EmojiViewPagerAdapter(
    e0List: MutableList<String>,
    private val eAdapterList: List<EmojiRecyclerAdapter>,
    private var columns: Int,
    private var listener: EmojiRecyclerAdapter.OnItemClickListener?,
    private var listener2: EmojiRecyclerLiveDataAdapter.OnItemClickListener?
) :
    RecyclerView.Adapter<EmojiViewPagerAdapter.ViewHolder>() {

    val e0Adapter = EmojiRecyclerLiveDataAdapter(e0List)

    // ViewHolder 선언
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rv: RecyclerView = view.findViewById(R.id.emoji_item_rv)

        fun bind(_position: Int) {
            val gridLayoutManager = GridLayoutManager(itemView.context, columns)
            rv.adapter = when (_position) {
                in 1..9 -> {
                    eAdapterList[_position-1].setOnItemClickListener(listener = listener)
                    eAdapterList[_position-1]
                }
                else -> {
                    e0Adapter.setOnItemClickListener(listener = listener2)
                    e0Adapter
                }
            }
            rv.layoutManager = gridLayoutManager
        }
    }

    // 아이템 개수 카운트
    override fun getItemCount(): Int = eAdapterList.size + 1

    // ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rv = LayoutInflater.from(parent.context).inflate(R.layout.item_emoji_container, parent, false)
        return ViewHolder(rv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    // e0Adapter 데이터셋 변경
    fun changeAdapter(newList: MutableList<String>) {
        this.e0Adapter.renewList(newList)
    }

    // e0Adapter 데이터셋 변경 알림
    fun notifyE0DataSetChanged() {
        this.e0Adapter.notifyItemRangeChanged(0, this.e0Adapter.itemCount)
    }

    fun changeColumns(newColumn: Int) {
        this.columns = newColumn
        notifyDataSetChanged()
    }

    fun initListener(
        newListener: EmojiRecyclerAdapter.OnItemClickListener,
        newListener2: EmojiRecyclerLiveDataAdapter.OnItemClickListener
    ) {
        this.listener = newListener
        this.listener2 = newListener2
    }
}