package com.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.quizapp.data.IQuizRepository;
import com.quizapp.data.QuizRepository;
import com.quizapp.data.db.QuizDataBase;
import com.quizapp.data.history.HistoryStorage;
import com.quizapp.data.history.IHistoryStorage;

public class App extends Application {

   public static IQuizRepository quizRepository;
    public static QuizDataBase.QuizDatabase database;
    public static IHistoryStorage historyStorage;


    @Override
    public void onCreate() {
        super.onCreate();

        database = Room
                .databaseBuilder(this, QuizDataBase.QuizDatabase.class, "quiz")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        historyStorage = new HistoryStorage(database.getHistoryDao());
        quizRepository = new QuizRepository();

    }
}
