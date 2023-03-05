package com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.data.ToolbarSettingDataHolder

class ToolbarSettingAdapter(
    var toolbarSettingList: MutableList<ToolbarSettingDataHolder>
) :
    RecyclerView.Adapter<ToolbarSettingAdapter.ToolbarSettingViewHolder>(),
    ItemTouchHelperListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolbarSettingViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_toolbar_setting, parent, false)
        return ToolbarSettingViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return toolbarSettingList.size
    }

    override fun onBindViewHolder(holder: ToolbarSettingViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ToolbarSettingViewHolder(itemView: View) : ViewHolder(itemView) {
        private val iconImageView =
            itemView.findViewById<ImageView>(R.id.iv_toolbar_setting_viewholder_icon)
        private val descriptionTextView =
            itemView.findViewById<TextView>(R.id.tv_toolbar_setting_viewholder_description)
        private val isActiveSwitch =
            itemView.findViewById<SwitchCompat>(R.id.switch_toolbar_setting_viewholder_activator)
        private val divider = itemView.findViewById<View>(R.id.v_toolbar_setting_viewholder_divider)

        fun bind(position: Int) {
            Glide.with(itemView)
                .load(toolbarSettingList[position].iconDrawable)
                .into(iconImageView)

            descriptionTextView.text = toolbarSettingList[position].descriptionText
            isActiveSwitch.isChecked = toolbarSettingList[position].isActivated

            divider.visibility =
                if (position == toolbarSettingList.lastIndex) View.GONE else View.VISIBLE
        }
    }

    override fun onItemMove(from: Int, to: Int): Boolean {
        val temp = toolbarSettingList[from]
        toolbarSettingList.removeAt(from)
        toolbarSettingList.add(to, temp)
        for (i in from..to) {
            mOnToolbarChanged?.onToolbarChange(toolbarSettingList[i].settingId, i)
        }

        notifyItemMoved(from, to)
        return true
    }

    override fun onItemSwipe(position: Int) {

    }

    private var mOnToolbarChanged: OnToolbarChanged? = null

    fun setOnToolbarChangeListener(listener: OnToolbarChanged) {
        this.mOnToolbarChanged = listener
    }

    interface OnToolbarChanged {
        fun onToolbarChange(id: String, value: Int)
    }
}