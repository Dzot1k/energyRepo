package ru.App.data

import android.content.Context
import androidx.lifecycle.LiveData
import ru.App.models.AnalysisModel


interface CalculateRepository {

    val calculation: LiveData<AnalysisModel>

    val events: LiveData<String>

    val problems: LiveData<String>

    fun calculateVoltage(context: Context, voltage: String, voltageStandard: String): Boolean

    fun calculateFrequency(context: Context, frequency: String): Boolean

    fun calculateAsymmetry(context: Context, asymmetry: String): Boolean

    fun calculateNonSinus(context: Context, nonSinusoidality: String, voltageStandard: String): Boolean

    fun reset()

    fun getCount(): Int
}