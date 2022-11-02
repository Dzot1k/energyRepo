package ru.App.ui

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import ru.App.databinding.FragmentRecommendationsBinding
import ru.App.viewModel.MainViewModel


class FragmentRecommendations : Fragment() {
    private lateinit var binding: FragmentRecommendationsBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecommendationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botText.movementMethod = LinkMovementMethod.getInstance()
        binding.textComplaint.movementMethod = LinkMovementMethod.getInstance()

        viewModel.report.observe(viewLifecycleOwner) {
            if (viewModel.report.value == true) {
                binding.emptyText.visibility = View.GONE
                binding.textIfPowerOutageTrue.visibility = View.VISIBLE
            } else {
                binding.textIfPowerOutageTrue.visibility = View.GONE
                if (viewModel.events.value.isNullOrBlank()) {
                    binding.emptyText.visibility = View.VISIBLE
                }
            }
        }

        viewModel.events.observe(viewLifecycleOwner) {
            if (!viewModel.events.value.isNullOrEmpty() && viewModel.events.value!="") {
                binding.textProblems.visibility = View.VISIBLE
                binding.botText.visibility = View.VISIBLE
                binding.textComplaint.visibility = View.VISIBLE
                binding.emptyText.visibility = View.GONE
            } else {
                binding.textProblems.visibility = View.GONE
                binding.botText.visibility = View.GONE
                binding.textComplaint.visibility = View.GONE
            }
            binding.problems.text = viewModel.events.value
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentRecommendations()
    }
}