package com.decompose.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.decompose.components.application_list.ApplicationList
import com.decompose.components.application_page.ApplicationPage

@Composable
fun ApplicationPageUi(
    component: ApplicationPage,
    modifier: Modifier = Modifier,
) {
    ApplicationListUi(
        component = component.applicationList,
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun ApplicationPageUiPreview() {
    ApplicationPageUi(
        component = ApplicationPagePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class ApplicationPagePreview : ApplicationPage {
    override val applicationList: ApplicationList = ApplicationListPreview()
}

