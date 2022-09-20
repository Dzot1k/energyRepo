package ru.App.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import ru.App.databinding.FragmentAnalysisBinding
import ru.App.viewModel.MainViewModel

class FragmentAnalysis : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentAnalysisBinding
    private var radioGroupBlock: Boolean = false
    private lateinit var currentVoltage: String
    private lateinit var reporting: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentVoltage = ""
        reporting = ""
        val radioGroup1: RadioGroup = binding.radioGroup
        val radioGroup2: RadioGroup = binding.radioGroup2

        binding.calculate.setOnClickListener {

            if (
                currentVoltage != ""
            ) {
                binding.resultVoltage.text = ""
                binding.resultFrequency.text = ""
                binding.resultAsymmetry.text = ""
                binding.resultNonSin.text = ""
                viewModel.reset()
                reporting = if (binding.editVoltage.text.toString() == "0") "Да" else "Нет"
                viewModel.reportShutdown(reporting)



                if (binding.editVoltage.text.toString() != "" && binding.editVoltage.text.toString() != "0") {
                    val voltage = binding.editVoltage.text.toString()
                    if (viewModel.calculateVoltage(
                            binding.resultVoltage.context,
                            voltage,
                            currentVoltage
                        )
                    ) {
                        binding.resultVoltage.also {
                            it.setTextColor(Color.GREEN)
                            it.text = "Напряжение соответствует норме"
                        }
                    } else {
                        binding.resultVoltage.also {
                            it.setTextColor(Color.RED)
                            it.text = "Напряжение не соответствует норме"
                        }
                    }
                } else {
                    binding.resultVoltage.text = ""
                }

                if (binding.editFrequency.text.toString() != "") {
                    val frequency = binding.editFrequency.text.toString()
                    if (viewModel.calculateFrequency(binding.resultFrequency.context, frequency)) {
                        binding.resultFrequency.also {
                            it.setTextColor(Color.GREEN)
                            it.text = "Частота соответствует норме"
                        }
                    } else {
                        binding.resultFrequency.also {
                            it.setTextColor(Color.RED)
                            it.text = "Частота не соответствует норме"
                        }
                    }
                }

                if (binding.editAsymmetry.text.toString() != "") {
                    val asymmetry = binding.editAsymmetry.text.toString()
                    if (viewModel.calculateAsymmetry(binding.resultAsymmetry.context, asymmetry)) {
                        binding.resultAsymmetry.also {
                            it.setTextColor(Color.GREEN)
                            it.text = "Несимметрия соответствует норме"
                        }
                    } else {
                        binding.resultAsymmetry.also {
                            it.setTextColor(Color.RED)
                            it.text = "Несимметрия не соответствует норме"
                        }
                    }
                }


                if (binding.editNonSinusoidality.text.toString() != "") {
                    val nonSinusoidality = binding.editNonSinusoidality.text.toString()
                    if (viewModel.calculateNonSinusoidality(
                            binding.resultNonSin.context,
                            nonSinusoidality,
                            currentVoltage
                        )
                    ) {
                        binding.resultNonSin.also {
                            it.setTextColor(Color.GREEN)
                            it.text = "Несинусоидальность в норме"
                        }
                    } else {
                        binding.resultNonSin.also {
                            it.setTextColor(Color.RED)
                            it.text = "Несинусоидальность не соответствует норме"
                        }
                    }
                }

            }
            reporting = if (binding.editVoltage.text.toString() == "0") "Да" else "Нет"
            viewModel.reportShutdown(reporting)
        }


        radioGroup1.setOnCheckedChangeListener { _, checkedId ->
            if (radioGroupBlock)
                return@setOnCheckedChangeListener
            val radio: RadioButton = requireView().findViewById(checkedId)
            currentVoltage = radio.text.toString()
            val id: Int = radioGroup2.checkedRadioButtonId
            if (id != -1) {
                radioGroupBlock = true
                radioGroup2.clearCheck()
                radioGroupBlock = false
            }
        }

        radioGroup2.setOnCheckedChangeListener { _, checkedId ->
            if (radioGroupBlock)
                return@setOnCheckedChangeListener
            val radio: RadioButton = requireView().findViewById(checkedId)
            currentVoltage = radio.text.toString()
            val id: Int = radioGroup1.checkedRadioButtonId
            if (id != -1) {
                radioGroupBlock = true
                radioGroup1.clearCheck()
                radioGroupBlock = false
            }
        }

        binding.reset.setOnClickListener {
            radioGroupBlock = true
            radioGroup1.clearCheck()
            radioGroup2.clearCheck()
            radioGroupBlock = false
            viewModel.reset()

            reporting = ""
            currentVoltage = ""
            binding.also {
                it.editVoltage.setText("")
                it.editFrequency.setText("")
                it.editAsymmetry.setText("")
                it.editNonSinusoidality.setText("")
                it.resultVoltage.text = ""
                it.resultFrequency.text = ""
                it.resultAsymmetry.text = ""
                it.resultNonSin.text = ""
            }

        }


    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentAnalysis()
    }
}