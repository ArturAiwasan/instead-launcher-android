/*
 * Copyright (c) 2021 Boris Timofeev <btimofeev@emunix.org>
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */

package org.emunix.insteadlauncher.domain.usecase

import org.emunix.insteadlauncher.domain.worker.UpdateRepositoryWorker
import javax.inject.Inject

/**
 * Stop the worker periodically updating the repository
 */
interface StopUpdateRepositoryWorkUseCase {

    fun execute()
}

class StopUpdateRepositoryWorkUseCaseImpl @Inject constructor(
    private val updateRepositoryWorker: UpdateRepositoryWorker
) : StopUpdateRepositoryWorkUseCase {

    override fun execute() {
        updateRepositoryWorker.stop()
    }
}