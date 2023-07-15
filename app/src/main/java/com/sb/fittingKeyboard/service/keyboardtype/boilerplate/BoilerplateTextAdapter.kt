package com.sb.fittingKeyboard.service.keyboardtype.boilerplate

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.data.KEYBOARD_BOILERPLATE_TEXTS_LIST

class BoilerplateTextAdapter(
    private val boilerplateTextsList: MutableMap<String, String>,
    private val onClick: (View) -> Unit,
    private val onLongClick: (View, Int) -> Unit
) :
    RecyclerView.Adapter<BoilerplateTextAdapter.BoilerplateViewHolder>()
{
    private var mOnClick: ((View) -> Unit)? = null
    private var mOnLongClick: ((View, Int) -> Unit)? = null
    private var mTheme = 0
    private var mFontSize = 0
    private var mFontType = 0
    private var mFontColor = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoilerplateViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_boilerplate_text, parent, false)

        return BoilerplateViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return boilerplateTextsList.size
    }

    override fun onBindViewHolder(holder: BoilerplateViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onViewRecycled(holder: BoilerplateViewHolder) {
        super.onViewRecycled(holder)
    }

    inner class BoilerplateViewHolder(itemView: View) : ViewHolder(itemView) {
        private val boilerplateButton =
            itemView.findViewById<Button>(R.id.bpvh_btn_boilerplate_text)

        init {
            boilerplateButton.setOnClickListener { view ->
                mOnClick?.let { it -> it(view) }
            }
            boilerplateButton.setOnLongClickListener { view ->
                mOnLongClick?.let { it -> it(view, adapterPosition) }
                return@setOnLongClickListener true
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            boilerplateButton.apply {
                text = boilerplateTextsList[KEYBOARD_BOILERPLATE_TEXTS_LIST[position]]
                typeface = when(mFontType) {
                    1 -> ResourcesCompat.getFont(this.context, R.font.aritta)
                    2 -> ResourcesCompat.getFont(this.context, R.font.dovemayo)
                    3 -> ResourcesCompat.getFont(this.context, R.font.imcresoojin)
                    4 -> ResourcesCompat.getFont(this.context, R.font.maplestorylight)
                    5 -> ResourcesCompat.getFont(this.context, R.font.nanumbarungothic)
                    6 -> ResourcesCompat.getFont(this.context, R.font.nanumsquarer)
                    7 -> ResourcesCompat.getFont(this.context, R.font.seoulnamsan)
                    8 -> ResourcesCompat.getFont(this.context, R.font.tttogether)
                    9 -> ResourcesCompat.getFont(this.context, R.font.cookierun)
                    10 -> ResourcesCompat.getFont(this.context, R.font.tmoney)
                    11 -> ResourcesCompat.getFont(this.context, R.font.tadaktadak)
                    else -> Typeface.DEFAULT
                }
                setTextColor(mFontColor)
                textSize = mFontSize.toFloat() / resources.displayMetrics.density
                setBackgroundResource(
                    when (mTheme) {
                        0 -> R.drawable.keydesign_14_char
                        1 -> R.drawable.keydesign_00_char
                        2 -> R.drawable.keydesign_04_char
                        3 -> R.drawable.keydesign_08_char
                        4 -> R.drawable.keydesign_09_char
                        5 -> R.drawable.keydesign_05_char
                        6 -> R.drawable.keydesign_10_char
                        7 -> R.drawable.keydesign_02_a
                        8 -> R.drawable.keydesign_03_char
                        9 -> R.drawable.keydesign_06_char
                        10 -> R.drawable.keydesign_07_char
                        11 -> R.drawable.keydesign_07_char
                        12 -> R.drawable.keydesign_07_char
                        13 -> R.drawable.keydesign_07_char
                        14 -> R.drawable.keydesign_07_char
                        15 -> R.drawable.keydesign_15_char
                        16 -> R.drawable.keydesign_16_char
                        17 -> R.drawable.keydesign_07_char
                        18 -> R.drawable.keydesign_18_char
                        else -> R.drawable.keydesign_14_char
                    }
                )
            }
            mOnClick = onClick
            mOnLongClick = onLongClick
        }
    }

    fun setBoilerplateTextsList(newMap: MutableMap<String, String>) {
        for (key in KEYBOARD_BOILERPLATE_TEXTS_LIST) {
            if (newMap[key] == boilerplateTextsList[key]) continue
            notifyItemChanged(KEYBOARD_BOILERPLATE_TEXTS_LIST.indexOf(key))
            boilerplateTextsList[key] = newMap[key]!!
        }
    }

    fun setTheme(theme: Int) {
        this.mTheme = theme
        notifyDataSetChanged()
    }

    fun setFontType(type: Int) {
        this.mFontType = type
        notifyDataSetChanged()
    }

    fun setFontSize(size: Int) {
        this.mFontSize = size
        notifyDataSetChanged()
    }

    fun setFontColor(color: Int) {
        this.mFontColor = color
        notifyDataSetChanged()
    }
}