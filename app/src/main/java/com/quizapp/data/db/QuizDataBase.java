package com.quizapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.quizapp.data.history.HistoryDao;
import com.quizapp.model.QuizResult;

public class QuizDataBase {


    @Database(entities = {QuizResult.class}, version = 1, exportSchema = false)
    abstract public class QuizDatabase extends RoomDatabase {
        public abstract HistoryDao getHistoryDao();
    }
    }
