package com.quizapp.data.model;

import com.google.gson.annotations.SerializedName;
import com.quizapp.model.Category;

import java.util.List;

public class CategoriesResponse {


    @SerializedName("trivia-categories")
    List<Category> categories;

}
