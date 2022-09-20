package ru.App

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import ru.App.adapter.VpAdapter
import ru.App.databinding.ActivityMainBinding
import ru.App.ui.FragmentAnalysis
import ru.App.ui.FragmentRecommendations
import ru.App.ui.FragmentReference
import ru.App.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val fragList = listOf(
        FragmentAnalysis.newInstance(),
        FragmentReference.newInstance(),
        FragmentRecommendations.newInstance()
    )

    private val fragListTitles = listOf(
        "Анализ",
        "Справка",
        "Отчёт"
    )

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = VpAdapter(this, fragList)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, pos ->
            tab.text = fragListTitles[pos]
            viewModel.report.observe(this){
                if (it) {
                    if (pos == 2) {
                        val badge = tab.orCreateBadge
                        badge.number = viewModel.getCount()
                    }
                }
            }
            viewModel.events.observe(this) {
                if (!it.isNullOrBlank()) {
                    if (pos == 2) {
                        val badge = tab.orCreateBadge
                        badge.number = viewModel.getCount()
                    }
                } else {
                    if (pos == 2) {
                        tab.removeBadge()
                    }
                }
            }

        }.attach()


    }
}