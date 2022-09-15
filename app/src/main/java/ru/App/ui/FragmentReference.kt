package ru.App.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import ru.App.databinding.FragmentReferenceBinding
import ru.App.viewModel.MainViewModel

class FragmentReference : Fragment() {
    private lateinit var binding : FragmentReferenceBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.calculation.observe(viewLifecycleOwner){

        }
    }

    companion object {


        @JvmStatic
        fun newInstance() = FragmentReference()
    }
}