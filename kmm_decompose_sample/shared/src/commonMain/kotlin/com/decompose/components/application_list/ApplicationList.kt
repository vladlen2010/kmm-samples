package com.decompose.components.application_list

import com.arkivanov.decompose.value.Value

interface ApplicationList {

    val model: Value<Model>

    data class Model(
        val applications: List<Application>
    )

    fun onItemClicked(applicationId: String)

}

data class Application( //TODO: transfer to domain layer
    val id: String,
    val title: String
)