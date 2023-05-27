package com.sb.fittingKeyboard.keyboardSettings.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.keyboardSettings.data.KeyboardThemesDataHolder

class ThemeRecyclerAdapter(private val themeList: List<KeyboardThemesDataHolder>)
    : RecyclerView.Adapter<ThemeRecyclerAdapter.ThemeDataViewHolder>() {
    private var selectedPosition = 0
    private var mPickThemeListener: OnPickThemeListener? = null

    fun setOnPickThemeListener(listener: OnPickThemeListener) {
        this.mPickThemeListener = listener
    }

    fun initializeCurrentTheme(position: Int) {
        selectedPosition = position
        notifyItemChanged(selectedPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme_setting, parent, false)
        return ThemeDataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return themeList.size
    }

    override fun onBindViewHolder(holder: ThemeDataViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ThemeDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val themeImage  : ImageView = itemView.findViewById(R.id.iv_theme)
        private val checkedItem : ImageView = itemView.findViewById(R.id.iv_ischecked)

        fun bind(position: Int) {
            themeImage.setImageResource(themeList[position].themeImage)

            if (selectedPosition == position) {
                checkedItem.setBackgroundResource(R.drawable.ic_ischecked)
                checkedItem.alpha = 0.8F
            } else {
                checkedItem.setBackgroundResource(0)
                checkedItem.alpha = 1.0F
            }
        }

        init {
            themeImage.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(selectedPosition)

                mPickThemeListener?.onPick(selectedPosition)
            }
        }
    }

    interface OnPickThemeListener {
        fun onPick(themeIndex: Int)
    }
}