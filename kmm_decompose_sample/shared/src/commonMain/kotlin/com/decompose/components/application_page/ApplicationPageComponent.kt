package com.decompose.components.application_page

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.decompose.components.application_list.ApplicationList
import com.decompose.components.application_list.ApplicationListComponent

class ApplicationPageComponent(
    componentContext: ComponentContext,
    onApplicationClick: (String) -> Unit,
) : ApplicationPage, ComponentContext by componentContext {

    override val applicationList: ApplicationList =
        ApplicationListComponent(
            componentContext = childContext(key = "application_page"),
            onItemClick = { applicationId -> onApplicationClick(applicationId) }
        )
}