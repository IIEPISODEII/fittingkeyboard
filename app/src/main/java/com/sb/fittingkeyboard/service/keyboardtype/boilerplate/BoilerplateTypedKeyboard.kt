package com.sb.fittingkeyboard.service.keyboardtype.boilerplate

import android.annotation.SuppressLint
import android.content.Context.VIBRATOR_SERVICE
import android.os.Vibrator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sb.fittingKeyboard.databinding.FragmentBoilerplatetextBinding
import com.sb.fittingkeyboard.service.BoilerplateTextAdapter
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class BoilerplateTypedKeyboard(
    private val binding: FragmentBoilerplatetextBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val vibrator = binding.root.context.getSystemService(VIBRATOR_SERVICE) as Vibrator

        val boilerplateTextsAdapter = BoilerplateTextAdapter(
            boilerplateTextsList = mutableMapOf(),
            onClick = { view -> inputBoilerplateText(view, vibrator) },
            onLongClick = { _, i -> jumpToBoilerplateEditor(i) }
        )

        viewModel.kbTheme.observe(imeService) { boilerplateTextsAdapter.setTheme(it) }

        viewModel.kbFontSize.observe(imeService) { boilerplateTextsAdapter.setFontSize(it) }

        viewModel.kbNormalKeysFontColor.observe(imeService) { boilerplateTextsAdapter.setFontColor(it) }

        viewModel.kbFontType.observe(imeService) { boilerplateTextsAdapter.setFontType(it) }

        viewModel.boilerplateTexts.observe(imeService) { boilerplateTextsAdapter.setBoilerplateTextsList(it) }

        binding.recyviewBoilerplateBoilerplatetextItemsContainer.apply {
            adapter = boilerplateTextsAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}