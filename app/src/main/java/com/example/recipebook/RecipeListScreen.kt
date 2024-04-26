package com.example.recipebook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipebook.data.Datasource
import com.example.recipebook.model.Recipes

@Composable
fun RecipeListApp() {
    var searchText by remember { mutableStateOf("") }
    val recipes = Datasource().loadRecipes()

    Column(
        modifier = Modifier
            .background(Color(0xFFD8BFD8))
    ) {
        Search( //inputs search field
            searchText = searchText,
            onSearchTextChanged = { newText ->
                searchText = newText
            }
        )
        RecipeList(
            recipeList = if (searchText.isNotBlank()) { //for searching
                recipes.filter { recipe ->
                    val recipeName = LocalContext.current.getString(recipe.stringResourceId)
                    recipeName.lowercase().contains(searchText.lowercase()) //compares all stringResourceId as string with the search text field
                }
            } else {
                recipes
            }
        )
    }
}

@Composable
fun Search( //search text field on topbar
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    TextField(
        label = { Text("Search for Recipes...") },
        value = searchText,
        onValueChange = { newText ->
            onSearchTextChanged(newText)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun RecipeList(
    recipeList: List<Recipes>,
    modifier: Modifier = Modifier
) {
    LazyColumn( //converts all recipecards into lazy column
        modifier = modifier
    ) {
        items(recipeList) { recipes ->
            RecipeCard(
                recipes = recipes,
                modifier = Modifier
                    .padding(8.dp)

            )

        }
    }
}


@Composable
fun RecipeCard( //creates the individual cards
    recipes: Recipes,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column{
            Text(
                text = LocalContext.current.getString(recipes.stringResourceId),
                modifier = Modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painter = painterResource(recipes.imageResourceId),
                contentDescription = stringResource(recipes.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

            var showText by remember { mutableStateOf(false) }

            Button( //button to show or hide the recipe
                onClick = { showText = !showText },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (!showText) {
                    Text(text = "Check Recipe")
                } else {
                    Text(text = "Close Recipe")
                }
            }

            if (showText) {
                Text(
                    text = LocalContext.current.getString(recipes.recipeResourceId),
                    modifier = Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RecipeListPreview() {
    RecipeListApp()
}