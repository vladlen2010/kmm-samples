package com.decompose.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.application_list.Application
import com.decompose.components.application_list.ApplicationList

@Composable
fun ApplicationListUi(
    component: ApplicationList,
    modifier: Modifier = Modifier,
) {
    val model by component.model.subscribeAsState()

    LazyColumn(
        modifier = modifier
    ) {
        items(model.applications) { application ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        component.onItemClicked(application.id)
                    }
            ) {
                Text(
                    text = application.title,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ApplicationListUiPreview() {
    ApplicationListUi(
        component = ApplicationListPreview()
    )
}

class ApplicationListPreview : ApplicationList {
    override val model: Value<ApplicationList.Model> =
        MutableValue(
            ApplicationList.Model(
                List(25) {
                    Application(
                        id = "$it",
                        title = "Application $it"
                    )
                }
            )
        )

    override fun onItemClicked(applicationId: String) {}
}