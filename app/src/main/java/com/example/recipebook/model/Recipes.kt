package com.example.recipebook.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recipes(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val recipeResourceId: Int
)
