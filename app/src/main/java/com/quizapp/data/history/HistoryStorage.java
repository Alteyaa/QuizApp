package com.quizapp.data.history;

import androidx.lifecycle.LiveData;

import com.quizapp.model.QuizResult;
import com.quizapp.model.ShortQuizResult;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    private HistoryDao dao;

    public HistoryStorage(HistoryDao dao){
        this.dao = dao;
    }

    @Override
    public QuizResult getQuizResult(int id) {
          return dao.get(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return (int) dao.insert(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<ShortQuizResult>> getAllShort() {
        return null;
    }
}
