package ru.App.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.App.data.CalculateRepository
import ru.tne.Application.data.impl.CalculateRepositoryInMemory


class MainViewModel : ViewModel() {

    private val repository: CalculateRepository = CalculateRepositoryInMemory()

    val events by repository::events

    fun getCount(): Int{
        return repository.getCount()
    }

    fun reset(){
        repository.reset()
    }

    fun calculateVoltage(context: Context, voltage: String, voltageStandard: String): Boolean {
        return repository.calculateVoltage(context, voltage, voltageStandard)
    }

    fun calculateFrequency(context: Context, frequency: String): Boolean {
        return repository.calculateFrequency(context, frequency)
    }

    fun calculateAsymmetry(context: Context, asymmetry: String): Boolean {
        return repository.calculateAsymmetry(context, asymmetry)
    }

    fun calculateNonSinusoidality(context: Context, nonSinusoidality: String, voltageStandard: String): Boolean {
        return repository.calculateNonSinus(context, nonSinusoidality, voltageStandard)
    }
}