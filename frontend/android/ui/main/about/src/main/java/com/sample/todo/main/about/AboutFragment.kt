package com.sample.todo.main.about

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.WorkManager
import com.sample.todo.base.entity.DynamicFeatureModule
import com.sample.todo.base.entity.Holder
import com.sample.todo.main.about.databinding.AboutFragmentBinding
import com.sample.todo.base.extension.observeEvent
import com.sample.todo.work.downloadmodule.DownloadModuleWorker


class AboutFragment(
    private val viewModelFactory: AboutViewModelFactory,
    private val workManager: WorkManager,
    private val holder: Holder<AboutFragment>,
    private val navigator: AboutNavigator
) : Fragment()  {
    private lateinit var binding: AboutFragmentBinding
    private val aboutViewModel: AboutViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        holder.instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AboutFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = aboutViewModel
            lifecycleOwner = viewLifecycleOwner
            seedDatabaseButton.setOnClickListener {
                navigator.navigateToSeedDatabaseActivity()
            }
        }
        aboutViewModel.apply {
            navigateToStatisticsEvent.observeEvent(viewLifecycleOwner) {
                navigator.navigateToStatisticsFragment()
            }
            navigateToSettingsEvent.observeEvent(viewLifecycleOwner) {
                navigator.navigateToSettingActivity()
            }
            displayModuleDetailDialogEvent.observeEvent(viewLifecycleOwner) {
                // TODO download module for now, display module detail dialog later
                DownloadModuleWorker.enqueNewWorker(DynamicFeatureModule.SETTINGS)
            }
        }
        return binding.root
    }
}