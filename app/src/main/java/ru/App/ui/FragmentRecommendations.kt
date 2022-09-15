package ru.App.ui

import android.os.Bundle
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
        viewModel.recommendations.observe(viewLifecycleOwner) {
            if (!viewModel.recommendations.value.isNullOrEmpty()) {
                binding.textProblems.visibility = View.VISIBLE
                binding.textRecommendations.visibility = View.VISIBLE
                binding.botText.visibility = View.VISIBLE
                binding.emptyText.visibility = View.GONE
            } else {
                binding.textProblems.visibility = View.GONE
                binding.textRecommendations.visibility = View.GONE
                binding.botText.visibility = View.GONE
                binding.emptyText.visibility = View.VISIBLE
            }
            binding.problems.text = viewModel.problems.value
            binding.recommendations.text = it


        }
    }

    companion object {


        @JvmStatic
        fun newInstance() = FragmentRecommendations()
    }
}