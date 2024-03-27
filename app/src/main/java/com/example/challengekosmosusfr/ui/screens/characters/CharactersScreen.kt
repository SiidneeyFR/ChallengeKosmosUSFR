package com.example.challengekosmosusfr.ui.screens.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.challengekosmosusfr.ui.components.CharacterItem
import com.example.challengekosmosusfr.ui.components.LoadingItem
import com.example.challengekosmosusfr.ui.components.Title
import com.example.challengekosmosusfr.ui.theme.ChallengeKosmosUSFRTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel
) {
    val state : CharactersViewModel.UiState by charactersViewModel.state.collectAsState()

    val isRefreshing = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing.value)
    val dashboardScroll = rememberScrollState()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
        }
    }

    ChallengeKosmosUSFRTheme {
        when {
            state.isShowLoading -> {
                LoadingItem(
                    title = "Cargando personajes"
                )
            }
            state.characters.isNotEmpty() -> {
                Column() {
                    Title("Personajes de Rick y Morty")
                    SwipeRefresh(
                        state = swipeRefreshState,
                        onRefresh = {
                            isRefreshing.value = true
                        },
                        refreshTriggerDistance = 150.dp,
                        modifier = Modifier
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxHeight()
                                .verticalScroll(dashboardScroll)
                                .nestedScroll(nestedScrollConnection)
                        ) {
                            state.characters.forEach { itCharacter ->
                                CharacterItem(
                                    character = itCharacter
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}