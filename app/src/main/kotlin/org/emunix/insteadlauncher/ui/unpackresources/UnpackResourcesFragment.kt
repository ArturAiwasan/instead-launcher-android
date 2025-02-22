/*
 * Copyright (c) 2021-2022 Boris Timofeev <btimofeev@emunix.org>
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */

package org.emunix.insteadlauncher.ui.unpackresources

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.emunix.insteadlauncher.R
import org.emunix.insteadlauncher.databinding.FragmentUnpackResourcesBinding
import org.emunix.insteadlauncher.helpers.visible

@AndroidEntryPoint
class UnpackResourcesFragment : Fragment(R.layout.fragment_unpack_resources) {

    private val binding by viewBinding(FragmentUnpackResourcesBinding::bind)

    private val viewModel: UnpackResourcesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUnpackSuccessStatus().observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                findNavController().navigate(R.id.action_unpackResourcesFragment_to_installedGamesFragment)
            }
        }

        viewModel.getErrorStatus().observe(viewLifecycleOwner) { showError ->
            binding.unpackResourcesProgressBar.visible(!showError)
            binding.errorTextView.visible(showError)
            binding.unpackResourcesTryAgainButton.visible(showError)
        }

        binding.unpackResourcesTryAgainButton.setOnClickListener { viewModel.tryAgainIsClicked() }
    }
}