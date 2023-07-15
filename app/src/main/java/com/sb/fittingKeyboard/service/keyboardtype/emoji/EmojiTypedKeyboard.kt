package com.sb.fittingKeyboard.service.keyboardtype.emoji

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import androidx.viewpager2.widget.ViewPager2
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentEmojiBinding
import com.sb.fittingKeyboard.EMOJI_ICON_WIDTH
import com.sb.fittingKeyboard.service.MainKeyboardService
import com.sb.fittingKeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingKeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingKeyboard.service.keyboardtype.emoji.indicator.CustomIndicator
import com.sb.fittingKeyboard.service.util.RepeatTouchListener
import com.sb.fittingKeyboard.service.util.e1SmileysAndEmoticons
import com.sb.fittingKeyboard.service.util.e2PeopleAndBody
import com.sb.fittingKeyboard.service.util.e3AnimalsAndNature
import com.sb.fittingKeyboard.service.util.e4FoodAndDrink
import com.sb.fittingKeyboard.service.util.e5TravelAndPlaces
import com.sb.fittingKeyboard.service.util.e6Activities
import com.sb.fittingKeyboard.service.util.e7Objects
import com.sb.fittingKeyboard.service.util.e8Symbols
import com.sb.fittingKeyboard.service.util.e9Flags
import com.sb.fittingKeyboard.service.util.emojiIconList
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel
import org.json.JSONArray

class EmojiTypedKeyboard(
    private val binding: FragmentEmojiBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    private val emojisViewPager by lazy { binding.root.findViewById<ViewPager2>(R.id.viewpager_emoji) }
    private val customEmojiIndicator by lazy { binding.root.findViewById<CustomIndicator>(R.id.indicator_emoji_list) }
    private val emojiScrollView: HorizontalScrollView by lazy { binding.root.findViewById(R.id.scrollview_emoji_container) }

    private var emojiPageChangeCallback: ViewPager2.OnPageChangeCallback? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!

        val emojiAdapterList = listOf(
            EmojiRecyclerAdapter(e1SmileysAndEmoticons),
            EmojiRecyclerAdapter(e2PeopleAndBody),
            EmojiRecyclerAdapter(e3AnimalsAndNature),
            EmojiRecyclerAdapter(e4FoodAndDrink),
            EmojiRecyclerAdapter(e5TravelAndPlaces),
            EmojiRecyclerAdapter(e6Activities),
            EmojiRecyclerAdapter(e7Objects),
            EmojiRecyclerAdapter(e8Symbols),
            EmojiRecyclerAdapter(e9Flags)
        )

        val emojiPagerAdapter = EmojiViewPagerAdapter(
            mutableListOf(),
            emojiAdapterList,
            0,
            null,
            null
        )

        binding.apply {
            imgbtnEmojiBack.setOnClickListener {
                vibrate()
                viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
            }
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            val longClickInterval = it.toLong() + 100L

            binding.imgbtnEmojiDelete.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar()
                    }
                )
            )
        }

        emojisViewPager.adapter = emojiPagerAdapter

        viewModel.kbEmojiColumns.observe(imeService) { (emojisViewPager.adapter as EmojiViewPagerAdapter).changeColumns(it) }

        viewModel.kbRecentlyUsedEmoticons.observe(imeService) {
            val jsonArray = JSONArray(it)
            val arr = mutableListOf<String>()

            for (i in 0 until jsonArray.length()) {
                if (jsonArray.optString(i).isNotBlank()) arr.add(jsonArray.optString(i))
            }
            (emojisViewPager.adapter as EmojiViewPagerAdapter).changeAdapter(arr)
        }

        val emojiIconClickListeners = MutableList(emojiIconList.size) { View.OnClickListener { } }
        for (i in emojiIconClickListeners.indices) {
            emojiIconClickListeners[i] = View.OnClickListener {
                emojisViewPager.currentItem = i
            }
        }

        customEmojiIndicator.createIconPanel(
            iconsList = emojiIconList,
            position = 1,
            clickListeners = emojiIconClickListeners
        )
        (emojisViewPager.adapter as EmojiViewPagerAdapter).initListener(
            object : EmojiRecyclerAdapter.OnItemClickListener {
                override fun onItemClick(v: View, pos: Int) {
                    clearComposingStep()
                    imeService.currentInputConnection.commitText((v as Button).text.toString(), 1)
                    viewModel.setRecentlyUsedEmoticon(v.text.toString())
                }
            },
            object : EmojiRecyclerLiveDataAdapter.OnItemClickListener {
                override fun onItemClick(v: View, pos: Int) {
                    clearComposingStep()
                    imeService.currentInputConnection.commitText((v as Button).text.toString(), 1)
                }
            })
        emojisViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        emojisViewPager.offscreenPageLimit = 1

        // 무한스크롤 뷰페이저
        emojiPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val mPosition = emojisViewPager.currentItem
                customEmojiIndicator.selectPosition(position = mPosition)
                emojiScrollView.smoothScrollTo(getEmojiIconXPosition(emojiScrollView, mPosition), 0)

                /** emojisViewPager.changeAdapter()로 데이터셋 변경 알림시킬 경우, 뷰페이저 1페이지에서 스크롤이 자꾸 고정되는 현상을 방지하고자
                 *  데이터셋 변경 알림은 notifyE0DataSetChanged으로 뷰페이저 페이지 변경 시 실행하도록 함
                 **/
                if (mPosition == 0) (emojisViewPager.adapter as EmojiViewPagerAdapter).notifyE0DataSetChanged()
            }
        }
        emojisViewPager.registerOnPageChangeCallback(emojiPageChangeCallback!!)
    }

    fun setViewPagerDefault() {
        emojisViewPager.setCurrentItem(1, false)
    }

    fun unregisterPageChangeCallback() {
        if (emojiPageChangeCallback == null) return

        emojisViewPager.unregisterOnPageChangeCallback(emojiPageChangeCallback!!)
        emojiPageChangeCallback = null
    }

    private fun getEmojiIconXPosition(view: FrameLayout, position: Int): Int {
        val emojiIconWidth = (EMOJI_ICON_WIDTH * Resources.getSystem().displayMetrics.density).toInt()
        val _position: Float = (view.width / emojiIconWidth) / 2F

        return (emojiIconWidth * (position - _position) + emojiIconWidth /2).toInt()
    }
}