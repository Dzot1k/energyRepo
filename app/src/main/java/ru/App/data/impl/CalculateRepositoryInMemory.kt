package ru.tne.Application.data.impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import ru.App.R
import ru.App.data.CalculateRepository
import ru.App.models.AnalysisModel

class CalculateRepositoryInMemory : CalculateRepository {

    override val calculation = MutableLiveData<AnalysisModel>()

    override val problems = MutableLiveData("")
    override val events = MutableLiveData("")

    private var problemsCount: Int = 0
    private var eventsCount: Int = 0


    private val textForProblems
        get() = problems.value

    private val textForEvents
        get() = events.value

    override fun getCount(): Int{
        return problemsCount
    }

    override fun calculateVoltage(
        context: Context,
        voltage: String,
        voltageStandard: String
    ): Boolean {
        val voltageDouble = voltage.toDouble()
        val voltageStandardDouble = voltageStandard.toDouble()

        val voltageStandardMin = voltageStandardDouble * (1 - voltageDeviation)
        val voltageStandardMax = voltageStandardDouble * (1 + voltageDeviation)
        if (voltageDouble in voltageStandardMin..voltageStandardMax) return true

        problemsCount++
        problems.value = textForProblems + "\n" + String.format(
            context.getString(R.string.problemVoltage),
            problemsCount,
            voltage,
            voltageStandard
        )
        eventsCount++
        events.value =
            textForEvents + "\n" + String.format(
                context.getString(R.string.voltage_less198_more242),
                eventsCount
            )
        return false
    }

    override fun calculateFrequency(context: Context, frequency: String): Boolean {
        if (frequency.toDouble() in frequencyRangeHz) return true
        if (problemsCount > 0) {
            problemsCount++
            problems.value = textForProblems + "\n" + String.format(
                context.getString(R.string.problemFrequency),
                problemsCount,
                frequency
            )
        } else {
            problemsCount++
            problems.value = textForProblems + "\n" + String.format(
                context.getString(R.string.problemFrequency),
                problemsCount,
                frequency
            )
            eventsCount++
            events.value = textForEvents + "\n" + String.format(
                context.getString(R.string.voltage_less198_more242),
                eventsCount
            )

        }
        return false
    }

    override fun calculateAsymmetry(context: Context, asymmetry: String): Boolean {
        val asymmetryDouble = asymmetry.toDouble()
        if (asymmetryDouble in asymmetryRange) return true
        if (asymmetryDouble in 2.0..4.0) {
            problemsCount++
            problems.value = textForProblems + "\n" + String.format(
                context.getString(R.string.problemAsymmetry),
                problemsCount,
                asymmetry
            )
            eventsCount++
            events.value = textForEvents + "\n" + String.format(
                context.getString(R.string.asymmetry2_4),
                eventsCount
            )
        } else {
            problemsCount++
            problems.value = textForProblems + "\n" + String.format(
                context.getString(R.string.problemAsymmetry),
                problemsCount,
                asymmetry
            )
            eventsCount++
            events.value = textForEvents + "\n" + String.format(
                context.getString(R.string.asymmetryMore4),
                eventsCount
            )
        }
        return false
    }

