package com.example.recipebook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipebook.ui.theme.RecipeBookTheme

@Composable
fun RecipeStartScreen(
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6FA)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(
                text = stringResource(R.string.welcome), //Recipes by Ferdinand
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 85.dp, bottom = 50.dp)
            )
            Image(
                painter = painterResource(R.drawable.recipe_app_icon), //adds the chef Icon
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            RecipeStartButton(
                onClick = onNextButtonClicked, //navigates to RecipeListScreen.kt
                modifier = Modifier
                    .padding(top = 20.dp)
            )
        }
    }

@Composable
fun RecipeStartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .widthIn(min = 250.dp)
    ) {
        Text(text = "Start")
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun RecipeStartPreview() {
    RecipeBookTheme {
        RecipeStartScreen(
            onNextButtonClicked = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}