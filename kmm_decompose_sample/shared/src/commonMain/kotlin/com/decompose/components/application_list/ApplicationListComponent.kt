package com.decompose.components.application_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class ApplicationListComponent(
    componentContext: ComponentContext,
    private val onItemClick: (String) -> Unit
) : ApplicationList, ComponentContext by componentContext {

    private val _model = MutableValue(
        ApplicationList.Model(
            List(100) { index ->
                Application(
                    id = "$index",
                    title = "Application $index"
                )
            }
        )
    )
    override val model: Value<ApplicationList.Model> = _model

    override fun onItemClicked(applicationId: String) {
        onItemClick(applicationId)
    }
}