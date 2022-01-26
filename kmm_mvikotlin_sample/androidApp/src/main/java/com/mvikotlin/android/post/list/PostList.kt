package com.mvikotlin.android.post.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mvikotlin.components.post_list.PostListItem

@Composable
fun PostList(
    items: List<PostListItem>,
    isRefreshing: Boolean,
    onRefresh: (Boolean) -> Unit,
    onItemClicked: (Long) -> Unit,

) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { onRefresh(true) }
    ) {
        LazyColumn {
            itemsIndexed(items) { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClicked(item.id)
                        }
                ) {
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            )
                    )
                }
                if (index < items.lastIndex) {
                    Divider()
                }
            }
        }
    }
}