package com.example.composepokedex.ui.screen.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.composepokedex.domain.model.Pokemon
import com.example.composepokedex.ui.theme.PokemonTypeColors
import java.util.*

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val lazyItems = uiState.pokemonList.collectAsLazyPagingItems()

    Surface(modifier = Modifier.fillMaxSize().padding(16.dp), color = Color.White) {
        Column(){
            Text("Pokedex", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier,
            ) {
                items(lazyItems) {
                    if (it != null) {
                        PokemonItem(it)
                    }
                }

                lazyItems.apply {
                    when{
                        loadState.refresh is LoadState.Loading ->{
                            item { Text("Refresh loading")}
                        }
                        loadState.append is LoadState.Loading ->{
                            item { Text("Append loading")}
                        }
                        loadState.refresh is LoadState.Error ->{
                            val error = lazyItems.loadState.refresh as LoadState.Error
                            item { Text("Refresh error ${error.error.localizedMessage}")}
                        }
                        loadState.append is LoadState.Error ->{
                            val error = lazyItems.loadState.append as LoadState.Error
                            item { Text("Append error ${error.error.localizedMessage}")}
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonItemPreview(pokemon: Pokemon = Pokemon(id=1, number=1, name="Mijagi", officialArtWorkUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/16.png", types = listOf("fire", "ice"))){
    PokemonItem(pokemon)
}

@Composable
fun PokemonItem(pokemon: Pokemon){
//    val painter = rememberImagePainter(
//        data = pokemon.officialArtWorkUrl,
//        builder = {
//            crossfade(true)
//        }
//    )

    Box(modifier = Modifier.border(0.dp, Color.Transparent)){
        //pokemon info
        PokemonItemInfo(pokemon)

        //pokemon picture
//        Image(painter = painter, contentDescription = pokemon.name)

        AsyncImage(
            model = pokemon.officialArtWorkUrl,
            contentDescription = pokemon.name,
            modifier = Modifier.fillMaxWidth().sizeIn(maxWidth = 150.dp, maxHeight = 120.dp).offset(y = (-10).dp),
            alignment = Alignment.CenterEnd,

        )

    }
//    Spacer(Modifier.height(8.dp))
}

@Composable
fun PokemonItemInfo(pokemon: Pokemon){
    val cardColor = PokemonTypeColors[pokemon.types.first()] ?: Color.LightGray

    val number = pokemon.number.toString().padStart(3, '0')
    val name = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, end = 0.dp, bottom = 16.dp, start = 0.dp)
        .clip(RoundedCornerShape(5))
        .background(cardColor.copy(alpha = .5f))
    ){
        Column(modifier = Modifier
            .padding(8.dp)
        ){
            Text(
                "#$number",
                fontSize = 10.sp,
                letterSpacing = 1.1.sp
            )
            Text(
                name,
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(Modifier.height(4.dp))
            PokemonTypeRow(types = pokemon.types)
        }
    }
}

@Composable
fun PokemonTypeRow(types: List<String>){
    Row(
        Modifier.fillMaxWidth()
    ) {
        types.forEach { type ->
            val name = type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            val typeColor = PokemonTypeColors[type] ?: Color.LightGray

            Box(
                Modifier
                    .clip(RoundedCornerShape(10))
                    .background(typeColor)
                    .padding(2.dp, 0.dp)
            ) {
                Text(name, fontSize = 10.sp, color = Color.White)
            }
            Spacer(Modifier.width(4.dp))
        }
    }
}