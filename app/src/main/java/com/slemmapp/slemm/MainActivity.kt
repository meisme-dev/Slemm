package com.slemmapp.slemm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slemmapp.slemm.api.Api
import com.slemmapp.slemm.components.Padding
import com.slemmapp.slemm.components.PostView
import com.slemmapp.slemm.components.SlemmBar
import com.slemmapp.slemm.ui.theme.SlemmTheme

var baseurl: String? = null;

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlemmTheme {
                val main = Main()
                main.MainContent()
            }
        }
    }
}

class Main {
    data class State(
        var posts: Api.PostList? = null,
        var page: Int = 1,
        var api: Api? = null,
        var instance: Api.Service? = null,
        var mutablePage: MutableState<Int>? = null,
        var mutablePosts: MutableState<Api.PostList?>? = null,
    ) {
        suspend fun loadPosts() {
            val innerInstance = api!!.getInstance().create(Api.Service::class.java)
            posts = innerInstance.getPosts(page.toString()).body()
            instance = innerInstance
            mutablePosts?.value = posts
        }

        suspend fun loadMorePosts() {
//            var newPosts: List<Api.PostInfo>? = posts?.posts
//            if (newPosts != null) {
//                instance?.getPosts(page.toString())?.body()?.let { newPosts.plus(it.posts) }
//
//            }
            val newPosts: SnapshotStateList<Api.PostInfo>? = instance?.getPosts(page.toString())
                ?.body()?.posts
//            val newPosts = mutablePosts!!.value?.posts!! + (instance?.getPosts(
//                page.toString()
//            )?.body()?.posts ?: emptyList())
            if (newPosts != null) {
                posts?.posts?.addAll(newPosts)
            }
            mutablePosts?.value = posts
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun MainContent() {
        var state = State()
        state.mutablePage = remember { mutableStateOf(state.page) }
        state.mutablePosts = remember {
            mutableStateOf(state.posts)
        }

        Scaffold(topBar = { SlemmBar() }) { paddingValues ->
            LaunchedEffect(state.posts) {
                if (state.posts == null) {
                    state.api = Api(
                        "https://lemmy.world/api/v3/",
                        Api.AuthBody(usernameOrEmail = null, password = null)
                    )
                    state.loadPosts()
                }
                state.mutablePosts?.value = state.posts
            }
            if (state.mutablePosts!!.value == null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Padding(padding = 2.dp)
                    Text("Loading posts...")
                }
            } else {
                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    PostView(state)

                }
            }

        }
    }
}