package com.sb.fittingKeyboard.service.emoji

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.com.sb.fittingKeyboard.service.emoji.EmojiRecyclerLiveDataAdapter
import com.sb.fittingKeyboard.com.sb.fittingKeyboard.service.util.EmojiCollections

class EmojiViewPagerAdapter(
    e0List: MutableList<String>,
    private var columns: Int,
    private var listener: EmojiRecyclerAdapter.OnItemClickListener?,
    private var listener2: EmojiRecyclerLiveDataAdapter.OnItemClickListener?
) :
    RecyclerView.Adapter<EmojiViewPagerAdapter.ViewHolder>() {

    val e0Adapter = EmojiRecyclerLiveDataAdapter(e0List)
    val e1Adapter = EmojiRecyclerAdapter(EmojiCollections.e1SmileysAndEmoticons)
    val e2Adapter = EmojiRecyclerAdapter(EmojiCollections.e2PeopleAndBody)
    val e3Adapter = EmojiRecyclerAdapter(EmojiCollections.e3AnimalsAndNature)
    val e4Adapter = EmojiRecyclerAdapter(EmojiCollections.e4FoodAndDrink)
    val e5Adapter = EmojiRecyclerAdapter(EmojiCollections.e5TravelAndPlaces)
    val e6Adapter = EmojiRecyclerAdapter(EmojiCollections.e6Activities)
    val e7Adapter = EmojiRecyclerAdapter(EmojiCollections.e7Objects)
    val e8Adapter = EmojiRecyclerAdapter(EmojiCollections.e8Symbols)
    val e9Adapter = EmojiRecyclerAdapter(EmojiCollections.e9Flags)

    // ViewHolder 선언
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rv: RecyclerView = view.findViewById(R.id.emoji_item_rv)

        fun bind(_position: Int) {
            val gridLayoutManager = GridLayoutManager(itemView.context, columns)
            rv.adapter = when (_position) {
                1 -> {
                    e1Adapter.setOnItemClickListener(listener = listener)
                    e1Adapter
                }
                2 -> {
                    e2Adapter.setOnItemClickListener(listener = listener)
                    e2Adapter
                }
                3 -> {
                    e3Adapter.setOnItemClickListener(listener = listener)
                    e3Adapter
                }
                4 -> {
                    e4Adapter.setOnItemClickListener(listener = listener)
                    e4Adapter
                }
                5 -> {
                    e5Adapter.setOnItemClickListener(listener = listener)
                    e5Adapter
                }
                6 -> {
                    e6Adapter.setOnItemClickListener(listener = listener)
                    e6Adapter
                }
                7 -> {
                    e7Adapter.setOnItemClickListener(listener = listener)
                    e7Adapter
                }
                8 -> {
                    e8Adapter.setOnItemClickListener(listener = listener)
                    e8Adapter
                }
                9 -> {
                    e9Adapter.setOnItemClickListener(listener = listener)
                    e9Adapter
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
    override fun getItemCount(): Int = 10

    // ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rv = LayoutInflater.from(parent.context).inflate(R.layout.emoji_items, parent, false)
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

    fun initListener(newListener: EmojiRecyclerAdapter.OnItemClickListener, newListener2: EmojiRecyclerLiveDataAdapter.OnItemClickListener) {
        this.listener = newListener
        this.listener2 = newListener2
    }
}