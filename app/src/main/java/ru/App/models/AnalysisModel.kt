package ru.App.models

data class AnalysisModel(
    val voltage: Double = -1.0,
    val frequency: Double = -1.0,
    val nonSinusoidality: Double = -1.0,
    val asymmetry: Double = -1.0
) {
}