package com.sb.fittingKeyboard.service.keyboardtype.boilerplate

import android.annotation.SuppressLint
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sb.fittingKeyboard.databinding.FragmentBoilerplatetextBinding
import com.sb.fittingKeyboard.service.MainKeyboardService
import com.sb.fittingKeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingKeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingKeyboard.service.util.RepeatTouchListener
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel

class BoilerplateTypedKeyboard(
    private val binding: FragmentBoilerplatetextBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!

        val boilerplateTextsAdapter = BoilerplateTextAdapter(
            boilerplateTextsList = mutableMapOf(),
            onClick = { view -> inputBoilerplateText(view) },
            onLongClick = { _, i -> jumpToBoilerplateEditor(i) }
        )

        viewModel.kbTheme.observe(imeService) { boilerplateTextsAdapter.setTheme(it) }

        viewModel.kbFontSize.observe(imeService) { boilerplateTextsAdapter.setFontSize(it) }

        viewModel.kbNormalKeysFontColor.observe(imeService) { boilerplateTextsAdapter.setFontColor(it) }

        viewModel.kbFontType.observe(imeService) { boilerplateTextsAdapter.setFontType(it) }

        viewModel.boilerplateTexts.observe(imeService) { boilerplateTextsAdapter.setBoilerplateTextsList(it) }

        viewModel.kbLongClickInterval.observe(imeService) {
            val longClickInterval = it.toLong() + 100L

            binding.imgbtnBoilerplateDelete.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar()
                    }
                )
            )

            binding.btnBoilerplateSpace.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view)
                    }
                )
            )
        }

        binding.apply {
            imgbtnBoilerplateEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnBoilerplateBack.setOnClickListener {
                if (viewModel.kbHasVibration.value!!) vibrate()
                viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
            }

            recyviewBoilerplateBoilerplatetextItemsContainer.apply {
                adapter = boilerplateTextsAdapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }
}