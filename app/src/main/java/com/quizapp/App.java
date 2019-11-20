package com.quizapp;

import android.app.Application;

import data.IQuizRepository;
import data.QuizRepository;

public class App extends Application {

   public static IQuizRepository quizRepository;


    @Override
    public void onCreate() {
        super.onCreate();
        quizRepository = new QuizRepository();

    }
}
