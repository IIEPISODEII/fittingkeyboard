package com.sb.fittingkeyboard.keyboardsettings.ui.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sb.fittingKeyboard.R
import com.sb.fittingkeyboard.keyboardsettings.data.BoilerplateTextSettingDataHolder

class BoilerplateTextSettingAdapter(
    val boilerplateTextsList: MutableList<BoilerplateTextSettingDataHolder>,
    val listener: OnTextChangeListener
) :
    RecyclerView.Adapter<BoilerplateTextSettingAdapter.BoilerplateTextSettingViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoilerplateTextSettingViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_boilerplate_setting, parent, false)
        return BoilerplateTextSettingViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return boilerplateTextsList.size
    }

    override fun onBindViewHolder(holder: BoilerplateTextSettingViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onViewRecycled(holder: BoilerplateTextSettingViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    inner class BoilerplateTextSettingViewHolder(itemView: View) : ViewHolder(itemView) {
        private val indexTextview =
            itemView.findViewById<AppCompatTextView>(R.id.tv_setting_boilerplates_viewholder_index)
        private val boilerplateTextView =
            itemView.findViewById<AppCompatEditText>(R.id.et_setting_boilerplates_viewholder_text)

        init {
            boilerplateTextView.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    mTextChangeListener?.onTextChange(adapterPosition, (p0 ?: "").toString())
                }

                override fun afterTextChanged(p0: Editable?) {}

            })
            boilerplateTextView.isFocusable = true
        }

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            indexTextview.text = "${position+1}"
            boilerplateTextView.setText(boilerplateTextsList[position].boilerplateText)
            setOnTextChangeListener()
        }

        fun unbind() {
            mTextChangeListener = null
        }
    }

    private var mTextChangeListener: OnTextChangeListener? = null

    fun setOnTextChangeListener() {
        this.mTextChangeListener = listener
    }

    interface OnTextChangeListener {
        fun onTextChange(boilerplateIndex: Int, boilerplateText: String)
    }
}