    override fun calculateNonSinus(
        context: Context,
        nonSinusoidality: String,
        voltageStandard: String
    ): Boolean {
        val nonSinusoidalityDouble = nonSinusoidality.toDouble()
        when (voltageStandard.toInt()) {
            220, 380 -> if (nonSinusoidalityDouble in nonSinusRange220and380) return true
            else {
                if (nonSinusoidalityDouble in 8.0..12.0) {
                    problemsCount++
                    problems.value = textForProblems + "\n" + String.format(
                        context.getString(R.string.problemNonSinus),
                        problemsCount,
                        nonSinusoidality,
                        "0 - 12"
                    )
                    eventsCount++
                    events.value = textForEvents + "\n" + String.format(
                        context.getString(R.string.nonSinusLess),
                        eventsCount
                    )
                } else {
                    problemsCount++
                    problems.value = textForProblems + "\n" + String.format(
                        context.getString(R.string.problemNonSinus),
                        problemsCount,
                        nonSinusoidality,
                        "0 - 12"
                    )
                    eventsCount++
                    events.value = textForEvents + "\n" + String.format(
                        context.getString(R.string.nonSinusMore),
                        eventsCount
                    )
                }
            }

            6, 10 -> if (nonSinusoidalityDouble in nonSinusRange6And10) return true
            else {
                if (nonSinusoidalityDouble in 5.0..8.0) {
                    problemsCount++
                    problems.value = textForProblems + "\n" + String.format(
                        context.getString(R.string.problemNonSinus),
                        problemsCount,
                        nonSinusoidality,
                        "0 - 8"
                    )
                    eventsCount++
                    events.value = textForEvents + "\n" + String.format(
                        context.getString(R.string.nonSinusLess),
                        eventsCount
                    )
                } else {
                    problemsCount++
                    problems.value = textForProblems + "\n" + String.format(
                        context.getString(R.string.problemNonSinus),
                        problemsCount,
                        nonSinusoidality,
                        "0 - 8"
                    )
                    eventsCount++
                    events.value = textForEvents + "\n" + String.format(
                        context.getString(R.string.nonSinusMore),
                        eventsCount
                    )
                }
            }
            35 -> if (nonSinusoidalityDouble in nonSinusRange35) return true
            else {
                if (nonSinusoidalityDouble in 4.0..6.0) {
                    problemsCount++
                    problems.value = textForProblems + "\n" + String.format(
                        context.getString(R.string.problemNonSinus),
                        problemsCount,
                        nonSinusoidality,
                        "0 - 6"
                    )
                    eventsCount++
                    events.value = textForEvents + "\n" + String.format(
                        context.getString(R.string.nonSinusLess),
                        eventsCount
                    )
                } else {
                    problemsCount++
                    problems.value = textForProblems + "\n" + String.format(
                        context.getString(R.string.problemNonSinus),
                        problemsCount,
                        nonSinusoidality,
                        "0 - 6"
                    )
                    eventsCount++
                    events.value = textForEvents + "\n" + String.format(
                        context.getString(R.string.nonSinusMore),
                        eventsCount
                    )
                }
            }
            else -> if (nonSinusoidalityDouble in nonSinusRange110And220) return true
            else {
                if (nonSinusoidalityDouble in 2.0..3.0) {
                    problemsCount++
                    problems.value = textForProblems + "\n" + String.format(
                        context.getString(R.string.problemNonSinus),
                        problemsCount,
                        nonSinusoidality,
                        "0 - 3"
                    )
                    eventsCount++
                    events.value = textForEvents + "\n" + String.format(
                        context.getString(R.string.nonSinusLess),
                        eventsCount
                    )
                } else {
                    problemsCount++
                    problems.value = textForProblems + "\n" + String.format(
                        context.getString(R.string.problemNonSinus),
                        problemsCount,
                        nonSinusoidality,
                        "0 - 3"
                    )
                    eventsCount++
                    events.value = textForEvents + "\n" + String.format(
                        context.getString(R.string.nonSinusMore),
                        eventsCount
                    )
                }
            }
        }
        return false
    }

    override fun reset() {
        problems.value = ""
        events.value = ""
        problemsCount = 0
        eventsCount = 0
    }


    companion object {
        const val voltageDeviation = 0.1
        val frequencyRangeHz = 49.6..50.4
        val asymmetryRange = 0.0..2.0
        val nonSinusRange220and380 = 0.0..8.0
        val nonSinusRange6And10 = 0.0..5.0
        val nonSinusRange35 = 0.0..4.0
        val nonSinusRange110And220 = 0.0..2.0
    }


}