package app.futured.arkitekt.examplehilt.ui

import app.futured.arkitekt.core.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

class MainViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<MainViewModel>
) : BaseViewModelFactory<MainViewModel>() {
    override val viewModelClass = MainViewModel::class
}
